/**
 * file name : WordSeprator.java
 * created at : 3:43:13 PM Mar 22, 2016
 * created by 970655147
 */

package com.hx.common.str;

import com.hx.common.interf.seprator.StringSeprator;
import com.hx.common.util.AssertUtils;
import com.hx.common.util.InnerTools;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 分割字符串的工具
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 4/15/2017 3:01 PM
 */
public final class WordsSeprator implements StringSeprator {

    /**
     * 给定的字符串
     */
    private String str;
    /**
     * 分隔符与下一个位置的映射
     */
    private Map<String, Integer> sepToPos;
    /**
     * 需要跳过的符号对[这两个符号对之间的内容, 不做处理]
     */
    private Map<String, String> escapeMap;
    /**
     * ,
     * , 以及其
     *
     */
    /**
     * 当前遍历到的索引
     */
    private int idx;
    /**
     * 下一个待取的字符串[并不一定是next()返回的结果, 可能拿到分隔符]
     */
    private String next;
    /**
     * 调用next()方法之后, 缓存的next()结果
     */
    private String current;
    /**
     * 调用next()方法之后, 缓存的next()的位置
     */
    private int currentStartIdx;
    /**
     * 调用next()方法之后, 缓存的上上一个next()的结果
     */
    private String prev;
    /**
     * 调用next()方法之后, 缓存的上上一个next()的位置
     */
    private int prevStartIdx;
    /**
     * 是否获取分隔符
     */
    private boolean gotSep;
    /**
     * 是否获取空白字符串
     */
    private boolean gotEmptyStr;
    /**
     * 下一"回合"是否是获取分隔符的回合[如果是false, 则表示当前回合获取分隔符]
     */
    private boolean isNextSep;
    /**
     * 缓存的调用next()之后的上一个分隔符
     */
    private String lastSep;

    /**
     * 初始化
     *
     * @param str         给定的字符串
     * @param seps        分隔符列表
     * @param escapeMap   需要跳过的符号对
     * @param gotSep      是否获取分隔符
     * @param gotEmptyStr 是否获取空字符串
     * @since 1.0
     */
    public WordsSeprator(String str, Set<String> seps, Map<String, String> escapeMap,
                         boolean gotSep, boolean gotEmptyStr) {
        AssertUtils.assert0(str != null, "str can't be null !");

        this.str = str;
        this.escapeMap = escapeMap;
        this.gotSep = gotSep;
        this.gotEmptyStr = gotEmptyStr;
        this.isNextSep = false;
        this.lastSep = null;
        // update at 2016.04.21
        // update 'Map<String, Integer> sepToPos' => 'Set<String> seps', construct 'sepToPos' by this Constructor
        // incase of 'str' startsWith 'sep'
        // keep input order for some confict cond, likes '?' & '??'
        this.sepToPos = new LinkedHashMap<>();
        if (seps != null) {
            for (String sep : seps) {
                sepToPos.put(sep, -1);
            }
        }

        // freshAll, got every 'sep''s right position!
        freshAll();
    }

    public WordsSeprator(String str, Set<String> seps, Map<String, String> escapeMap, boolean gotSep) {
        this(str, seps, escapeMap, gotSep, false);
    }

    public WordsSeprator(String str, Set<String> seps, Map<String, String> escapeMap) {
        this(str, seps, escapeMap, true, false);
    }

    @Override
    public boolean hasNext() {
        if (next != null) {
            return true;
        }

        // true 				&& 	true
        if ((idx >= str.length()) && (!(isNextSep && (lastSep != null)))) {
            return false;
        }
        if (gotSep) {
            isNextSep = !isNextSep;
            boolean isNowSep = (!isNextSep);
            if (isNowSep) {
                next = lastSep;
                return hasNext();
            }
        }

        String sep = minSep();
        int pos = getPosBySep(sep);

        // incase of '?', '??' [choice which one is decided by 'InputOrder'[see 'minsep'] ], fixed at 2016.09.30
        while ((pos >= 0) && (pos < idx)) {
            fresh(sep);
            sep = minSep();
            pos = getPosBySep(sep);
        }

        String res = null;
        if (pos < 0) {
            res = str.substring(idx);
            idx = str.length();
            lastSep = null;
        } else {
            fresh(sep);
            res = str.substring(idx, pos);
            idx = pos + sep.length();
            lastSep = sep;
        }
        // because 'Constants' denpend on 'WordsSeprator', and 'Tools' denpend on 'Constants'
        // so use 'InnerTools.isEmpty' instead of 'Tools.isEmpty'[cause circle denpency] in case of 'InitException'
        // if 'res.trim' in 'Constants.EMPTY_CONDITIONS', skip it ! [may cause 'some space' loss]
        if ((!gotEmptyStr) && InnerTools.isEmpty(res)) {
            return hasNext();
        }
        next = res;
        return hasNext();
    }

