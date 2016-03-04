import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class FindingZodiacTest {

    FindingZodiac findingZodiac;

    @Before
    public void setUp(){
        findingZodiac = new FindingZodiac();
    }

    @Test
    public void canary(){
        assertTrue(true);
    }

    @Test
    public void validDateTest(){
        assertTrue(findingZodiac.validate("09/06/1992"));
    }

    @Test
    public void invalidateDateTest(){
        assertFalse(findingZodiac.validate("02/30/1992"));
    }

    @Test
    public void validDayTest(){
        assertEquals("Sun", findingZodiac.dayOfTheWeek(9, 6, 1992));
    }

    @Test
    public void zodiacSignTest(){
        assertEquals("Libra", findingZodiac.zodiacSign(6, 9));
    }
}