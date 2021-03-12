package week1;

import java.util.Arrays;

public class URL {
    
    /**
     * 题目要求java要用字符数组来实现，而且假设数组长度是永远够用的
     */
    public String replaceSpaces(String S, int length) {
        char[] arr = S.toCharArray();
        int m;
        for (m = arr.length - 1; m >= 0 && arr[m] == ' '; --m) {}
        
        if (m < 0) {
            char[] chars = new char[length * 3];
            for (int i = 0; i < length * 3;) {
                chars[i++] = '%';
                chars[i++] = '2';
                chars[i++] = '0';
            }
            return new String(chars);
        }
        
        char[] chars = new char[m * 3];
        int i = 0;
        for (int n = 0; n <= m; ++n) {
            char c = arr[n];
            if (c == ' ') {
                chars[i++] = '%';
                chars[i++] = '2';
                chars[i++] = '0';
            } else {
                chars[i++] = c;
            }
        }
        return new String(Arrays.copyOfRange(chars, 0, i));
    }
}