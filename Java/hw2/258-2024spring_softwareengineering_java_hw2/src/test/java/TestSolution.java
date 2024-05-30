import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class TestSolution{
    @Test
    public void testIPMatch() {
        Solution s = new Solution();
        Assert.assertEquals(false, s.IPmatch("192.168.1.100", "192.168.2.0/23"));
        Assert.assertEquals(true, s.IPmatch("192.168.3.100", "192.168.2.0/23"));
        Assert.assertEquals(false, s.IPmatch("192.168.3.100", "192.168.2.0/24"));
    }

    @Test
    public void testQuordle() {
        Solution s = new Solution();
        List<Character> have = new ArrayList<>();
        have.add('a');
        have.add('f');
        List<Character> haveNot = new ArrayList<>();
        haveNot.add('w');
        haveNot.add('i');
        Map<Integer, Character> rightIn = new HashMap<>();
        rightIn.put(4, 'r');
        // show result
        List<String> result = s.quordleCheat(have, haveNot, rightIn);
        for (String str : result) {
            System.out.println(str);
        }
    }
}