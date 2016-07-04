package jp.nyatla.jzaif.api.result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.nyatla.jzaif.Utils;
import jp.nyatla.jzaif.types.CurrencyPair;
import jp.nyatla.jzaif.types.TradeType;

import org.json.JSONArray;
import org.json.JSONObject;

public class StreamingNotify {
	/**last_price.priceの値*/
	final public double last_price;
	/**last_price.actionの値*/
	final public TradeType last_action;
	final public Date timestamp;
	final public DepthResult depth;
	final public TradesResult trades;
	final public List<String> target_users;
	final public CurrencyPair currenty_pair;
	public StreamingNotify(JSONObject i_jso)
	{
		//asks,bids
		this.depth=new DepthResult(i_jso);
		this.trades=new TradesResult(i_jso.getJSONArray("trades"));
		{
			this.target_users=new ArrayList<String>();
			JSONArray a=i_jso.getJSONArray("target_users");
			for(int i=0;i<a.length();i++){
				this.target_users.add(a.getString(i));
			}
		}
		this.currenty_pair=CurrencyPair.strToVal(i_jso.getString("currency_pair"));
		JSONObject lp=i_jso.getJSONObject("last_price");
		this.last_action=TradeType.strToVal(lp.getString("action"));
		this.last_price=lp.getDouble("price");
		this.timestamp=Utils.parseZaifFullTimeText(i_jso.getString("timestamp"));
		return;
	}
}
