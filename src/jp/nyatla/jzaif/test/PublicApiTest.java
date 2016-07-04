package jp.nyatla.jzaif.test;

import jp.nyatla.jzaif.api.PublicApi;
import jp.nyatla.jzaif.api.result.DepthResult;
import jp.nyatla.jzaif.api.result.TickerResult;
import jp.nyatla.jzaif.api.result.TradesResult;
import jp.nyatla.jzaif.types.CurrencyPair;

public class PublicApiTest {
	public static void main(String[] args)
	{
		PublicApi lp=new PublicApi(CurrencyPair.BTCJPY);
		double r1=lp.lastPrice();
		DepthResult r2=lp.depth();
		TickerResult r3=lp.ticker();
		TradesResult r4=lp.trades();
		return;
	}
}
