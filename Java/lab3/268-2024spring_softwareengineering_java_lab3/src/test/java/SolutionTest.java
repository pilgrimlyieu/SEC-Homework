import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
    @Test
    public void test() throws Exception{
        Solution s = new Solution();
        Cat c1 = new Cat("cat1");
        Cat c2 = new Cat("cat2");
        Dog d1 = new Dog("dog1");
        Dog d2 = new Dog("dog2");
        Wolf w1 = new Wolf("wolf1");
        Wolf w2 = new Wolf("wolf2");
        Object[] animals = {c1, d1, w1, c2, d2, w2};
        Assert.assertEquals(null, s.handleAnimals(animals, "run", "attack", "cook"));
    }
}