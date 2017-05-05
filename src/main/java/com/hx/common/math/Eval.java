/**
 * file name : Eval.java
 * created at : 下午6:41:13 2016年8月11日
 * created by 970655147
 */

package com.hx.common.math;

import com.hx.common.util.InnerTools;

import java.util.*;

/**
 * 实现类似于js的eval函数 [计算表达式部分]
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/5/2017 8:47 PM
 */
public final class Eval {

    // disable constructor
    private Eval() {
        InnerTools.assert0(false, "can't instantiate !");
    }

    // 各个符号的常量, 匹配的符号映射, 各个符号的优先级
    /**
     * +
     */
    public static final char ADD = '+';
    /**
     * -
     */
    public static final char SUB = '-';
    /**
     * *
     */
    public static final char MUL = '*';
    /**
     * /
     */
    public static final char DIV = '/';
    /**
     * %
     */
    public static final char MOD = '%';
    /**
     * (
     */
    public static final char LEFT_BRACKET = '(';
    /**
     * )
     */
    public static final char RIGHT_BRACKET = ')';

    /**
     * 括号对
     */
    private static final Map<Character, Character> MATCHED = new HashMap<>();
    /**
     * 约定优先级
     */
    private static Map<Character, Integer> PRIORITIES = new HashMap<>();

    // 初始化
    static {
        MATCHED.put(LEFT_BRACKET, RIGHT_BRACKET);

        PRIORITIES.put(ADD, 0);
        PRIORITIES.put(SUB, 0);
        PRIORITIES.put(MUL, 1);
        PRIORITIES.put(DIV, 1);
        PRIORITIES.put(MOD, 1);
        PRIORITIES.put(LEFT_BRACKET, 2);
        PRIORITIES.put(RIGHT_BRACKET, 2);
    }

    /**
     * 先统计出exp中各个符号的位置  在用eval0真正计算
     * 首先记录所有的符号的位置, 添加到一个符号列表中[如果 有子计算过程, 递归计算]
     * 然后  计算给定的表达式
     *
     * @param exp 给定的表达式
     * @return int
     * @author Jerry.X.He
     * @date 5/5/2017 8:48 PM
     * @since 1.0
     */
    public static int eval(String exp) {
        InnerTools.assert0(exp != null, "exp can't be null !");

        // Operator, Operation
        List<Integer> resInBrackets = new LinkedList<>();
        List<Operator> optr = new LinkedList<Operator>();
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            switch (c) {
                case ADD:
                case SUB:
                case MUL:
                case DIV:
                case MOD:
                    optr.add(new Operator(c, i));
                    break;
                case LEFT_BRACKET:
                    int nextI = getNextMatched(exp, i + 1, LEFT_BRACKET, MATCHED);
                    resInBrackets.add(eval(exp.substring(i + 1, nextI)));
                    optr.add(new Operator(LEFT_BRACKET, i));
                    optr.add(new Operator(RIGHT_BRACKET, nextI));
                    i = nextI;
                default:
                    break;
            }
        }

//		while(optr.size() > 0) {
//			System.out.println(optr.pop());
//		}

        int val = eval0(exp, optr, resInBrackets, null);


