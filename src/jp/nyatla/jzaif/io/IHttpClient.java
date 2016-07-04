package jp.nyatla.jzaif.io;

/**
 * JZaifの使用するHttpコネクションのインタフェイスです。
 */
public interface IHttpClient
{
	/**
	 * 指定したURLからGETリクエストでテキストを取得します。
	 * @param i_url
	 * @return
	 */
	public String getText(String i_url);
	/**
	 * 指定したURLからPOSTリクエストでテキストデータを取得して返します。
	 * @param i_url
	 * @param i_key
	 * Keyヘッダメッセージの値を指定します。
	 * @param i_sign
	 * Signヘッダメッセージの値を指定します。
	 * @param i_msg
	 * POSTリクエストbodyを指定します。
	 * @return
	 */
	public String postText(String i_url,String i_key,String i_sign,String i_msg);
}
