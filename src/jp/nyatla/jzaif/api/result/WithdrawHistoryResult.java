package jp.nyatla.jzaif.api.result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

public class WithdrawHistoryResult extends ExchangeCommonResult{
	public class Item
	{
		final public long id;
		final public Date timestamp;
		final public String address;
		final public double fee;
		final public double amount;
		final public String txid;
		public Item(long i_id,JSONObject i_jso){
			this.id=i_id;
			this.timestamp=new Date(i_jso.getLong("timestamp")*1000);
			this.address=i_jso.getString("address");
			this.fee=i_jso.getDouble("fee");
			this.amount=i_jso.getDouble("amount");
			this.txid=i_jso.isNull("txid")?null:i_jso.getString("txid");
		}
	}
	final public List<Item> history;
	public WithdrawHistoryResult(JSONObject i_jso)
	{
		super(i_jso);
		if(!this.success){
			this.history=null;
			return;
		}
		this.history=new ArrayList<Item>();
		JSONObject jso=i_jso.getJSONObject("return");
		for(String n : jso.keySet())
		{
			this.history.add(new Item(Long.parseLong(n),jso.getJSONObject(n)));
		}
		return;	 
	}
}