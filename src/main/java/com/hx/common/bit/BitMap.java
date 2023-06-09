/**
 * file name : BitMap.java
 * created at : 下午10:56:39 2016年8月11日
 * created by 970655147
 */

package com.hx.common.bit;

import com.hx.common.util.InnerTools;

import java.util.BitSet;

//将bitPerData个bit视为一组数组
//约定最高位为符号位, 剩余的bit为数据位
// 约定 00...0 为 (-maxPos-1)
// 0001 -> +1
// 1001 -> -1
// 0000 -> 0
// 1000 -> -8
// 无符号的规则, 则所有的bit均为数据位
// 0001 -> 1
// 1001 -> 129
// 0000 -> 0
// 1000 -> 128
public final class BitMap {

    /**
     * 每一个data使用多少bit存放
     */
    private int bitPerData;
    /**
     * 存放的数据的数量
     */
    private int capacity;
    /**
     * 存放的数据是否有符号
     */
    private boolean signed;

    /**
     * 存放实际数据的BitSet
     */
    private BitSet data;
    /**
     * bitPerData可以表示的最大的正整数
     */
    private int maxPos;
    /**
     * bitPerData可以表示的最小的负整数
     */
    private int minNeg;
    /**
     * << 代替乘法优化
     */
    private int leftShift;

    /**
     * 初始化
     * this.bitPerData = bitSizeFor(bitPerData); 是为了计算索引的优化
     * 可以提取三个接口, 就可以自定义是否优化的策略了[bitSizeFor, calcByteTh, calcBitOff]
     *
     * @param bitPerData 每一个data使用多少bit存放
     * @param capacity   存放的数据的数量
     * @param signed     存放的数据是否有符号
     * @since 1.0
     */
    public BitMap(int bitPerData, int capacity, boolean signed) {
        InnerTools.assert0(((bitPerData > 0) && (bitPerData < 32)), "'bitPerData' must in [0, 32) !");
        InnerTools.assert0((capacity > 0), "'capacity' must in [0, #) !");

        this.bitPerData = bitPerData;
        this.capacity = capacity;
        this.signed = signed;
        maxPos = calcMaxPos(this.bitPerData - 1);
        minNeg = (-maxPos) - 1;
        // -128 <-> 127
        // 		|| + 128
        //      \/
        // 	  0 <-> 255
        if (!signed) {
            minNeg += (maxPos + 1);
            maxPos += (maxPos + 1);
        }
        data = new BitSet(calcByteCnt(this.bitPerData * this.capacity));
        leftShift = -1;
        leftShift = ((bitPerData & bitPerData - 1) == 0) ? calcShfitBit(bitPerData) : leftShift;
    }

    public BitMap(int bitPerData, int capacity) {
        this(bitPerData, capacity, true);
    }

    // ----------------- 工具方法 -----------------------

    /**
     * 创建无符号的BitMap
     *
     * @param val 给定的数字
     * @return com.hx.common.bit.BitMap
     * @author Jerry.X.He
     * @date 5/5/2017 7:50 PM
     * @since 1.0
     */
    public static BitMap newUnsignedInt(int val) {
        val = (val < 0) ? -val : val;
        return newUnsignedInt(val, String.valueOf(val).length());
    }

    public static BitMap newUnsignedInt(int val, int cap) {
        // 4bit : 0 - 9
        BitMap result = new BitMap(4, cap, false);
        return setInt(result, val);
    }

    /**
     * 配置给定的result的前(val.length)个bit为其对应的数值
     *
     * @param result 给定的BitMap
     * @param val    需要配置值
     * @return com.hx.common.bit.BitMap
     * @author Jerry.X.He
     * @date 5/5/2017 7:52 PM
     * @since 1.0
     */
    public static BitMap setInt(BitMap result, int val) {
        InnerTools.assert0(result != null, "'bitMap' can't be null !");

        val = (val < 0) ? -val : val;
        String valStr = String.valueOf(val);
        if (InnerTools.isEmpty(valStr)) {
            return result;
        }

        int idx = 0;
        for (int i = valStr.length() - 1; i >= 0; i--) {
            result.set(idx++, valStr.charAt(i) - '0');
        }
        return result;
    }

