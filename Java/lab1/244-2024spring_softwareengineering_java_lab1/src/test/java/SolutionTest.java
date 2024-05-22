import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
    @Test
    public void test_1(){
        Solution s = new Solution();
        Assert.assertEquals("Even Number", s.checkOddEven(2));
        Assert.assertEquals("Even Number", s.checkOddEven(66));
        Assert.assertEquals("Odd Number", s.checkOddEven(13));
    }

    @Test
    public void test_2(){
        Solution s = new Solution();
        Assert.assertEquals("Tuesday", s.returnDayInWord(2));
        Assert.assertEquals("Not a valid day", s.returnDayInWord(66));
        Assert.assertEquals("Not a valid day", s.returnDayInWord(13));
        Assert.assertEquals("Sunday", s.returnDayInWord(0));
    }

    @Test
    public void test_3(){
        Solution s = new Solution();
        Assert.assertEquals(39916800L, s.factorial(11));
        Assert.assertEquals(479001600L, s.factorial(12));
        Assert.assertEquals(6227020800L, s.factorial(13));
    }

    @Test
    public void test_4(){
        Solution s = new Solution();
        Assert.assertEquals(1L, s.extractDigits(11));
        Assert.assertEquals(1L, s.extractDigits(1));
        Assert.assertEquals(120L, s.extractDigits(15423));
        Assert.assertEquals(55801305L, s.extractDigits(953799999));
        Assert.assertEquals(0L, s.extractDigits(15023));
    }

    @Test
    public void test_5(){
        Solution s = new Solution();
        Assert.assertEquals(11, s.reverseInt(11));
        Assert.assertEquals(1, s.reverseInt(1));
        Assert.assertEquals(32451, s.reverseInt(15423));
        Assert.assertEquals(999997359, s.reverseInt(953799999));
        Assert.assertEquals(2051, s.reverseInt(15020));
    }
}
