package buz;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class DateUtilsTest {
	private static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyyMMddHHmmssSSS");

	@Test
	public void testToday() {
		final Date date = new Date();
		final Date result = DateUtils.today();

		assertEquals(sdf.format(date).substring(0, 8) + "000000000",
				sdf.format(result));
	}

	@Test
	public void testTomorow() {
		final Date date = new Date();
		Calendar gc = GregorianCalendar.getInstance();
		gc.setTime(date);
		gc.roll(Calendar.DATE, true);
		final Date expected = gc.getTime();
		assertEquals(sdf.format(expected).substring(0, 8) + "000000000",
				sdf.format(DateUtils.tomorow()));
	}

	@Test
	public void testClearDate() throws ParseException {
		final Date date = sdf.parse("20111111111111111");
		assertEquals("20111111000000000", sdf.format(DateUtils.clearDate(date)));
	}

}
