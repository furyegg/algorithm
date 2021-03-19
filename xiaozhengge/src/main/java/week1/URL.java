package week1;

import java.util.Arrays;

public class URL {
    /**
     * 题目要求java要用字符数组来实现，而且假设数组长度是永远够用的
     */
    public String replaceSpaces(String S, int length) {
        char[] arr = S.toCharArray();
        char[] res = new char[length * 3];
    
        int i = 0;
        int count = 0;
        for (int n = 0; n < length; ++n) {
            char c = arr[n];
            if (c == ' ') {
                res[i++] = '%';
                res[i++] = '2';
                res[i++] = '0';
                count += 3;
            } else {
                res[i++] = c;
                ++count;
            }
        }
        return new String(Arrays.copyOfRange(res, 0, count));
    }
}