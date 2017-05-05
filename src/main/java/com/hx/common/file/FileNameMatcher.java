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
     * ��֧�ֵ�ͨ���, '?' ���Ա�ʾһ�������ַ�, '*' ��ʾ����������ַ�[�����ƥ����isGreedy����Լ��]
     * ��Ϊ��ͨ��������, û��'?', '*' ���ļ���, ��������û��д'?', '*'����ı�ʾ[��� Ҫд�Ļ�, ģ��ת���][������wildCard��ʱ��, ��������ת���'?', '*', ��ƥ���ʱ��, ��'\?', '\*'�滻Ϊ��ʵ���ַ�����ʾ��'?', '*' ]
     * һ��pattern�и�����pattern�ķָ���, ������pattern֮��Ĺ�ϵΪ "��·��"
     * ���� ��û��Լ��"��·��"�ĳ���[���ȼ����� �ᵼ���ܶ�����], ������ʹ��match����ʵ�ְ�
     */
    /**
     * ƥ��һ���ַ���ͨ���
     */
    public static final Character MATCH_ONE = '?';
    /**
     * ƥ�����ַ���ͨ���
     */
    public static final Character MATCH_MULTI = '*';
    /**
     * ͨ����б�
     */
    public static final char[] WILDCARDS = new char[]{MATCH_ONE, MATCH_MULTI};
    /**
     * ?������
     */
    public static final int MATCH_ONE_IDX = 0;
    /**
     * *������
     */
    public static final int MATCH_MULTI_IDX = 1;
    /**
     * �ָ���pattern�ķ���
     */
    public static final String PATTERN_SEP = "\\|";

    /**
     * �жϸ�����fileName�Ƿ�ƥ�������pattern
     *
     * @param fileName �������ļ���
     * @param pattern  ������pattern
     * @param isGreedy ��ʾ�Ƿ����̰��ƥ���ģʽ[Ҳ����ͨ���'*'�Ƿ�̰��]
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
     * �ж�str01[start01, start01+len], str02[start02, start02+len]�������Ƿ���ͬ
     *
     * @param str01   �ַ���1
     * @param start01 �ַ���1��ʼ�Ƚϵ�����
     * @param str02   �ַ���2
     * @param start02 �ַ���2��ʼ�Ƚϵ�����
     * @param len     �Ƚϵ��Ӵ��ĳ���
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
     * �жϸ������ַ����Ƿ�Ϊ��
     *
     * @param str �������ַ���
     * @return boolean
     * @author Jerry.X.He
     * @date 5/5/2017 8:41 PM
     * @since 1.0
     */
    public static boolean isEmpty(String str) {
        return (str == null) || "".equals(str.trim());
    }

    /**
     * match�ĺ���ҵ�񷽷�
     *
     * @param fileName �������ļ���
     * @param pattern  ������pattern
     * @param isGreedy ��ʾ�Ƿ����̰��ƥ���ģʽ[Ҳ����ͨ���'*'�Ƿ�̰��]
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
            // ���û��ƥ���pattern���ַ���, ֱ�ӷ���false
            if ((fileNameIdx < 0) || (fileNameIdx >= fileName.length())) {
                return false;
            }

            nextWildCard(pattern, nextWildCards, wildCardAndIdx);
            int len = wildCardAndIdx.pos - patternIdx;
            if (len != 0) {
                // ���fileName������, ���ߵ���һ��ͨ���֮����ַ�����pattern��ƥ��, ֱ�ӷ���false
                if (fileNameIdx + len >= fileName.length()) {
                    return false;
                }
                if (!equalsInRange(fileName, fileNameIdx, pattern, patternIdx, len)) {
                    return false;
                }
            }

            // �������ͨ����ĳ���
            switch (wildCardAndIdx.wildCardIdx) {
                // ����'?', �������� : ��ȷƥ����ַ����ĳ���+1
                case MATCH_ONE_IDX: {
                    fileNameIdx += (len + 1);
                    patternIdx += (len + 1);
                    break;
                }
                // ����'*', ��Ϊ̰�� �ͷ�̰�����д���
                case MATCH_MULTI_IDX: {
                    int curPos = wildCardAndIdx.pos;
                    peekNextWildCard(fileName, nextWildCards, wildCardAndIdx);
                    String strBetweenNextWildCard = null;
                    // �����һ���ַ�ҲΪͨ���, ��strBetweenNextWildCardΪ"", isEmpty(strBetweenNextWildCard), ֱ�ӷ�����true, �Լ������fileNameIdx�ĸ������Ӱ��  ���ɴ���
                    // ���� ��ҪԤ����pattern, ��ֹ���Ƶ�������� "**", "*?"
                    if (wildCardAndIdx.pos != -1) {
                        strBetweenNextWildCard = pattern.substring(curPos + 1, wildCardAndIdx.pos);
                    } else {
                        strBetweenNextWildCard = pattern.substring(curPos + 1);
                    }
                    // ����pattern�����һ���ַ�Ϊ*�ĳ���
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
     * Ԥ����pattern
     * 1. ��ֹ���Ƶ�������� "**", "*?"
     *
     * @param pattern ������pattern
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
     * ����һ��ͨ���������������
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
     * ��ʼ��ͨ�����λ��
     *
     * @param pattern       ������pattern
     * @param nextWildCards ͨ����б�
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
     * �ж��Ƿ�����һ��ͨ���
     *
     * @param nextWildCards ͨ����б�
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
     * ��ȡpattern����һ��ͨ�����λ��, �����¸�ͨ�������һ��λ��
     *
     * @param pattern        ������pattern
     * @param nextWildCards  ͨ����б�
     * @param wildCardAndIdx ��¼��һ��ͨ�������Ϣ
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
     * ��ȡpattern����һ��ͨ���������, �ŵ�wildCardAndIdx��
     *
     * @param fileName       �ļ�����
     * @param nextWildCards  ͨ����б�
     * @param wildCardAndIdx ��¼��һ��ͨ�������Ϣ
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
     * ��ȡpattern����һ����ͨ���������
     *
     * @param nextWildCards ͨ����б�
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
     * �жϸ������ַ��������Ƿ�����������ַ�
     *
     * @param chars ������char�б�
     * @param ch    ��ҪѰ�ҵĵ�char
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
     * ͨ���������, �Լ��䵱ǰλ��
     *
     * @author Jerry.X.He <970655147@qq.com>
     * @version 1.0
     * @date 5/5/2017 8:46 PM
     */
    private static class WildCardAndIdx {
        /**
         * ͨ���������
         */
        public int wildCardIdx;
        /**
         * ��context�и�ͨ�����λ��[��ı�]
         */
        public int pos;

        public String toString() {
            return WILDCARDS[wildCardIdx] + " -> " + pos;
        }
    }

}
