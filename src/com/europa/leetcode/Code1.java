package com.europa.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 表示数值的字符串
 * 想出最复杂的构造、以及遗漏的分支情况
 * "   +323.423e-121313   "
 * 对于上面这个数，有以下情况
 * 0. 空格
 * 1. 空格后正负号
 * 2. 正负号后数字
 * 3. 数字后小数点
 * 4. 小数点后数字
 * 5. 数字后幂符号e|E
 * 6. 幂符号后正负号
 * 7. 正负号后数字
 * 8. 数字后空格
 *
 * 则对于上述的状态，要找出其下一个字符运行的状态，否则就不是数值。
 * 以states数组表示每个位置允许的状态，如states[0]即为首字母，允许是空格、符号、数字或者小数点，
 * 如是空格，则其下一个字符状态仍与当前字母状态相同，为states[0]。
 * 如是符号，则其下一个字符必须为数字或者小数点，为states[1]。
 * 如是数字，则下一个字符必须为数字、小数点、幂符号、或者空格，定义为states[2]。
 * 依此类推。
 *
 * 注意：状态下允许多种字符，但同一种字符出现在不同的位置时可能属于不同的状态。如
 * 小数点出现在首字符或者紧跟在正负号后第二个字符时，其后面必须得接数字，如果没有数字，则不符合规则，这个字符串不是数值。
 * 但是如果小数点是出现在正常整数数字后，则其结束时可以认定为数值。
 *
 * 技巧：先定义规则内符合条件的最复杂的字符串，然后通过查漏补缺补上剩余的分支状态。
 * 如这题states[7]与states[8]两个状态为后续补充的分支，分别对应区分 ["+."] ["1e+"]这种情况
 * @author 山雨光云
 * @since 2023/9/01 17:02
 **/
public class Code1 {

    public static void main(String[] args) {
        Code1 instance = new Code1();
        boolean number = instance.isNumber("-1E-16");
        System.out.println(number);
    }

    public boolean isNumber(String s) {
        Map[] states = {
                // 表示允许的状态及其下一个字符合法的话要跳到的状态下标 如' '就是状态0 表空格 其下一个字符必须跟空格、符号、数字、或者小数点才合法。
                new HashMap<Character, Integer>() {{put(' ', 0);put('s', 1);put('d', 2);put('.', 7);}},
                new HashMap<Character, Integer>() {{put('d', 2);put('.', 7);}},
                new HashMap<Character, Integer>() {{put('d', 2);put('.', 3);put('e', 4);put(' ', 6);}},
                new HashMap<Character, Integer>() {{put('d', 3);put('e', 4);put(' ', 6);}},
                new HashMap<Character, Integer>() {{put('s', 8);put('d', 5);}},
                new HashMap<Character, Integer>() {{put('d', 5);put(' ', 6);}},
                new HashMap<Character, Integer>() {{put(' ', 6);}},
                new HashMap<Character, Integer>() {{put('d', 3);}},
                new HashMap<Character, Integer>() {{put('d', 5);}},
        };

        char[] arrays = s.toCharArray();
        int cur = 0;
        char chr;
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] >= '0' && arrays[i] <= '9') {
                chr = 'd';
            }
            else if (arrays[i] == '+' || arrays[i] == '-') {
                chr = 's';
            }
            else if (arrays[i] == ' ' || arrays[i] == '.') {
                chr = arrays[i];
            }
            else if (arrays[i] == 'e' || arrays[i] == 'E') {
                chr = 'e';
            } else {
                chr = '?';
            }

            if (!states[cur].containsKey(chr)) {
                return false;
            }
            cur = (int)states[cur].get(chr);
        }
        return cur == 2 || cur == 3 || cur == 5 || cur == 6;
    }
}