        return val;
    }

    /**
     * 计算给定的表达式  从"首 -> 尾"
     * 获取第一个操作数作为默认的返回值
     * 然后 获取第一个操作符, 以及下一个操作符
     * 如果下一个操作符的优先级大于当前操作符, 递归计算(当前结果, 第一个操作符之后的部分) [这里可以为本方法添加两个参数, 将带return的递归构造成尾递归, 不过这里  就不做了]
     * 否则  计算第一个操作符旁边的两个操作数, 循环, 直到操作符列表中只剩下一个操作符
     * 计算最后一个操作符  二边的操作数
     *
     * @param exp           给定的表达式
     * @param optr          解析表达式之后的列表
     * @param resInBrackets ()之间的表达式的值
     * @param lastOptNow    上一个Operand
     * @return int
     * @author Jerry.X.He
     * @date 5/5/2017 8:49 PM
     * @since 1.0
     */
    private static int eval0(String exp, List<Operator> optr, List<Integer> resInBrackets, Operator lastOptNow) {
        if (InnerTools.isEmpty(optr)) {
            return Integer.parseInt(exp);
        }

        int res = 0;
        if (lastOptNow == null) {
            Operator firstOpt = optr.get(0);
            if (firstOpt.oper == LEFT_BRACKET) {
                optr.remove(0);
                optr.remove(0);
                res = resInBrackets.remove(0);
            } else {
                res = parseFirstInt(exp, firstOpt);
            }
        } else {
            res = parseInt(exp, lastOptNow, optr.get(0));
        }

        while (optr.size() > 1) {
            Operator optNow = optr.get(0);
            Operator optNext = optr.get(1);
            optr.remove(0);
            if (PRIORITIES.get(optNext.oper) > PRIORITIES.get(optNow.oper)) {
                if (optNext.oper == LEFT_BRACKET) {
                    Operator optNNext = null;
                    if (optr.size() > 2) {
                        optNNext = optr.get(2);
                    }
                    if ((optNNext != null) && (PRIORITIES.get(optNNext.oper) > PRIORITIES.get(optNow.oper))) {
                        return calc(res, optNow, eval0(exp, optr, resInBrackets, null));
                    } else {
                        optr.remove(0);
                        optr.remove(0);
                        res = calc(res, optNow, resInBrackets.remove(0));
                    }
                } else {
                    return calc(res, optNow, eval0(exp, optr, resInBrackets, optNow));
                }
            } else {
                res = calc(res, optNow, parseInt(exp, optNow, optNext));
            }
        }

        if (optr.size() > 0) {
            res = calc(res, optr.get(0), parseLastInt(exp, optr.get(0)));
        }

        return res;
    }
    // 计算给定的表达式  从"尾 -> 首"
