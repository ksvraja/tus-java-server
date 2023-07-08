package me.desair.tus.server.util;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.commons.lang3.time.TimeZones;

public class DateUtils {
	
	private static String HEADER_STRING_PATTERN = "EEE, dd MMM yyyy HH:mm:ss zzz";
	
	private static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss",
			TimeZone.getTimeZone(TimeZones.GMT_ID), Locale.US);

	private static final FastDateFormat HEADER_DATE_FORMAT = FastDateFormat.getInstance(HEADER_STRING_PATTERN,
			TimeZone.getTimeZone(TimeZones.GMT_ID), Locale.US);

	private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(HEADER_STRING_PATTERN, Locale.US); 
	
	public static String getHeaderDateString(String date) {

		try {
			return HEADER_DATE_FORMAT.format(DATE_FORMAT.parse(date));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Instant getHeaderDate(String date) {
		LocalDateTime localDateTime = LocalDateTime.parse(date, dateTimeFormatter); 
		ZoneId zoneId = ZoneId.of("GMT"); 
		ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId); 
		return zonedDateTime.toInstant();
	}
	
	public static long getHeaderDateLong(String date) {
		LocalDateTime localDateTime = LocalDateTime.parse(date, dateTimeFormatter); 
		ZoneId zoneId = ZoneId.of("GMT"); 
		ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId); 
		return zonedDateTime.toInstant().toEpochMilli();
	}
}
