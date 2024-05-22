# Homework01

## Problem 1: SumAverageRunningInt

Write a function called sumAverageRunningInt to produce the sum of a, a+1, a+2, ..., to b (a < b). Store the input number ***a*** and ***b*** in variables lowerbound and upperbound,
then, computes and returns the average of the sum.

```java
public class Solution{
    public double sumAverageRunningInt(int a, int b){
        /**
         * example:
         * a = 1, b = 100
         * return: 50.5
         */
        //WRITE YOUR CODE HERE
    }
}
```

## Problem 2: Segment Length

Write a function that takes four numbers（x_1，y_1，x_2，y_2） and returns the length of the segment of the two points.
The result is rounded to 3 decimal places.

```java
public class Solution{
    public double segLength(double x_1, double y_1, double x_2, double y_2){
        /**
         * Math.sqrt(number)求平方根
         * example:
         * x_1 = 1.2, y_1 = 0.1
         * x_2 = 0.6, y_1 = 0.8
         * return: 0.922
         */
        //WRITE YOUR CODE HERE
    }
}
```
> 提示：在本题中，保留三位小数可以参考如下代码：
>
> ```java
> double number = 3.1415926;
> String result = String.format("%.3f", number);
> number = Double.parseDouble(str);
> ```

## Problem 3: Find Prime(素数)

Write a function that takes an integer n that is **greater than** 2 and returns the largest prime that is smaller than n.

```java
public class Solution{
    public int findPrime(int n){
        /**
         * example:
         * n = 9
         * return: 7
         */
        //WRITE YOUR CODE HERE
    }
}
```

## Problem 4: Fibonacci(斐波那契数列)

Write a function called Fibonacci to compute the first ***n*** Fibonacci numbers F(n), where F(n)=F(n–1)+F(n–2) and F(1)=F(2)=1. 
And return the last number.

```java
public class Solution{
    public int fibonacci(int n){
        /**
         * example:
         * n = 20
         * The first 20 Fibonacci numbers are:
         * 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584 4181 6765
         * return: 6765
         */
        //WRITE YOUR CODE HERE
    }
}
```

## Problem 5: CozaLozaWoza

Write a function called CozaLozaWoza which takes a positive integer n. The function shall extract each digit from the input.
Then calculates the number of 1 as "Coza", the number of 2 as "Loza" and the number of 3 as "Woza". Finally, it shall return 
the string as "Coza2Loza0Woza4".

```java
public class Solution{
    public String cozaLozaWoza(int n){
        /**
         * example:
         * n = 11543333
         * return: "Coza2Loza0Woza4"
         */
        //WRITE YOUR CODE HERE
    }
}
```

## Problem 6: Continuous Calculation

Write a function that takes the first parameter as the starting value and the second parameter as the number of operations.
The function only performs the following three operations：
1. x = x+1
2. x = x%10
3. x = x*2

Execute the above three operations in a loop based on the number of operations. Return the final value.

```java
public class Solution{
    public int conCalculation(int start, int times){
        /**
         * example:
         * start = 10; times = 5
         * 10->11->1->2->3->3
         * return: 3
         */
        //WRITE YOUR CODE HERE
    }
}
```