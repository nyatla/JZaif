package jp.nyatla.jzaif.test;


import jp.nyatla.jzaif.api.StreamingApi;
import jp.nyatla.jzaif.api.result.StreamingNotify;
import jp.nyatla.jzaif.types.CurrencyPair;

public class StreamingApiTest {
	static public class Sa extends StreamingApi
	{
		public Sa(CurrencyPair i_cpair) {
			super(i_cpair);
		}
		@Override
		public void onUpdate(String i_data)
		{
			System.out.println(i_data);
		}
		@Override
		public void onUpdate(StreamingNotify i_data)
		{
			return;
		}
	}
	public static void main(String[] args)
	{
		StreamingApi lp=new Sa(CurrencyPair.BTCJPY);
		try {
			Thread.sleep(100000000);
		} catch (InterruptedException e) {
		}
		lp.shutdown();
		return;
	}
}
