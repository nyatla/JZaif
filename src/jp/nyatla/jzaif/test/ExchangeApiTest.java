package jp.nyatla.jzaif.test;

import java.io.File;

import jp.nyatla.jzaif.api.ApiKey;
import jp.nyatla.jzaif.api.ExchangeApi;
import jp.nyatla.jzaif.api.result.ActiveOrdersResult;
import jp.nyatla.jzaif.api.result.CancelOrderResult;
import jp.nyatla.jzaif.api.result.DepositHistoryResult;
import jp.nyatla.jzaif.api.result.GetInfoResult;
import jp.nyatla.jzaif.api.result.TradeHistoryResult;
import jp.nyatla.jzaif.api.result.TradeResult;
import jp.nyatla.jzaif.api.result.WithdrawHistoryResult;
import jp.nyatla.jzaif.api.result.WithdrawResult;
import jp.nyatla.jzaif.types.Currency;
import jp.nyatla.jzaif.types.CurrencyPair;
import jp.nyatla.jzaif.types.TradeType;

public class ExchangeApiTest {

	public static void main(String[] args)
	{
		ApiKey k=ApiKey.loadFromXml(new File("setting.xml"));
		ExchangeApi lp=new ExchangeApi(k);
		GetInfoResult r1=lp.getInfo();
		TradeHistoryResult r2=lp.tradeHistory(null,null,null,null,null,null,null,null);
		TradeResult r4=lp.trade(CurrencyPair.MONAJPY,TradeType.BID,1,1,null);
		ActiveOrdersResult r3=lp.activeOrders(null);
		CancelOrderResult r5=lp.cancelOrder(r3.orders.get(0).id);
		

		WithdrawResult r7=lp.withdraw(Currency.MONA, "MMfeGULzEfsRxcehx2Xq5ZArqCfxBoZiSh",0.1,0.0);
		DepositHistoryResult r8=lp.depositHistory(Currency.MONA,null,null,null,null,null,null,null);
		WithdrawHistoryResult r10=lp.withdrawHistory(Currency.MONA,null,null,null,null,null,null,null);
		
		return;
		}
}