//	private static int eval0(String exp, List<Operator> optr, List<Integer> resInBrackets, Operator lastOptNow) {
//		int res = 0;
//		if(lastOptNow == null) {
//			Operator lastOpt = optr.get(optr.size()-1);
//			if(lastOpt.oper == RIGHT_BRACKET) {
//				optr.remove(optr.size()-1);
//				optr.remove(optr.size()-1);
//				res = resInBrackets.remove(resInBrackets.size()-1);
//			} else {
//				res = parseLastInt(exp, lastOpt );
//			}
//		} else {
//			res = parseInt(exp, lastOptNow, optr.get(optr.size()-1) );
//		}
//		while(optr.size() > 1) {
//			Operator optNow = optr.get(optr.size()-1);
//			Operator optNext = optr.get(optr.size()-2);
//			optr.remove(optr.size()-1);
//			if(PRIORITIES.get(optNext.oper) > PRIORITIES.get(optNow.oper) ) {
//				if(optNext.oper == RIGHT_BRACKET) {
//					Operator optNNext = optr.get(optr.size()-3);
//					if(PRIORITIES.get(optNNext.oper) > PRIORITIES.get(optNow.oper) ) {
//						return calc(eval0(exp, optr, resInBrackets, null), optNow, res );
//					} else {
//						optr.remove(optr.size()-1);
//						optr.remove(optr.size()-1);
//						res = calc(resInBrackets.remove(resInBrackets.size()-1), optNow, res );
//					}
//				} else {
//					return calc(eval0(exp, optr, resInBrackets, optNow), optNow, res );
//				}
//			} else {
//				res = calc(parseInt(exp, optNow, optNext), optNow, res );
//			}
//		}
//		if(optr.size() > 0) {
//			res = calc(parseFirstInt(exp, optr.get(0)), optr.get(0), res );
//		}
//		
//		return res;
//	}

    /**
     * 计算val01, val02  的opt操作  返回结果
     *
     * @param val01 操作数1
     * @param opt   操作
     * @param val02 操作数2
     * @return int
     * @author Jerry.X.He
     * @date 5/5/2017 8:51 PM
     * @since 1.0
     */
    private static int calc(int val01, Operator opt, int val02) {
        int val = 0;
        switch (opt.oper) {
            case ADD:
                val = val01 + val02;
                break;
            case SUB:
                val = val01 - val02;
                break;
            case MUL:
                val = val01 * val02;
                break;
            case DIV:
                val = val01 / val02;
                break;
            case MOD:
                val = val01 % val02;
                break;
        }

        return val;
    }

    /**
     * 解析当前运算符 和下一个运算符之间的的操作数
     *
     * @param exp     计算的表达式
     * @param optNow  当前Operand
     * @param optNext 下一个Operand
     * @return int
     * @author Jerry.X.He
     * @date 5/5/2017 8:51 PM
     * @since 1.0
     */
    private static int parseInt(String exp, Operator optNow, Operator optNext) {
        String intStr = null;
        try {
            intStr = exp.substring(optNow.index + 1, optNext.index).trim();
            return Integer.parseInt(intStr);
        } catch (Exception e) {
            InnerTools.err("error while parse first operand[idx : " + optNow.index + "][' " + intStr + " '] !");
            throw e;
        }
    }

    /**
     * 解析当前运算符 之后的的操作数
     *
     * @param exp    正在计算的表达式
     * @param optNow 当前Operand
     * @return int
     * @author Jerry.X.He
     * @date 5/5/2017 8:52 PM
     * @since 1.0
     */
    private static int parseLastInt(String exp, Operator optNow) {
        try {
            return Integer.parseInt(exp.substring(optNow.index + 1).trim());
        } catch (Exception e) {
            InnerTools.err("error while parse prev operand[idx : " + optNow.index + "] !");
            throw e;
        }
    }

    /**
     * 计算当前表达式的第一个操作数
     *
     * @param exp    正在计算的表达式
     * @param optNow 当前Operand
     * @return int
     * @author Jerry.X.He
     * @date 5/5/2017 8:53 PM
     * @since 1.0
     */
    private static int parseFirstInt(String exp, Operator optNow) {
        try {
            return Integer.parseInt(exp.substring(0, optNow.index).trim());
        } catch (Exception e) {
            InnerTools.err("error while parse first operand[idx : " + optNow.index + "] !");
            throw e;
        }
    }

    /**
     * 获取当前位置的符号匹配的下一个符号
     *
     * @param exp     当前正在计算的表达式
     * @param idx     left的索引
     * @param left    left
     * @param matched 记录配对的符号对
     * @return int
     * @author Jerry.X.He
     * @date 5/5/2017 8:53 PM
     * @since 1.0
     */
    private static int getNextMatched(String exp, int idx, char left, Map<Character, Character> matched) {
        Deque<Character> stack = new LinkedList<>();
        stack.push(left);
        for (int i = idx; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if (ch == matched.get(stack.peek())) {
                stack.pop();
                if (stack.size() == 0) {
                    idx = i;
                    break;
                }
                continue;
            }
            if (matched.containsKey(ch)) {
                stack.push(ch);
            }
        }

        return idx;
    }

    // --------------- bean --------------------

    /**
     * 封装每一个操作符   以及其索引
     *
     * @author Jerry.X.He <970655147@qq.com>
     * @version 1.0
     * @date 5/5/2017 8:54 PM
     */
    private static class Operator {
        /**
         * 操作符
         */
        char oper;
        /**
         * 当前操作符在表达式的位置
         */
        int index;

        /**
         * 初始化
         *
         * @param oper  操作符
         * @param index 当前操作符在表达式的位置
         * @since 1.0
         */
        Operator(char oper, int index) {
            this.oper = oper;
            this.index = index;
        }

        public Operator() {

        }

        /**
         * for debug ..
         *
         * @return java.lang.String
         * @author Jerry.X.He
         * @date 5/5/2017 8:55 PM
         * @since 1.0
         */
        public String toString() {
            return oper + " -> " + index + "; ";
        }
    }

}
