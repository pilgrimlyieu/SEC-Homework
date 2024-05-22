public class Solution{
    public double sumAverageRunningInt(int a, int b){
        return ((double) a + b) / 2;
    }

    public double segLength(double x_1, double y_1, double x_2, double y_2){
        return Double.parseDouble(String.format("%.3f", Math.sqrt(Math.pow(x_1 - x_2, 2) + Math.pow(y_1 - y_2, 2))));
    }

    boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int findPrime(int n){
        for (int i = n; i >= 2; i--) {
            if (isPrime(i)) {
                return i;
            }
        }
        return 2;
    }

    public int fibonacci(int n){
        int n1 = 0;
        int n2 = 1;
        for (int i = 0; i < n; i++) {
            int temp = n2;
            n2 += n1;
            n1 = temp;
        }
        return n1;
    }

    public String cozaLozaWoza(int n){
        int n1 = 0, n2 = 0, n3 = 0;
        while (n != 0) {
            switch (n % 10) {
                // Maven use Java11, so there will be an error when using arrow expression. See `pom.xml` for details.
                case 1: n1++; break;
                case 2: n2++; break;
                case 3: n3++; break;
            }
            n /= 10;
        }
        return String.format("Coza%dLoza%dWoza%d", n1, n2, n3);
    }

    public int conCalculation(int start, int times){
        for (int i = 0; i < times; i++) {
            switch (i % 3) {
                case 0: start++; continue;
                case 1: start %= 10; continue;
                case 2: start *= 2; continue;
            }
        }
        return start;
    }
}