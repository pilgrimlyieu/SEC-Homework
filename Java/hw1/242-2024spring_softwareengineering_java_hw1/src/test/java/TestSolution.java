import org.junit.Assert;
import org.junit.Test;

public class TestSolution{
    @Test
    public void test_1(){
        Solution s = new Solution();
        Assert.assertEquals(50.5, s.sumAverageRunningInt(1,100), 0.01);
        Assert.assertEquals(55.5, s.sumAverageRunningInt(55,56), 0.01);
        Assert.assertEquals(458.5, s.sumAverageRunningInt(251,666), 0.01);
        Assert.assertEquals(50038.5, s.sumAverageRunningInt(77,100000), 0.01);
    }

    @Test
    public void test_2(){
        Solution s = new Solution();
        Assert.assertEquals(0.922, s.segLength(1.2, 0.1, 0.6, 0.8), 0.0001);
        Assert.assertEquals(0, s.segLength(3.14, 2.72, 3.14, 2.72), 0.0001);
        Assert.assertEquals(0.694, s.segLength(0.1255, 0.1066, 0.6512, 0.5596), 0.0001);
        Assert.assertEquals(1.414, s.segLength(0.0, 0.0, 1.0, 1.0), 0.0001);
    }

    @Test
    public void test_3(){
        Solution s = new Solution();
        Assert.assertEquals(3, s.findPrime(3));
        Assert.assertEquals(7, s.findPrime(10));
        Assert.assertEquals(97, s.findPrime(100));
        Assert.assertEquals(199, s.findPrime(200));
    }

    @Test
    public void test_4(){
        Solution s = new Solution();
        Assert.assertEquals(1, s.fibonacci(2));
        Assert.assertEquals(2, s.fibonacci(3));
        Assert.assertEquals(6765, s.fibonacci(20));
        Assert.assertEquals(832040, s.fibonacci(30));
        Assert.assertEquals(2144908973, s.fibonacci(55));
    }

    @Test
    public void test_5(){
        Solution s = new Solution();
        Assert.assertEquals("Coza2Loza0Woza4", s.cozaLozaWoza(11543333));
        Assert.assertEquals("Coza0Loza0Woza1", s.cozaLozaWoza(3));
        Assert.assertEquals("Coza0Loza0Woza0", s.cozaLozaWoza(4566660));
        Assert.assertEquals("Coza10Loza0Woza0", s.cozaLozaWoza(1111111111));
    }

    @Test
    public void test_6(){
        Solution s = new Solution();
        Assert.assertEquals(3, s.conCalculation(2,1));
        Assert.assertEquals(3, s.conCalculation(10,5));
        Assert.assertEquals(14, s.conCalculation(66,3));
        Assert.assertEquals(19, s.conCalculation(1223,10));
        Assert.assertEquals(9, s.conCalculation(1999833,20));
    }
}