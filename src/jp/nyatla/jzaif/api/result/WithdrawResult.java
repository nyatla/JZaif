package jp.nyatla.jzaif.api.result;


import org.json.JSONObject;

public class WithdrawResult extends ExchangeCommonResult{
	public WithdrawResult(JSONObject i_jso)
	{
		super(i_jso);
		if(!this.success){
			this.funds=null;
			this.txid="";
			return;
		}
		JSONObject r=i_jso.getJSONObject("return");
		this.funds=new Funds(r.getJSONObject("funds"));
		this.txid="";
	}
	final public String txid;
	final public Funds funds;
}
