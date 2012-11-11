package buz;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {


    public static Date today() {
        return clearDate(new Date());
    }
    
    public static Date tomorow() {
    	Calendar gc = GregorianCalendar.getInstance();
		gc.roll(Calendar.DATE, true);
        return clearDate(gc.getTime());
    }

    public static Date clearDate(Date date){
		Calendar gc = GregorianCalendar.getInstance();
		gc.setTime(date);
		gc.set(Calendar.HOUR_OF_DAY, 0);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);
		
		return gc.getTime();
    }
        


}
