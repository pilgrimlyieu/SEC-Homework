import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

    public void printMatrix(int[][] mat) {
        /**
         * example:
         *  input:
         *      1 2 3 4
         *      10 11 12 5
         *      9 8 7 6
         *  print:
         *      1 2 3 4 5 6 7 8 9 10 11 12
         * **/
        String result = "";
        int startRow = 0, startCol = 0, endRow = mat.length - 1, endCol = mat[0].length - 1;
        while (startRow <= endRow && startCol <= endCol) {
            for (int i = startCol; i <= endCol; i++) {
                result += mat[startRow][i] + " ";
            }
            startRow++;
            for (int i = startRow; i <= endRow; i++) {
                result += mat[i][endCol] + " ";
            }
            endCol--;
            if (startRow <= endRow) {
                for (int i = endCol; i >= startCol; i--) {
                    result += mat[endRow][i] + " ";
                }
                endRow--;
            }
            if (startCol <= endCol) {
                for (int i = endRow; i >= startRow; i--) {
                    result += mat[i][startCol] + " ";
                }
                startCol++;
            }
        }
        System.out.println(result.trim());
    }

    public String hex2Bin(String num) {
        final String[] HEX_BITS = { "0000", "0001", "0010", "0011",
                                    "0100", "0101", "0110", "0111",
                                    "1000", "1001", "1010", "1011",
                                    "1100", "1101", "1110", "1111" };
        String result = "";
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            if (c >= '0' && c <= '9') {
                result += HEX_BITS[c - '0'];
            } else if (c >= 'a' && c <= 'f') {
                result += HEX_BITS[c - 'a' + 10];
            }
            if (i < num.length() - 1) {
                result += " ";
            }
        }
        return result;
    }

    public String strCompression(String str){
        //Returns the compressed string
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int count = 1;
            while (i + 1 < str.length() && str.charAt(i + 1) == c) {
                i++;
                count++;
            }
            result += c;
            result += String.valueOf(count);
        }
        return (str.length() > result.length()) ? result : str;
    }


    public int strMatching (String str1, String str2){
        //Returns the index
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) == str2.charAt(0)) {
                boolean match = true;
                for (int j = 1; j < str2.length(); j++) {
                    if (i + j >= str1.length() || str1.charAt(i + j) != str2.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Don't change any code about getPinyin function
     * example:
     *  input: '坎'
     *  return: "kan"
     * */
    private String getPinyin(char character){
        String pinyinStr = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        if (character > 128) {
            try {
                String[] strings=PinyinHelper.toHanyuPinyinStringArray(character, defaultFormat);
                if (strings.length==0){
                    return "";
                }
                pinyinStr = strings[0];
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        }else{
            return "";
        }
        return pinyinStr;
    }

    public List<String> kyzhyz(List<String> words){
        //Return the Chinese phrase with the format "ky,zhyz" or "kyzhyz" in the input.
        /**
         * input:
         *      {"坎勇，最会游走！","科比，这很硬肘","肉包：狂羊，真狠一撞","狂野震撼亚洲！！","不可以。总会有这样的。"，,"堪忧，最后一"}
         * return:
         *      {"坎勇，最会游走","狂羊，真狠一撞","狂野震撼亚洲"}
         * */
        // 绷，引了正则包，是暗示用正则解法是吧，可是课上也没讲
        List<String> result = new ArrayList<>();
        for (String word : words) {
            String pinyin = "";
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (getPinyin(c).equals("")) {
                    pinyin += c;
                } else {
                    pinyin += getPinyin(c).charAt(0);
                }
            }
            Pattern pattern1 = Pattern.compile("kyzhyz");
            Pattern pattern2 = Pattern.compile("ky，zhyz");
            Matcher find1 = pattern1.matcher(pinyin);
            Matcher find2 = pattern2.matcher(pinyin);
            if (find1.find()) {
                result.add(word.substring(find1.start(), find1.end()));
            }
            else if (find2.find()) {
                result.add(word.substring(find2.start(), find2.end()));
            }
        }
        return result;
        // 一开始没用正则做的，结果错了，因为我误以为要单独成句，即 ky 前不能有字母（但不知为何不认为 zhyz 后也不能有）
        // 然后错了，找不到错误，又看到引了正则包就决定用正则重写，结果就过了
        // 原因是重写时我忘了这茬了，不然 pattern 就会写 "(?<![a-z])kyzhyz" 了
        // 综上，什么「」题目：input 格式错误 + 没说清楚细节 + 夹带私货
    }
}
