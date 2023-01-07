package util;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

public class UtilTest {

    @Test
    public void testDateFormatter_WHEN_InputDateIsNull_RETURN_EmptyString() {
        //GIVEN
        final LocalDateTime localDateTime = null;
        //THEN
        Assert.assertEquals("", Util.formatDate(localDateTime));
    }

    @Test
    public void testDateFormatter_WHEN_InputDateIsValid_RETURN_FormattedDate() {
        //GIVEN
        final LocalDateTime localDateTime = LocalDateTime.of(2019, Month.JANUARY, 22, 13, 20, 59);
        //THEN
        Assert.assertEquals("2019-01-22 13:20:59", Util.formatDate(localDateTime));
    }
}
