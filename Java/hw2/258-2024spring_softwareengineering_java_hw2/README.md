# Homework02

## Problem 1: String Combination

Given a long string and several short strings, determine whether the long string is composed of these short strings. Short strings can be concatenated in any order, some may appear multiple times, and some may not appear at all. **For simplicity, we stipulate that any two short strings do not have the same letters.** (You can think about why this is easier)

```java
public class Solution {  
    public boolean strCombination(String longStr, String[] shortStrs){
        /**
         * example:
         *  input:
         *      123equals123   [123, equals]
         *  output:
         *      true
         * **/
        /** WRITE YOUR CODE HERE **/
        return true; //Delete this line
    }
}
```

## Problem 2: CardMaster

John likes playing poker but doesn't like playing cards. Please help him sort his cards in order of `2AKQJT9876543`  from top to bottom. **(The T here is actually the 10 of playing cards)**.
Please write a function called cardMaster to sort the input playing cards **(String)** and then return the sorted playing cards **(String)**.

```java
public class Solution {  
    public String cardMaster(String cards) {
        // Return the sorted playing cards.
        /**
         * example:
         *  input:
         *      36TA
         *  output:
         *      AT63
         * **/
        /** WRITE YOUR CODE HERE **/
        return null; //Delete this line
    }
}
```

## Problem 3: Tensor Unfolding

Tensor calculating is the critical part in machine learning. To simplify tensor calculation, we need to unfold tensor first.

Tensor can be intuitively explained as a 'pile' of matrixes(a first order tensor is vector, a second tensor is a matrix, a third order tensor is multiple matrixes, just imagine several matrixes pile up in another dimension!

There several modals to unfold a tensor, in this task you'll just consider unfolding a third order matrix in the following way:

Given a m×n×z tensor T, the unfolded matrix should be like: [T(:,:,1), T(:,:,2), ... ,T(:,:,z)]. Just concatenate each row of each matrix in tensor!


```java
public class Solution {
    public int[][] TensorUnfold(int [][][] tensor) {
        /**
         * example:
         *  input:
         *   [
         *    [[1, 2, 3],
         *     [4, 5, 6]],
         *    [[7, 8, 9],
         *     [10, 11, 12]]
         *   ]
         *  output:
         *   [[1, 2, 3, 7, 8, 9],
         *    [4, 5, 6, 10, 11, 12]]
         * **/
        /** WRITE YOUR CODE HERE **/
        return null; //Delete this line
    }
}
```

## Problem 4: Match IP Address and subnet

IP and subnet are two critical components of computer network.

IP address is a 32-bit integer, usually split into four 8-bit binaries and represent in decimal form and concatenated by `'.'`(e.g. `192.168.1.0`). IP address is composed of two parts: network address and host address. Network address is the prefix of the IP address, whose length is determined by a integer subnet mask.

Hence, a subnet can be seen as a network address and a subnet mask(e.g. `192.168.1.0/24`), before slash is the network address, after slash is the subnet mask. When presenting an subnet, we only care about the network address but ignore the host address.


Only when the prefix of an IP address matches the subnet's network address, the IP belongs to the subnet.

Given an IP address and a subnet, your task is to check whether the IP address belongs to the subnet.

For better understanding, consider an example. Suppose two IP `192.168.1.100`, `192.168.3.100` and a subnet `192.168.2.0/23` are given, IP `192.168.1.100` is not included in the subnet, because its prefix is `192.168.0`. IP `192.168.3.100` belongs to the subnet, because its prefix is `192.168.2`(the mask is 23, hence we extract the first 23 bits in the IP, which is `192.168.2`)

```java
public class Solution {  
    public boolean IPmatch(String IP, String subnet) {
        /** WRITE YOUR CODE HERE **/
        return false; //Delete this line
    }
}
```

## Problem 5: Quordle

Jane loves playing **[Daily Quordle](https://www.merriam-webster.com/games/quordle/#/)**, which is a game guessing words made up of 5 letters. In the game, every time you guess a word, you can get hints whether a letter is in a word or not. If a letter is on the right spot, you will get hint too.

Your task is to implement function `quordleCheat`, which take in three lists. List `have` contains letters in the word. List `haveNot` contains letters not in the word. List `rightIn` contains letters and their position(start from 0) in the word. `quordleCheat` should return all the words of 5 letters that meets the conditions. You are provided a dictionary [words.txt](words.txt) which contains all the words. There is no need of change the case of words.

```java
public class Solution {    
    public List<String> quordleCheat(List<Character> have, List<Character> haveNot, Map<Integer,Character> rightIn) {
        /**
         * example:
         *  input:
         *   have: [a, f], haveNot: [w, i], rightIn: {(4:r)}
         *  output:
         *   [afear, after, facer, fader, fager, faker, farer, fator, favor, fchar, feuar, flear, Rafer, Safar, safer, sofar, unfar]
         * **/
        /** WRITE YOUR CODE HERE **/
        return null;//Delete this line
    }
}
```
### Hint
You need to read every word in the words.txt file for judgment, and you may need to use the *FileReader*, *BufferedReader* or other Classes. 

