package jp.nyatla.jzaif.api.result;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class DepthResult
{
	public class Item{
		final public double price;
		final public double volume;
		public Item(JSONArray i_src){
			this.price=i_src.getDouble(0);
			this.volume=i_src.getDouble(1);
		}
		@Override
		public String toString()
		{
			return "price:"+this.price+",volume:"+this.volume;
		}
	}
	final public List<Item> asks=new ArrayList<Item>();
	final public List<Item> bids=new ArrayList<Item>();
	public DepthResult(JSONObject i_src){
		JSONArray a=i_src.getJSONArray("asks");
		for(int i=0;i<a.length();i++){
			this.asks.add(new Item(a.getJSONArray(i)));
		}
		JSONArray b=i_src.getJSONArray("bids");
		for(int i=0;i<b.length();i++){
			this.bids.add(new Item(b.getJSONArray(i)));
		}
	}
	@Override
	public String toString()
	{
		return "{"+this.asks.toString()+"},{"+this.bids.toString()+"}";
	}	
}