    /**
     * 复制给定的BitMap
     *
     * @param bitMap 给定的bitMap
     * @return com.hx.common.bit.BitMap
     * @author Jerry.X.He
     * @date 5/5/2017 7:54 PM
     * @since 1.0
     */
    public static BitMap copyOf(BitMap bitMap) {
        InnerTools.assert0(bitMap != null, "'bitMap' can't be null !");

        BitMap result = new BitMap(bitMap.getBitPerData(), bitMap.getCapacity(), bitMap.isSigned());
        result.data.or(bitMap.data);
        return result;
    }

    /**
     * set(i), 配置第N个数据为_data
     *
     * @param idx   第N个数据
     * @param _data 配置的数据
     * @return com.hx.common.bit.BitMap
     * @author Jerry.X.He
     * @date 5/5/2017 7:54 PM
     * @since 1.0
     */
    public BitMap set(int idx, int _data) {
        InnerTools.assert0(((idx >= 0) && (idx < capacity)), "'idx'[" + idx + "] not in range [0, " + capacity + ") !");
        InnerTools.assert0(((_data >= minNeg) && (_data <= maxPos)), "'data'[" + _data + "] not in range [" + minNeg + ", " + maxPos + "] !");

        int lower = calcLowerBit(idx);
        int higher = (lower + bitPerData) - 1;
        // 根据是否最00..00, 或者"是负数" 更新第一个bit
        if (_data == minNeg) {
            if (signed) {
                data.set(higher);
            } else {
                data.clear(higher);
            }
            data.clear(lower, higher);
            return this;
        }

        boolean isNeg = (_data < 0);
        _data = isNeg ? -_data : _data;
        // update signed
        if (!signed) {
            isNeg = _data >= (((maxPos - minNeg) + 1) >> 1);
        }
        if (isNeg) {
            data.set(higher);
        } else {
            data.clear(higher);
        }
        for (int i = 0, dataLen = bitPerData - 1; i < dataLen; i++) {
            // low -> high		// data & 1; _data >>= 1; | bitOff-(bitPerData-1)+i
            if ((_data & 1) != 0) {
                data.set(lower + i);
            } else {
                data.clear(lower + i);
            }
            _data >>= 1;
        }

        return this;
    }

    /**
     * get(i), 获取idx所在的数据
     * signed			unsigned		update
     * -128 - 127		0 - 255
     * 1 00..00[-128]	128				spec
     * 1 00..01[-1]		129				128 - i
     * 1 11..11[-127]	255				128 - i
     * 0 00..00[0]		0				i
     * 0 00..01[1]		1				i
     *
     * @param idx 第N个数据
     * @return int
     * @author Jerry.X.He
     * @date 5/5/2017 7:56 PM
     * @since 1.0
     */
    public int get(int idx) {
        InnerTools.assert0(((idx >= 0) && (idx < capacity)), "'idx'[" + idx + "] not in range !");

        int lower = calcLowerBit(idx);
        int higher = (lower + bitPerData) - 1;
        boolean isNeg = data.get(higher);
        int result = 0;
        for (int i = 0, dataLen = bitPerData - 1; i < dataLen; i++) {
            // high -> low		// bitOff - i - 1
            result <<= 1;
            if (data.get(higher - i - 1)) {
                result++;
            }
        }

        // 对于singed, unsigned单独处理
        if (isNeg) {
            if (result == 0) {
                if (signed) {
                    result = minNeg;
                } else {
                    result = ((maxPos - minNeg) + 1) >> 1;
                }
            } else {
                if (signed) {
                    result = -result;
                } else {
                    result = (((maxPos - minNeg) + 1) >> 1) + result;
                }
            }
        }

        return result;
    }

