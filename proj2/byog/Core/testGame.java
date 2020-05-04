package byog.Core;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;

public class testGame {

    @Test
    public void testGetSeed() {
        String input = "n324324Soup789789786876Hello";
        String exp = "324324";
        assertEquals(exp, Game.getSeed(input));
        input = "jk352ikopu435435qrwq";
        exp = "";
        assertEquals(exp, Game.getSeed(input));
        input = "lwqrwqrqwr";
        exp = "";
        assertEquals(exp, Game.getSeed(input));
        input = "rwq0saxz";
        exp = "0";
    }

    @Test
    public void testGetOption() {
        String input = "lrqwrqw423rqwr";
        int exp = 1;
        assertEquals(exp, Game.getOption(input));
        input = "Lrqwrqwrqwr";
        exp = 1;
        assertEquals(exp, Game.getOption(input));
        input = "32wefsdql423:Q";
        exp = 2;
        assertEquals(exp, Game.getOption(input));
    }

}
