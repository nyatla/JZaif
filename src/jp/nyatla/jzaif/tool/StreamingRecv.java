package jp.nyatla.jzaif.tool;


import jp.nyatla.jzaif.api.StreamingApi;
import jp.nyatla.jzaif.api.result.StreamingNotify;
import jp.nyatla.jzaif.types.CurrencyPair;

public class StreamingRecv {
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
		CurrencyPair cp=CurrencyPair.strToVal(args[0]);
		StreamingApi lp=new Sa(cp);
		try {
			Thread.sleep(1000000000);
		} catch (InterruptedException e) {
		}
		lp.shutdown();
		return;
	}
}
