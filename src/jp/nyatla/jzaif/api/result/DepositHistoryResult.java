package jp.nyatla.jzaif.api.result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import org.json.JSONObject;

public class DepositHistoryResult extends ExchangeCommonResult{
	public class Item
	{
		final public long id;
		final public Date timestamp;
		final public String address;
		final public double amount;
		final public String txid;
		public Item(long i_id,JSONObject i_jso)
		{
			this.id=i_id;
			this.timestamp=new Date(i_jso.getLong("timestamp")*1000);
			this.address=i_jso.getString("address");
			this.amount=i_jso.getDouble("amount");
			this.txid=i_jso.getString("txid");
		}
	}
	final public List<Item> orders;
	public DepositHistoryResult(JSONObject i_jso)
	{
		super(i_jso);
		if(!this.success){			
			this.orders=null;
			return;
		}
		this.orders=new ArrayList<Item>();
		JSONObject jso=i_jso.getJSONObject("return");
		for(String n : jso.keySet())
		{
			this.orders.add(new Item(Long.parseLong(n),jso.getJSONObject(n)));
		}
		return;	 
		
	}
}
