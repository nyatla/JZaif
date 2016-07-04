package jp.nyatla.jzaif.api.result;

import java.util.Date;

import org.json.JSONObject;

/**
 * get_infoコマンドの戻り値を格納します。
 * 拡張パラメータは{@link #success}がtrueのときのみ有効です。
 */
public class GetInfoResult extends ExchangeCommonResult
{
	public GetInfoResult(JSONObject i_jso)
	{
		super(i_jso);
		if(!this.success){
			this.funds=null;
			this.deposit=null;
			this.rights=null;
			this.trade_count=0;
			this.open_orders=0;
			this.server_time=null;
			return;
		}
		JSONObject r=i_jso.getJSONObject("return");
		this.funds=new Funds(r.getJSONObject("funds"));
		this.deposit=new Deposit(r.getJSONObject("deposit"));
		this.rights=new Rights(r.getJSONObject("rights"));
		this.trade_count=r.getLong("trade_count");
		this.open_orders=r.getInt("open_orders");
		this.server_time=new Date(r.getLong("server_time")*1000);
	}

	public class Deposit{
		final public double jpy;
		final public double btc;
		final public double mona;		
		public Deposit(JSONObject i_jso)
		{
			this.jpy=i_jso.getDouble("jpy");
			this.btc=i_jso.getDouble("btc");
			this.mona=i_jso.getDouble("mona");
		}
	}
	public class Rights{
		final public boolean info;
		final public boolean trade;
		final public boolean withdraw;		
		public Rights(JSONObject i_jso)
		{
			this.info=i_jso.getInt("info")==1?true:false;
			this.trade=i_jso.getInt("trade")==1?true:false;
			this.withdraw=i_jso.getInt("withdraw")==1?true:false;
		}
	}

	final public Funds funds;
	final public Deposit deposit;
	final public Rights rights;
	final public long trade_count;
	final public int open_orders;
	final public Date server_time;
}
