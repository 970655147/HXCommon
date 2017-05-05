/**
 * file name : FileNameMatcher.java
 * created at : 5:02:38 PM Nov 29, 2015
 * created by 970655147
 */

package com.hx.common.file;

import com.hx.common.util.InnerTools;

/**
 * FileNameMatcher
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 5/5/2017 8:37 PM
 */
public final class FileNameMatcher {

    // disable constructor
    private FileNameMatcher() {
        InnerTools.assert0("can't instantiate !");
    }

    /**
     * 所支持的通配符, '?' 可以表示一个任意字符, '*' 表示任意个任意字符[具体的匹配由isGreedy进行约束]
     * 因为普通场景下面, 没有'?', '*' 的文件名, 因此这里便没有写'?', '*'本身的表示[如果 要写的话, 模拟转义吧][在搜索wildCard的时候, 不能搜索转义的'?', '*', 在匹配的时候, 将'\?', '\*'替换为真实的字符串表示的'?', '*' ]
     * 一个pattern中各个子pattern的分隔符, 各个子pattern之间的关系为 "短路或"
     * 这里 并没有约定"短路与"的场景[优先级问题 会导出很多问题], 请自行使用match进行实现吧
     */
    /**
     * 匹配一个字符的通配符
     */
    public static final Character MATCH_ONE = '?';
    /**
     * 匹配多个字符的通配符
     */
    public static final Character MATCH_MULTI = '*';
    /**
     * 通配符列表
     */
    public static final char[] WILDCARDS = new char[]{MATCH_ONE, MATCH_MULTI};
    /**
     * ?的索引
     */
    public static final int MATCH_ONE_IDX = 0;
    /**
     * *的索引
     */
    public static final int MATCH_MULTI_IDX = 1;
    /**
     * 分割多个pattern的符号
     */
    public static final String PATTERN_SEP = "\\|";

    /**
     * 判断给定的fileName是否匹配给定的pattern
     *
     * @param fileName 给定的文件名
     * @param pattern  给定的pattern
     * @param isGreedy 表示是否采用贪婪匹配的模式[也就是通配符'*'是否贪婪]
     * @return boolean
     * @author Jerry.X.He
     * @date 5/5/2017 8:39 PM
     * @since 1.0
     */
    public static boolean match(String fileName, String pattern, boolean isGreedy) {
        // add params' verify at 2016.08.28
        if (fileName == null && pattern == null) {
            return true;
        }
        if (fileName == null || pattern == null) {
            return false;
        }

        String[] subPatterns = pattern.split(PATTERN_SEP);
        for (int i = 0; i < subPatterns.length; i++) {
            if (match0(fileName, subPatterns[i], isGreedy)) {
                return true;
            }
        }

        return false;
    }

    public static boolean match(String fileName, String pattern) {
        return match(fileName, pattern, false);
    }

