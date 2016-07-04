package jp.nyatla.jzaif;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * YYYY-MM-DD hh:mm:ss.SSSSSS形式の文字列を日付に変換する。
	 * SSSのナノ秒フィールドは無視する。
	 * @param i_str
	 */
	public synchronized static Date parseZaifFullTimeText(String i_str)
	{
		String[]l=i_str.split("\\.");
		try {
			long t=sdf1.parse(l[0]).getTime()+(Integer.parseInt(l[1])/1000);
			return new Date(t);
		} catch (NumberFormatException | ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}
	public static void main(String[] args)
	{
		Date d=parseZaifFullTimeText("2016-07-04 21:17:08.662854");
		System.out.println(d);
		return;
	}
}
