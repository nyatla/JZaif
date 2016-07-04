package jp.nyatla.jzaif.io;

public interface IWebsocketClient {
	public void connect(String i_ws_url,IWebsocketObserver i_callback);
	public void disconnect();
	public boolean isConnectionEnable();
}
