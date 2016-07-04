package jp.nyatla.jzaif.api.result;

import org.json.JSONObject;

public class CancelOrderResult extends ExchangeCommonResult{
	public CancelOrderResult(JSONObject i_jso)
	{
		super(i_jso);
		if(!this.success){
			this.funds=null;
			this.order_id=0;
			return;
		}
		JSONObject r=i_jso.getJSONObject("return");
		this.funds=new Funds(r.getJSONObject("funds"));
		this.order_id=r.getLong("order_id");
	}
	final public long order_id;
	final public Funds funds;
}
