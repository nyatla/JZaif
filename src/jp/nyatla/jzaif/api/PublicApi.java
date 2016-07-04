package jp.nyatla.jzaif.api;


import org.json.JSONArray;
import org.json.JSONObject;


import jp.nyatla.jzaif.api.result.DepthResult;
import jp.nyatla.jzaif.api.result.TickerResult;
import jp.nyatla.jzaif.api.result.TradesResult;
import jp.nyatla.jzaif.io.IHttpClient;
import jp.nyatla.jzaif.io.NyansatHttpClient;
import jp.nyatla.jzaif.types.CurrencyPair;
/**
 * DepthAPIを提供するクラスです。
 */
public class PublicApi
{
	final public static String API_URL_PREFIX="https://api.zaif.jp/api/1/";
	final private CurrencyPair _cu_pair;
	final private IHttpClient _cl=new NyansatHttpClient();
	/**
	 * コンストラクタ。
	 * @param i_pair
	 * 通貨ペアを指定します。
	 */
	public PublicApi(CurrencyPair i_pair)
	{
		this._cu_pair=i_pair;
	}
	/**
	 * 現在のLastPriceを得ます。
	 * @return
	 */
	public DepthResult depth()
	{
		String json_str=this._cl.getText(API_URL_PREFIX+"depth/"+this._cu_pair.shortname);		
		JSONObject jso=new JSONObject(json_str);
		return new DepthResult(jso);
	}
	public TickerResult ticker()
	{
		String json_str=this._cl.getText(API_URL_PREFIX+"ticker/"+this._cu_pair.shortname);	
		JSONObject jso=new JSONObject(json_str);
		return new TickerResult(jso);
	}
	public TradesResult trades()
	{
		String json_str=this._cl.getText(API_URL_PREFIX+"trades/"+this._cu_pair.shortname);		
		JSONArray jso=new JSONArray(json_str);
		TradesResult r=new TradesResult(jso);
		return r;
	}
	public double lastPrice()
	{
		String json_str=this._cl.getText(API_URL_PREFIX+"last_price/"+this._cu_pair.shortname);		
		JSONObject jso=new JSONObject(json_str);
		return jso.getDouble("last_price");
	}	
}
