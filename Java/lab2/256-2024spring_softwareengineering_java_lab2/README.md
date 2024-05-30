# Lab02

## Problem 1: PrintMatrix

Given a matrix, how to unfold it clockwise from the outside to the inside?
Simply put, it means printing each number of this two-dimensional array clockwise from the outside to the inside. **(Separate each number with a space)**

```java
public class Solution {
    public void printMatrix(int [][] mat) {
        /**
         * example:
         *  input:
         *      1 2 3 4
         *      10 11 12 5
         *      9 8 7 6
         *  print:
         *      1 2 3 4 5 6 7 8 9 10 11 12
         * **/
        /** WRITE YOUR CODE HERE **/
    }
}
```

## Problem 2: Hex2Bin

Write a program called hex2Bin that prompts user for a hexadecimal string (lower-case) and returns its equivalent binary string. The output shall look like: **(Separate each sub_binary string with a space)**

```
Enter a Hexadecimal string: 1abc
The equivalent binary for hexadecimal "1abc" is: 0001 1010 1011 1100
```

```java
public class Solution{
    public String hex2Bin(String num){
        /** WRITE YOUR CODE HERE **/
        return null; //Delete this line
    }
}
```
### Hint

Use an array of 16 Strings containing binary strings corresponding to hexadecimal number 0-9A-F (or a-f), as follows:

```java
final String[] HEX_BITS = {"0000", "0001", "0010", "0011",
                    "0100", "0101", "0110", "0111",
                    "1000", "1001", "1010", "1011",
                    "1100", "1101", "1110", "1111"};
```

## Problem 3: String Compression

For a given string, compress the same consecutive letters together, such as aaaabba into a4b2a1. If the compressed length is **smaller**, return the compressed result, otherwise return the source string.***(If the lengths are the same, return the source string.)***

***If the given string is "", return "".***

```java
public class Solution {
    public String strCompression (String str){
        //Returns the compressed string
        /** WRITE YOUR CODE HERE **/
        return str;
    }
}
```

## Problem 4: String Matching

Given two strings str1 and str2, it is required to return the sequence when str2 appears for the first time in str1. If it does not appear, -1 is returned.

For example : str1 = "aabbcc" str2 = "bc", return 3.

***str1 and str2 are not empty.***

```java
public class Solution {
    public int strMatching (String str1, String str2){
        //Returns the index
        /** WRITE YOUR CODE HERE **/
        return -1;
    }
}
```

## Problem 5: ky,zhyz


David is interested with the Chinese meme **"ky,zhyz"**, which are phrases with the initial letters "ky,zhyz" in pinyin. However, David is not good at Chinese, so please help him to check and find the words having the form of **"ky,zhyz"** or **"kyzhyz"**.

We have implemented function `getPinyin`, which takes in a Chinese character as char and returns a string of its pinyin. When input is not a Chinese character, it will return empty string "". You can also freely use the functions in pinyin4j.

Please implement function kyzhyz, which takes in a list of Chinese string and returns the "ky,zhyz" or "kyzhyz" part of it. ***One string in the input will only contain at most one "ky,zhyz" or "kyzhyz" phrase.***
```java
public class Solution {
    public List<String> kyzhyz(List<String> words){
        //Return the Chinese phrase with the format "ky,zhyz" or "kyzhyz" in the input.
        /**
         * input:
         *      {"坎勇，最会游走！","科比，这很硬肘","肉包：狂羊，真狠一撞","狂野震撼亚洲！！","不可以。总会有这样的。"，,"堪忧，最后一"}
         * return:
         *      {"坎勇，最会游走","狂羊，真狠一撞","狂野震撼亚洲"}
         * */
        /** WRITE YOUR CODE HERE **/
        return null;//Delete this line
    }
}
```