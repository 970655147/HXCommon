/**
 * file name : IdxIterator.java
 * created at : 下午9:47:52 2016年8月11日
 * created by 970655147
 */

package com.hx.common.interf.idx;

// 一个获取索引的迭代器
public interface IdxIterator {

	/**
	 * 是否还有下一个索引
	 *
	 * @return  true if next invoke of next() return idx, or else
	 * @author Jerry.X.He
	 * @date 4/12/2017 10:17 PM
	 * @since 1.0
	 */
	boolean hasNext();

	/**
	 * 获取下一个索引
	 *
	 * @return  next index
	 * @author Jerry.X.He
	 * @date 4/12/2017 10:17 PM
	 * @since 1.0
	 */
	int next();

	/**
	 * 根据当前的状态, 复制一个 IdxIterator
	 *
	 * @return the copied IdxIterator of this
	 * @author Jerry.X.He
	 * @date 6/8/2017 6:58 PM
	 * @since 1.0
	 */
	IdxIterator copy();
	
}
