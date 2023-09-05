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