    /**
     * 判定str01[start01, start01+len], str02[start02, start02+len]在区间是否相同
     *
     * @param str01   字符串1
     * @param start01 字符串1开始比较的索引
     * @param str02   字符串2
     * @param start02 字符串2开始比较的索引
     * @param len     比较的子串的长度
     * @return boolean
     * @author Jerry.X.He
     * @date 5/5/2017 8:40 PM
     * @since 1.0
     */
    public static boolean equalsInRange(String str01, int start01, String str02, int start02, int len) {
        for (int i = 0; i < len; i++) {
            if (str01.charAt(start01 + i) != str02.charAt(start02 + i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断给定的字符串是否为空
     *
     * @param str 给定的字符串
     * @return boolean
     * @author Jerry.X.He
     * @date 5/5/2017 8:41 PM
     * @since 1.0
     */
    public static boolean isEmpty(String str) {
        return (str == null) || "".equals(str.trim());
    }

    /**
     * match的核心业务方法
     *
     * @param fileName 给定的文件名
     * @param pattern  给定的pattern
     * @param isGreedy 表示是否采用贪婪匹配的模式[也就是通配符'*'是否贪婪]
     * @return boolean
     * @author Jerry.X.He
     * @date 5/5/2017 8:42 PM
     * @since 1.0
     */
    private static boolean match0(String fileName, String pattern, boolean isGreedy) {
        pattern = preparePattern(pattern);

        int[] nextWildCards = newWildCards();
        initWildCards(pattern, nextWildCards);
        WildCardAndIdx wildCardAndIdx = new WildCardAndIdx();

        int fileNameIdx = 0, patternIdx = 0;
        while (hasNextWildCards(nextWildCards)) {
            // 如果没有匹配的pattern的字符串, 直接返回false
            if ((fileNameIdx < 0) || (fileNameIdx >= fileName.length())) {
                return false;
            }

            nextWildCard(pattern, nextWildCards, wildCardAndIdx);
            int len = wildCardAndIdx.pos - patternIdx;
            if (len != 0) {
                // 如果fileName不够长, 或者到下一个通配符之间的字符串和pattern不匹配, 直接返回false
                if (fileNameIdx + len >= fileName.length()) {
                    return false;
                }
                if (!equalsInRange(fileName, fileNameIdx, pattern, patternIdx, len)) {
                    return false;
                }
            }

            // 处理各个通配符的场景
            switch (wildCardAndIdx.wildCardIdx) {
                // 对于'?', 索引增加 : 精确匹配的字符串的长度+1
                case MATCH_ONE_IDX: {
                    fileNameIdx += (len + 1);
                    patternIdx += (len + 1);
                    break;
                }
                // 对于'*', 分为贪婪 和非贪婪进行处理
                case MATCH_MULTI_IDX: {
                    int curPos = wildCardAndIdx.pos;
                    peekNextWildCard(fileName, nextWildCards, wildCardAndIdx);
                    String strBetweenNextWildCard = null;
                    // 如果下一个字符也为通配符, 则strBetweenNextWildCard为"", isEmpty(strBetweenNextWildCard), 直接返回了true, 以及后面的fileNameIdx的更新造成影响  构成错误
                    // 所以 需要预处理pattern, 防止类似的情况发生 "**", "*?"
                    if (wildCardAndIdx.pos != -1) {
                        strBetweenNextWildCard = pattern.substring(curPos + 1, wildCardAndIdx.pos);
                    } else {
                        strBetweenNextWildCard = pattern.substring(curPos + 1);
                    }
                    // 处理pattern的最后一个字符为*的场景
                    if (isEmpty(strBetweenNextWildCard)) {
                        return true;
                    }

                    if (isGreedy) {
                        int prevFileNameIdx = fileNameIdx;
                        fileNameIdx = fileName.lastIndexOf(strBetweenNextWildCard);
                        // have no match with 'strBetweenNextWildCard' after fileNameIdx, cut off for next loop
                        // updated at 2016.08.29
                        if (fileNameIdx <= prevFileNameIdx) {
                            fileNameIdx = -1;
                        }
                    } else {
                        // if have no match with 'strBetweenNextWildCard' after fileNameIdx, 'fileNameIdx' will be '-1'
                        fileNameIdx = fileName.indexOf(strBetweenNextWildCard, fileNameIdx + 1);
                    }
                    patternIdx += (len + 1);
                    break;
                }
                // Other ??  can't be there in normal case
                default:
                    throw new RuntimeException("unsupported wildcard !");
            }
        }

        return equalsInRange(fileName, fileNameIdx, pattern, patternIdx, Math.min(fileName.length() - fileNameIdx, pattern.length() - patternIdx));
    }

    /**
     * 预处理pattern
     * 1. 防止类似的情况发生 "**", "*?"
     *
     * @param pattern 给定的pattern
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 5/5/2017 8:42 PM
     * @since 1.0
     */
    private static String preparePattern(String pattern) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0, len = pattern.length(); i < len; i++) {
            char ch = pattern.charAt(i);
            sb.append(ch);
            // '*XX'
            if (ch == MATCH_MULTI) {
                int nextI = i + 1;
                while ((nextI < len) && (contains(WILDCARDS, pattern.charAt(nextI)))) {
                    nextI++;
                }
                i = nextI - 1;
            }
        }

        return sb.toString();
    }

    /**
     * 创建一个通配符的索引的数组
     *
     * @return int[]
     * @author Jerry.X.He
     * @date 5/5/2017 8:43 PM
     * @since 1.0
     */
    private static int[] newWildCards() {
        return new int[WILDCARDS.length];
    }

    /**
     * 初始化通配符的位置
     *
     * @param pattern       给定的pattern
     * @param nextWildCards 通配符列表
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:43 PM
     * @since 1.0
     */
    private static void initWildCards(String pattern, int[] nextWildCards) {
        for (int i = 0; i < nextWildCards.length; i++) {
            nextWildCards[i] = pattern.indexOf(WILDCARDS[i]);
        }
    }

    /**
     * 判断是否还有下一个通配符
     *
     * @param nextWildCards 通配符列表
     * @return boolean
     * @author Jerry.X.He
     * @date 5/5/2017 8:43 PM
     * @since 1.0
     */
    private static boolean hasNextWildCards(int[] nextWildCards) {
        for (int i = 0; i < nextWildCards.length; i++) {
            if (nextWildCards[i] >= 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * 获取pattern中下一个通配符的位置, 并更新该通配符的下一个位置
     *
     * @param pattern        给定的pattern
     * @param nextWildCards  通配符列表
     * @param wildCardAndIdx 记录下一个通配符的信息
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:43 PM
     * @since 1.0
     */
    private static void nextWildCard(String pattern, int[] nextWildCards, WildCardAndIdx wildCardAndIdx) {
        peekNextWildCard(pattern, nextWildCards, wildCardAndIdx);

        nextWildCards[wildCardAndIdx.wildCardIdx] = pattern.indexOf(WILDCARDS[wildCardAndIdx.wildCardIdx], wildCardAndIdx.pos + 1);
    }

    /**
     * 获取pattern中下一个通配符的数据, 放到wildCardAndIdx中
     *
     * @param fileName       文件名称
     * @param nextWildCards  通配符列表
     * @param wildCardAndIdx 记录下一个通配符的信息
     * @return void
     * @author Jerry.X.He
     * @date 5/5/2017 8:44 PM
     * @since 1.0
     */
    private static void peekNextWildCard(String fileName, int[] nextWildCards, WildCardAndIdx wildCardAndIdx) {
        int next = getMinIdx(nextWildCards);
        wildCardAndIdx.wildCardIdx = next;

        if (next != -1) {
            wildCardAndIdx.pos = nextWildCards[next];
        } else {
            wildCardAndIdx.pos = -1;
        }
    }

    /**
     * 获取pattern中下一个的通配符的索引
     *
     * @param nextWildCards 通配符列表
     * @return int
     * @author Jerry.X.He
     * @date 5/5/2017 8:45 PM
     * @since 1.0
     */
    private static int getMinIdx(int[] nextWildCards) {
        int min = Integer.MAX_VALUE, idx = -1;
        for (int i = 0; i < nextWildCards.length; i++) {
            // '>= 0' for check valid
            if ((nextWildCards[i] >= 0) && (nextWildCards[i] < min)) {
                idx = i;
                min = nextWildCards[i];
            }
        }

        return idx;
    }

    /**
     * 判断给定的字符数组中是否包含给定的字符
     *
     * @param chars 给定的char列表
     * @param ch    需要寻找的的char
     * @return boolean
     * @author Jerry.X.He
     * @date 5/5/2017 8:46 PM
     * @since 1.0
     */
    private static boolean contains(char[] chars, char ch) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ch) {
                return true;
            }
        }

        return false;
    }

    /**
     * 通配符的索引, 以及其当前位置
     *
     * @author Jerry.X.He <970655147@qq.com>
     * @version 1.0
     * @date 5/5/2017 8:46 PM
     */
    private static class WildCardAndIdx {
        /**
         * 通配符的索引
         */
        public int wildCardIdx;
        /**
         * 在context中该通配符的位置[会改变]
         */
        public int pos;

        public String toString() {
            return WILDCARDS[wildCardIdx] + " -> " + pos;
        }
    }

}
