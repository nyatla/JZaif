package jp.nyatla.jzaif.api.result;

import org.json.JSONObject;

/**
 * ExchangeAPIの戻り値を格納するベースクラス
 */
public class ExchangeCommonResult {
	/** APIが成功したときにtrueです。*/
	final public boolean success;
	/** 失敗理由を格納するテキスト。{@link #success}がfalseのときのみ有効。*/
	final public String error_text;
	public ExchangeCommonResult(boolean i_success,String i_error_text)
	{
		this.success=i_success;
		this.error_text=i_error_text;
	}
	public ExchangeCommonResult(JSONObject i_jso)
	{
		boolean s=i_jso.getInt("success")==1;
		String e=(s?null:i_jso.getString("error"));
		this.success=s;
		this.error_text=e;
	}
	public class Funds{
		final public double jpy;
		final public double btc;
		final public double mona;
		public Funds(JSONObject i_jso)
		{
			this.jpy=i_jso.getDouble("jpy");
			this.btc=i_jso.getDouble("btc");
			this.mona=i_jso.getDouble("mona");
		}
	}	
}

