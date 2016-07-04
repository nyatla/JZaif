package jp.nyatla.jzaif.api.result;

import java.util.ArrayList;
import java.util.Date;



import jp.nyatla.jzaif.types.CurrencyPair;
import jp.nyatla.jzaif.types.TradeType;

import org.json.JSONArray;
import org.json.JSONObject;

public class TradesResult extends ArrayList<TradesResult.Item>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4431120995766718657L;
	public class Item{
		final public Date date;
		final public double price;
		final public double amount;
		final public long tid;
		final public CurrencyPair currency_pair;
		final public TradeType trade_type;
		public Item(JSONObject i_src){
			this.date=new Date(i_src.getLong("date")*1000);
			this.price=i_src.getDouble("price");
			this.amount=i_src.getDouble("amount");
			this.tid=i_src.getLong("tid");
			if(i_src.has("currency_pair")){
				this.currency_pair=CurrencyPair.strToVal(i_src.getString("currency_pair"));
			}else{
				//StreamingAPIのbug対応
				this.currency_pair=CurrencyPair.strToVal(i_src.getString("currenty_pair"));
			}
			this.trade_type=TradeType.strToVal(i_src.getString("trade_type"));
		}
		@Override
		public String toString()
		{
			return super.toString();
		}
	}
	public TradesResult(JSONArray i_src)
	{
		for(int i=0;i<i_src.length();i++){
			this.add(new Item(i_src.getJSONObject(i)));
		}
	}
}