    /**
     * getter
     */
    public int getBitPerData() {
        return bitPerData;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isSigned() {
        return signed;
    }

    // ---------------------------- 辅助方法 ----------------------------

    /**
     * 根据需要的bit的数量, 计算需要的字节的个数
     *
     * @param allBit 所有的bit
     * @return int
     * @author Jerry.X.He
     * @date 5/5/2017 7:58 PM
     * @since 1.0
     */
    private int calcByteCnt(int allBit) {
        return ((allBit - 1) >> 3) + 1;
    }

    /**
     * 获取使用bitPerData可以表示的最大的数字
     *
     * @param bitPerData 每一个data使用多少bit存放
     * @return int
     * @author Jerry.X.He
     * @date 5/5/2017 7:58 PM
     * @since 1.0
     */
    private int calcMaxPos(int bitPerData) {
        return (2 << (bitPerData - 1)) - 1;
    }

    /**
     * 计算idx表示的数字的最低位bit的位置
     *
     * @param idx 第N个数据
     * @return int
     * @author Jerry.X.He
     * @date 5/5/2017 7:59 PM
     * @since 1.0
     */
    private int calcLowerBit(int idx) {
        if (leftShift >= 0) {
            return idx << leftShift;
        } else {
            return idx * bitPerData;
        }
    }

    /**
     * 计算 * n 等价于 << 的位数
     *
     * @param bitPerData 每一个data使用多少bit存放
     * @return int
     * @author Jerry.X.He
     * @date 5/5/2017 7:59 PM
     * @since 1.0
     */
    public int calcShfitBit(int bitPerData) {
        switch (bitPerData) {
            case 1:
                return 0;
            case 2:
                return 1;
            case 4:
                return 2;
            case 8:
                return 3;
            case 16:
                return 4;
            case 32:
                return 5;
            default:
                throw new RuntimeException("unsupported 'bitPerData': " + bitPerData);
        }
    }

}

// ---------------- 一个简单的实现, 但是 还有很多不完善的地方, 不能处理bitPerData的场景[在BitSet的实现之前的实现] ------------------------
// 将bitPerData个bit视为一组数组
// 约定最高位为符号位, 剩余的bit为数字位
// 约定 00...0 为 (-maxPos-1)
// 0001 -> +1
// 1001 -> -1
// 0000 -> -8
//public class BitMap {
//
//	// 存放数据的数组, 一个数据占用多少个bit, 当前BitMap的数据容量
//	private byte[] data;
//	private int bitPerData;
//	private int maxPos;
//	private int minNeg;
//	private int capacity;
//	
//	// 初始化
//	// this.bitPerData = bitSizeFor(bitPerData); 是为了计算索引的优化
//	// 可以提取三个接口, 就可以自定义是否优化的策略了[bitSizeFor, calcByteTh, calcBitOff]
//	public BitMap(int bitPerData, int capacity) {
//		Tools.assert0(((bitPerData > 0) && (bitPerData < 32) ), "'bitPerData' must in [0, 32) !");
//		Tools.assert0((capacity > 0), "'capacity' must in [0, #) !");
//		
//		this.bitPerData = bitSizeFor(bitPerData);
//		this.capacity = capacity;
//		maxPos = calcMaxPos(this.bitPerData-1);
//		minNeg = (- maxPos) - 1;
//		data = new byte[calcByteCnt(this.bitPerData * this.capacity) ];
//	}
//	
//	// set(i)
//	public void set(int idx, int _data) {
//		Tools.assert0(((idx >= 0) && (idx < capacity) ), "'idx'[" + idx + "] not in range [0, " + capacity + ") !");
//		Tools.assert0(((_data >= minNeg) && (_data <= maxPos) ), "'data'[" + _data + "] not in range [" + minNeg + ", " + maxPos + "] !");
//		
//		int byteTh = calcByteTh(idx);
//		//   high <- low
//		// 7 6 5 4 - 3 2 1 0	// bit order
//		// 1 0 0 1 - 0 1 0 1	// bit data
//		int bitOff = calcBitOff(idx);
//		if(_data == minNeg) {
//			data[byteTh] |= (1 << bitOff);
//			for(int i=0, dataLen=bitPerData-1; i<dataLen; i++) {
//				data[byteTh] &= (~ (1 << (bitOff-(bitPerData-1)+i)) );
//			}
//			return ;
//		}
//		
//		boolean isNeg = (_data < 0) ? true : false;
//		_data = isNeg ? - _data : _data;
//		if(isNeg) {
//			data[byteTh] |= (1 << bitOff);
//		} else {
//			data[byteTh] &= (~ (1 << bitOff));
//		}
//		for(int i=0, dataLen=bitPerData-1; i<dataLen; i++) {
//			// low -> high		// data & 1; _data >>= 1; | bitOff-(bitPerData-1)+i
//			if((_data & 1) != 0) {
//				data[byteTh] |= (1 << (bitOff-(bitPerData-1)+i) );
//			} else {
//				data[byteTh] &= (~ (1 << (bitOff-(bitPerData-1)+i)) );
//			}
//			_data >>= 1;
//		}
//	}
//	
//	// get(i)
//	public int get(int idx) {
//		Tools.assert0(((idx >= 0) && (idx < capacity) ), "'idx'[" + idx + "] not in range !");
//		
//		int byteTh = calcByteTh(idx);
//		//   high <- low
//		// 7 6 5 4 - 3 2 1 0	// bit order
//		// 1 0 0 1 - 0 1 0 1	// bit data
//		int bitOff = calcBitOff(idx);
//		boolean isNeg = ((data[byteTh] & (1<<bitOff) ) != 0) ? true : false; 
//		int result = 0;
//		for(int i=0, dataLen=bitPerData-1; i<dataLen; i++) {
//			// high -> low		// bitOff - i - 1
//			result <<= 1;
//			if((data[byteTh] & (1 << (bitOff-i-1)) ) != 0) {
//				result ++;
//			}
//		}
//		if(isNeg) {
//			if(result == 0) {
//				result = minNeg;
//			} else {
//				result = - result;
//			}
//		}
//		
//		return result;
//	}
//	
//	// ---------------------------- 辅助方法 ----------------------------
//	// 根据需要的bit的数量, 计算需要的字节的个数
//	private int calcByteCnt(int allBit) {
//		return ((allBit - 1) >> 3) + 1;
//	}
//	private int calcMaxPos(int bitPerData) {
//		return (2 << (bitPerData-1) ) - 1;
//	}
//	private int calcByteTh(int i) {
//		switch(bitPerData) {
//			case 1: 
//				return i >> 3;
//			case 2:
//				return i >> 2;
//			case 4:
//				return i >> 1;
//			case 8:
//				return i >> 0;
//			case 16:
//				return i << 1;
//			case 32:
//				return i << 2;
//			default :
//				throw new RuntimeException("have not this 'bitPerData' !");
//		}
//	}
//	private int calcBitOff(int i) {
//		if(bitPerData >= 8) {
//			return 0;
//		} else {
//			return ((i & (calcByteTh(8)-1)) << calcShiftLeftBitOff()) + bitPerData - 1;
//		}
//	}
//	private int calcShiftLeftBitOff() {
//		switch(bitPerData) {
//			case 1: 
//				return 0;
//			case 2:
//				return 1;
//			case 4:
//				return 2;
//			case 8:
//				return 3;
//			default :
//				throw new RuntimeException("have not this 'bitPerData' !");
//		}
//	}
//    private static final int bitSizeFor(int cap) {
//        int n = cap - 1;
//        n |= n >>> 1;
//        n |= n >>> 2;
//        n |= n >>> 4;
//        n |= n >>> 8;
//        n |= n >>> 16;
//        return n + 1;
//    }
//	
//}