    @Override
    public String next() {
        prev = current;
        if (!hasNext()) {
            return null;
        }

        prevStartIdx = currentStartIdx;
        // lastSep represents there are no more next(), except current next
        currentStartIdx = idx - next.length() - ((lastSep != null) ? lastSep.length() : 0);
        if (gotSep && (!isNextSep)) {
            currentStartIdx += next.length();
        }
        current = next;
        String res = next;
        next = null;
        return res;
    }

    public String current() {
        if (!hasNext()) ;
        return current;
    }

    @Override
    public int currentStartIdx() {
        if (!hasNext()) ;
        return currentStartIdx;
    }

    @Override
    public String prev() {
        if (!hasNext()) ;
        return prev;
    }

    @Override
    public int prevStartIdx() {
        if (!hasNext()) ;
        return prevStartIdx;
    }

    @Override
    public int length() {
        return str.length();
    }

    @Override
    public String seek() {
        if (!hasNext()) {
            return null;
        }
        return next;
    }

    @Override
    public String rest() {
        if (!hasNext()) ;
        return str.substring(currentStartIdx + current.length());
    }

    @Override
    public String currentAndRest() {
        if (!hasNext()) ;
        return str.substring(currentStartIdx);
    }

    @Override
    public String rest(int pos) {
        if ((pos < 0) || (pos >= str.length())) {
            return null;
        }
        return str.substring(pos);
    }

    @Override
    public void remove() {
        throw new RuntimeException("unsupportedOperation !");
    }

    // ----------------- 辅助方法 -----------------------

    /**
     * fresh所有的分隔符的位置
     *
     * @return void
     * @author Jerry.X.He
     * @date 4/15/2017 2:59 PM
     * @since 1.0
     */
    private void freshAll() {
        for (Entry<String, Integer> entry : sepToPos.entrySet()) {
            fresh(entry.getKey());
        }
    }

    /**
     * fresh给定的分隔符的位置
     *
     * @return void
     * @author Jerry.X.He
     * @date 4/15/2017 2:59 PM
     * @since 1.0
     */
    private void fresh(String sep) {
        Integer pos = sepToPos.get(sep);
        if (pos != null) {
            sepToPos.put(sep, indexOf(str, sep, pos + 1));
        }
    }

    /**
     * 从str的start处开始获取下一个sep的位置[需要跳过escapeMap对应的条目]
     *
     * @return void
     * @author Jerry.X.He
     * @date 4/15/2017 2:59 PM
     * @since 1.0
     */
    private Integer indexOf(String str, String sep, int start) {
        int idx = start;
        whileLoop:
        while (idx < str.length()) {
            if (escapeMap != null) {
                for (Entry<String, String> entry : escapeMap.entrySet()) {
                    if (str.startsWith(entry.getKey(), idx)) {
//                        idx = str.indexOf(entry.getValue(), idx + entry.getKey().length());
                        idx = indexOfNonTransfer(str, entry.getValue(), idx + entry.getKey().length());
                        if (idx < 0) {
                            break whileLoop;
                        }
                        idx += entry.getValue().length();
                        continue whileLoop;
                    }
                }
            }

            if (str.startsWith(sep, idx)) {
                return idx;
            }
            idx++;
        }

        return -1;
    }

    /**
     * 获取str中从start开始下一个target的位置
     * 注意, 如果给定的位置前面是转义字符 "\", 则跳过 该匹配
     *
     * @param str    给定的字符串
     * @param target 目标字符串
     * @param start  开始的位置
     * @return int
     * @author Jerry.X.He
     * @date 5/6/2017 5:57 PM
     * @since 1.0
     */
    private int indexOfNonTransfer(String str, String target, int start) {
        for (int i = start, len = str.length(); i < len; i++) {
            if (str.startsWith(target, i) && ((i - 1 < 0) || (str.charAt(i - 1)) != '\\')) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 获取下一个分隔符
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 4/15/2017 3:00 PM
     * @since 1.0
     */
    private String minSep() {
        int minPos = Integer.MAX_VALUE;
        String minSep = null;
        for (Entry<String, Integer> entry : sepToPos.entrySet()) {
            if ((entry.getValue() >= 0) && (entry.getValue() < minPos)) {
                minPos = entry.getValue();
                minSep = entry.getKey();
            }
        }
        return minSep;
    }

    /**
     * 获取下一个分隔符的位置
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 4/15/2017 3:00 PM
     * @since 1.0
     */
    private int minPos() {
        String minSep = minSep();
        return getPosBySep(minSep);
    }

    /**
     * 获取给定的分隔符的下一个位置
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 4/15/2017 3:00 PM
     * @since 1.0
     */
    private int getPosBySep(String sep) {
        return (sep == null) ? -1 : sepToPos.get(sep);
    }

}
