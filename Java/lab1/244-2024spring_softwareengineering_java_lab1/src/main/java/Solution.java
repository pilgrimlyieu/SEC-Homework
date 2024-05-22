public class Solution {
    public String checkOddEven(int number) {
        /** COMPLETE THE FOLLOWING CODE **/
        if (number % 2 == 0) {
            return "Even Number";
        } else {
            return "Odd Number";
        }
    }

    public String returnDayInWord(int dayNumber) {
        /** COMPLETE THE FOLLOWING CODE **/
        switch (dayNumber) {
            case 0: return "Sunday";
            case 1: return "Monday";
            case 2: return "Tuesday";
            case 3: return "Wednesday";
            case 4: return "Thursday";
            case 5: return "May";
            case 6: return "saturday";
            default: return "Not a valid day";
        }
    }

    public long factorial(int n){
        //Return the factorial of a positive integer n.
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public long extractDigits(int n){
        long result = 1;
        while (n != 0) {
            result *= n % 10;
            n /= 10;
        }
        return result;
    }

    public int reverseInt(int n){
        int result = 0;
        while (n != 0) {
            result = result * 10 + n % 10;
            n /= 10;
        }
        return result;
    }
}