import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public boolean strCombination(String longStr, String[] shortStrs) {
        /**
         * example:
         *  input:
         *      123equals123   [123, equals]
         *  output:
         *      true
         * **/
        String check_str = longStr;
        for (String str : shortStrs) {
            int index = check_str.indexOf(str);
            while (index != -1) {
                check_str = check_str.substring(0, index) + check_str.substring(index + str.length());
                index = check_str.indexOf(str);
            }
        }
        return check_str.length() == 0;
    }

    public String cardMaster(String cards) {
        // Return the sorted playing cards.
        /**
         * example:
         *  input:
         *      36TA
         *  output:
         *      AT63
         * **/
        char[] cardArray = cards.toCharArray();
        for (int i = 0; i < cardArray.length; i++) {
            for (int j = i + 1; j < cardArray.length; j++) {
                if (sortCard(cardArray[i], cardArray[j]) < 0) {
                    char temp = cardArray[i];
                    cardArray[i] = cardArray[j];
                    cardArray[j] = temp;
                }
            }
        }
        return new String(cardArray);
    }

    int sortCard(char a, char b) {
        Map<Character, Integer> cardMap = new HashMap<>();
        cardMap.put('3', 1);
        cardMap.put('4', 2);
        cardMap.put('5', 3);
        cardMap.put('6', 4);
        cardMap.put('7', 5);
        cardMap.put('8', 6);
        cardMap.put('9', 7);
        cardMap.put('T', 8);
        cardMap.put('J', 9);
        cardMap.put('Q', 10);
        cardMap.put('K', 11);
        cardMap.put('A', 12);
        cardMap.put('2', 13);
        return cardMap.get(a) - cardMap.get(b);
    }

    public int[][] TensorUnfold(int[][][] tensor) {
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
        int[][] result = new int[tensor[0].length][tensor[0][0].length * tensor.length];
        for (int matrix = 0; matrix < tensor.length; matrix++) {
            for (int vector = 0; vector < tensor[matrix].length; vector++) {
                for (int number = 0; number < tensor[matrix][vector].length; number++) {
                    result[vector][matrix * tensor[matrix][vector].length + number] = tensor[matrix][vector][number];
                }
            }
        }
        return result;
    }

    public boolean IPmatch(String IP, String subnet) {
        String[] ip = IP.split("\\.");
        int maskN = Integer.parseInt(subnet.split("/")[1]);
        String[] subnetIP = subnet.split("/")[0].split("\\.");
        for (int i = 0; i < 4; i++) {
            int mask = (maskN >= 8) ? 0xFF : (0xFF << (8 - maskN));
            maskN = (maskN >= 8) ? maskN - 8 : 0;
            if ((Integer.parseInt(ip[i]) & mask) != (Integer.parseInt(subnetIP[i]) & mask)) {
                return false;
            }
        }
        return true;
    }

    public List<String> quordleCheat(List<Character> have, List<Character> haveNot, Map<Integer,Character> rightIn) {
        /**
         * example:
         *  input:
         *   have: [a, f], haveNot: [w, i], rightIn: {(4:r)}
         *  output:
         *   [afear, after, facer, fader, fager, faker, farer, fator, favor, fchar, feuar, flear, Rafer, Safar, safer, sofar, unfar]
         * **/
        List<String> answers = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("words.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() == 5) {
                    boolean isAnswer = true;
                    for (char c : have) {
                        if (!line.contains(String.valueOf(c))) {
                            isAnswer = false;
                            break;
                        }
                    }
                    for (int i = 0; i < 5; i++) {
                        char c = line.charAt(i);
                        if (haveNot.contains(c)) {
                            isAnswer = false;
                            break;
                        }
                        if (rightIn.containsKey(i) && c != rightIn.get(i)) {
                            isAnswer = false;
                            break;
                        }
                    }
                    if (isAnswer) {
                        answers.add(line);
                    }
                }
            }
            br.close();
        } catch (IOException e) { }
        return answers;
    }
}
