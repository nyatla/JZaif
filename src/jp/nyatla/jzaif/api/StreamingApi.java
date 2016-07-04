package jp.nyatla.jzaif.api;

import org.json.JSONObject;

import jp.nyatla.jzaif.api.result.StreamingNotify;
import jp.nyatla.jzaif.io.IWebsocketObserver;
import jp.nyatla.jzaif.io.JsrWebsocketClient;
import jp.nyatla.jzaif.types.CurrencyPair;

/**
 * StreamingAPIを受信します。
 *
 */
public class StreamingApi
{
	final private String API_URL_PREFIX="ws://api.zaif.jp:8888/stream?currency_pair=";
	final private JsrWebsocketClient _client;
	public StreamingApi(CurrencyPair i_cpair)
	{
		JsrWebsocketClient cl=new JsrWebsocketClient();
		cl.connect(API_URL_PREFIX+i_cpair.shortname,new WsObserver(this));
		this._client=cl;
	}
	public void shutdown()
	{
		this._client.disconnect();
	}
	private class WsObserver implements IWebsocketObserver
	{
		final StreamingApi _parent;
		WsObserver(StreamingApi i_parent){
			this._parent=i_parent;
		}
		@Override
		public void onStringPacket(String i_value)
		{
			this._parent.onUpdate(i_value);
			JSONObject jso=new JSONObject(i_value);
			this._parent.onUpdate(new StreamingNotify(jso));
			return;
		}
		@Override
		public void onError(String i_message)
		{
		}
	}
	/**
	 * Notyfyの受信を生JSONで通知します。受信するにはこの関数をオーバライドしてください。
	 * @param i_data
	 */
	public void onUpdate(String i_data)
	{
	}
	/**
	 * Notyfyの受信をパースして通知します。受信するにはこの関数をオーバライドしてください。
	 * @param i_data
	 */
	public void onUpdate(StreamingNotify i_data)
	{
	}
	
}
