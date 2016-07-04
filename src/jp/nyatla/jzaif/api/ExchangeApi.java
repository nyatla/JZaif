package jp.nyatla.jzaif.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



import org.json.JSONObject;


import jp.nyatla.jzaif.api.result.ActiveOrdersResult;
import jp.nyatla.jzaif.api.result.CancelOrderResult;
import jp.nyatla.jzaif.api.result.DepositHistoryResult;
import jp.nyatla.jzaif.api.result.GetInfoResult;
import jp.nyatla.jzaif.api.result.TradeResult;
import jp.nyatla.jzaif.api.result.TradeHistoryResult;
import jp.nyatla.jzaif.api.result.WithdrawResult;
import jp.nyatla.jzaif.api.result.WithdrawHistoryResult;
import jp.nyatla.jzaif.io.NyansatHttpClient;
import jp.nyatla.jzaif.io.IHttpClient;
import jp.nyatla.jzaif.types.Currency;
import jp.nyatla.jzaif.types.CurrencyPair;
import jp.nyatla.jzaif.types.SortOrder;
import jp.nyatla.jzaif.types.TradeType;


public class ExchangeApi
{
	/** 浮動小数点/整数をzaifフォーマットで出力する関数*/
	private static String numberToStr(double n)
	{
		if(n-Math.floor(n)!=0){
			return Double.toString(n);
		}else{
			return Long.toString((long)(Math.floor(n)));
		}
	}
	final private static String URL="https://api.zaif.jp/tapi";
	private long _nonce;
	final private ApiKey _apikey;
	final private IHttpClient _cl=new NyansatHttpClient();
	private static long makeAutoNonce()
	{
		try {
			long base_date = (new SimpleDateFormat("yyyy-MM-dd")).parse("2016-01-01").getTime();
			return (System.currentTimeMillis()-base_date)/1000;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	private JSONObject doCommand(String i_method,String i_additional_params)
	{
		this._nonce++;
		String query=String.format("nonce=%d&method=%s",this._nonce,i_method);
		if(i_additional_params!=null && i_additional_params.length()>0){
			query+=i_additional_params;
		}
		return new JSONObject(this._cl.postText(URL,this._apikey.key,this._apikey.makeSignedHex(query), query));
		
	}	
	/**
	 * nonceを自動で設定するコンストラクタ
	 */
	public ExchangeApi(ApiKey i_key)
	{
		this(i_key,makeAutoNonce());
	}
	public ExchangeApi(ApiKey i_key,long i_nonce)
	{
		this._apikey=i_key;
		this._nonce=i_nonce;
	}

	public GetInfoResult getInfo()
	{
		return new GetInfoResult(this.doCommand("get_info",null));
	}
	/**
	 * 
	 * @param i_from
	 * @param i_count
	 * @param i_from_id
	 * @param i_end_id
	 * @param i_order
	 * @param i_since
	 * @param i_end
	 * @param i_currency_pair
	 * @return
	 */
	public TradeHistoryResult tradeHistory(Integer i_from,Integer i_count,Long i_from_id,Long i_end_id,SortOrder i_order,Date i_since,Date i_end,CurrencyPair i_currency_pair)
	{
		String p="";
		if(i_from!=null){
			p+="&from="+i_from.toString();
		}
		if(i_count!=null){
			p+="&count="+i_count.toString();
		}
		if(i_from_id!=null){
			p+="&from_id="+i_from_id.toString();
		}
		if(i_end_id!=null){
			p+="&from_id="+i_end_id.toString();
		}
		if(i_order!=null){
			p+="&order="+i_order.strname;
		}
		if(i_since!=null){
			p+="&since="+(i_since.getTime()/1000);
		}
		if(i_end!=null){
			p+="&end="+(i_end.getTime()/1000);
		}
		if(i_currency_pair!=null){
			p+="&currency_pair="+i_currency_pair.shortname;
		}
		return new TradeHistoryResult(this.doCommand("trade_history",p));
	}
	/**
	 * Optionalパラメータを省略した{@link #tradeHistory}関数です。
	 * @return
	 */
	public TradeHistoryResult tradeHistory()
	{
		return this.tradeHistory(null, null, null, null, null, null, null, null);
	}
	/**
	 * 
	 * @param i_currency_pair
	 * @return
	 */
	public ActiveOrdersResult activeOrders(CurrencyPair i_currency_pair)
	{
		String p="";
		if(i_currency_pair!=null){
			p+="&currency_pair="+i_currency_pair.shortname;
		}
		return new ActiveOrdersResult(this.doCommand("active_orders",p));
	}
	/**
	 * Optionalパラメータを省略した{@link #activeOrders}関数です。
	 * @return
	 */
	public ActiveOrdersResult activeOrders()
	{
		return this.activeOrders(null);
	}
	
	/**
	 * 	
	 * @param i_currency_pair
	 * [Required]
	 * @param i_action
	 * [Required]
	 * @param i_price
	 * [Required]
	 * @param i_amount
	 * [Required]
	 * @param i_limit
	 * @return
	 */
	public TradeResult trade(CurrencyPair i_currency_pair,TradeType i_action,double i_price,Number i_amount,Double i_limit)
	{
		String p="";
		p+="&currency_pair="+i_currency_pair.shortname;
		p+="&action="+i_action.strname;
		p+="&price="+numberToStr(i_price);
		p+="&amount="+numberToStr(i_amount.doubleValue());
		if(i_limit!=null){
			p+="&i_limit="+Double.toString(i_limit);
		}
		return new TradeResult(this.doCommand("trade",p));		
	}
	/**
	 * Optionalを省略した{@link #trade}関数です。
	 * @return
	 */
	public TradeResult trade(CurrencyPair i_currency_pair,TradeType i_action,double i_price,Number i_amount)
	{
		return this.trade(i_currency_pair, i_action, i_price, i_amount,null);
	}
	/**
	 * 
	 * @param i_order_id
	 * [Required]
	 * @return
	 */
	public CancelOrderResult cancelOrder(long i_order_id)
	{
		String p="";
		p+="&order_id="+Long.toString(i_order_id);
		return new CancelOrderResult(this.doCommand("cancel_order",p));
		
	}
	/**
	 * 
	 * @param i_currency
	 * [Required]
	 * @param i_address
	 * [Required]
	 * @param i_amount
	 * [Required]
	 * @param i_opt_fee
	 * @return
	 */
	public WithdrawResult withdraw(Currency i_currency,String i_address,double i_amount,Double i_opt_fee)
	{
		String p="";
		p+="&currency="+i_currency.shortname;
		p+="&address="+i_address;
		p+="&amount="+numberToStr(i_amount);
		if(i_opt_fee!=null){
			p+="&opt_fee="+numberToStr(i_opt_fee);
		}
		return new WithdrawResult(this.doCommand("withdraw",p));
	}
	/**
	 * Optionalを省略した{@link #withdraw}関数です。
	 * @return
	 */
	public WithdrawResult withdraw(Currency i_currency,String i_address,double i_amount)
	{
		return this.withdraw(i_currency, i_address, i_amount,null);
	}
	
	/**
	 * @param i_currency
	 * [Required]
	 * @param i_from
	 * @param i_count
	 * @param i_from_id
	 * @param i_end_id
	 * @param i_order
	 * @param i_since
	 * @param i_end
	 * @return
	 */
	public DepositHistoryResult depositHistory(Currency i_currency,Integer i_from,Integer i_count,Long i_from_id,Long i_end_id,SortOrder i_order,Date i_since,Date i_end)
	{
		String p="";
		p+="&currency="+i_currency.shortname;
		if(i_from!=null){
			p+="&from="+i_from.toString();
		}
		if(i_count!=null){
			p+="&count="+i_count.toString();
		}
		if(i_from_id!=null){
			p+="&from_id="+i_from_id.toString();
		}
		if(i_end_id!=null){
			p+="&from_id="+i_end_id.toString();
		}
		if(i_order!=null){
			p+="&order="+i_order.strname;
		}
		if(i_since!=null){
			p+="&since="+(i_since.getTime()/1000);
		}
		if(i_end!=null){
			p+="&end="+(i_end.getTime()/1000);
		}
		return new DepositHistoryResult(this.doCommand("deposit_history",p));
	}
	/**
	 * Optionalを省略した{@link #depositHistory}関数です。
	 * @return
	 */	
	public DepositHistoryResult depositHistory(Currency i_currency)
	{
		return this.depositHistory(i_currency,null, null, null, null, null, null, null);
	}
	
	public WithdrawHistoryResult withdrawHistory(Currency i_currency,Integer i_from,Integer i_count,Long i_from_id,Long i_end_id,SortOrder i_order,Date i_since,Date i_end)
	{
		String p="";
		p+="&currency="+i_currency.shortname;
		if(i_from!=null){
			p+="&from="+i_from.toString();
		}
		if(i_count!=null){
			p+="&count="+i_count.toString();
		}
		if(i_from_id!=null){
			p+="&from_id="+i_from_id.toString();
		}
		if(i_end_id!=null){
			p+="&from_id="+i_end_id.toString();
		}
		if(i_order!=null){
			p+="&order="+i_order.strname;
		}
		if(i_since!=null){
			p+="&since="+(i_since.getTime()/1000);
		}
		if(i_end!=null){
			p+="&end="+(i_end.getTime()/1000);
		}
		return new WithdrawHistoryResult(this.doCommand("withdraw_history",p));		
	}
	/**
	 * Optionalを省略した{@link #withdrawHistory}関数です。
	 * @return
	 */		
	public WithdrawHistoryResult withdrawHistory(Currency i_currency)
	{
		return this.withdrawHistory(i_currency, null, null, null, null, null, null, null);
	}

}
