package jp.nyatla.jzaif.io;

/**
 * {@link IWebsocketClient}からの通知を受け取るインタフェイスです。
 */
public interface IWebsocketObserver {
	public void onStringPacket(String i_value);
	public void onError(String i_message);
}
