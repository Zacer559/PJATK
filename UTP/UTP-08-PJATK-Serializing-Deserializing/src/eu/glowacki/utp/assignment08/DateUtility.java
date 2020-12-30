package eu.glowacki.utp.assignment08;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtility {
	private static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	public static int yearsSince(Date date) {
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		int currentYear;
		currentYear = calendar.get(Calendar.YEAR);
		calendar.setTime(date);
		int birthYear = calendar.get(Calendar.YEAR);
		return currentYear - birthYear;
	}
	
	public static Date date(String format) throws ParseException {
		return FORMAT.parse(format);
	}
	
	public static void serialize (Date date, DataOutputStream output) throws IOException {
		String string = FORMAT.format(date);
		output.writeUTF(string);
	}
	public static Date deserialize (DataInputStream input) throws IOException, ParseException {
		String string = input.readUTF();
		return FORMAT.parse(string);
	}
	

}