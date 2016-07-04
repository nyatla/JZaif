package jp.nyatla.jzaif.api.result;

import org.json.JSONObject;

public class TradeResult extends ExchangeCommonResult
{
	final public double received;
	final public double remains;
	final public long order_id;
	final public Funds funds;

	public TradeResult(JSONObject i_jso)
	{
		super(i_jso);
		if(!this.success){
			this.received=Double.NaN;
			this.remains=Double.NaN;
			this.order_id=0;
			this.funds=null;
			return;
		}
		JSONObject jso=i_jso.getJSONObject("return");
		this.received=jso.getDouble("received");
		this.remains=jso.getDouble("remains");
		this.order_id=jso.getLong("order_id");
		this.funds=new Funds(jso.getJSONObject("funds"));
	}
}
