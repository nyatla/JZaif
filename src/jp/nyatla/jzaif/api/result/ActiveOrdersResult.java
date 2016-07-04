package jp.nyatla.jzaif.api.result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.nyatla.jzaif.types.CurrencyPair;
import jp.nyatla.jzaif.types.TradeType;

import org.json.JSONObject;

public class ActiveOrdersResult extends ExchangeCommonResult
{
	public class Item
	{
		final public long id;
		final public CurrencyPair currency_pair;
		final public TradeType action;
		final public double amount;
		final public double price;
		final public Date timestamp;
		public Item(long i_id,JSONObject i_jso)
		{
			this.id=i_id;
			this.currency_pair=CurrencyPair.strToVal(i_jso.getString("currency_pair"));
			this.action=TradeType.strToVal(i_jso.getString("action"));
			this.amount=i_jso.getDouble("amount");
			this.price=i_jso.getDouble("price");
			this.timestamp=new Date(i_jso.getLong("timestamp")*1000);
		}
	}
	final public List<Item> orders;
	public ActiveOrdersResult(JSONObject i_jso)
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
