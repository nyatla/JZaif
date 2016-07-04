package jp.nyatla.jzaif.api.result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.nyatla.jzaif.types.CurrencyPair;
import jp.nyatla.jzaif.types.TradeType;

import org.json.JSONObject;

/**
 * trade_history コマンドの戻り値を格納します。
 * 拡張パラメータは{@link #success}がtrueのときのみ有効です。
 */
public class TradeHistoryResult extends ExchangeCommonResult
{
	public class Item
	{
		final public long id;
		final public CurrencyPair currency_pair;
		final public TradeType action;
		final public double amount;
		final public double price;
		final public double fee;
		final public TradeType your_action;
		final public double bonus;
		final public Date timestamp;
		public Item(long i_id,JSONObject i_jso){
			this.id=i_id;
			this.currency_pair=CurrencyPair.strToVal(i_jso.getString("currency_pair"));
			this.action=TradeType.strToVal(i_jso.getString("action"));
			this.amount=i_jso.getDouble("amount");
			this.price=i_jso.getDouble("price");
			this.fee=i_jso.getDouble("fee");
			this.your_action=TradeType.strToVal(i_jso.getString("your_action"));
			this.bonus=i_jso.isNull("bonus")?Double.NaN:i_jso.getDouble("bonus");
			this.timestamp=new Date(i_jso.getLong("timestamp")*1000);
		}
	}
	final public List<Item> history;
	public TradeHistoryResult(JSONObject i_jso)
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
