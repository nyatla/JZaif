package jp.nyatla.jzaif.io;


import jp.nyatla.nyansat.client.BasicHttpClient;

/**
 * NyanStockを使うHTTP入出力インタフェイスの実装です。
 */
public class NyansatHttpClient implements IHttpClient
{
	final private BasicHttpClient _cl;
	public NyansatHttpClient()
	{
		this._cl=new BasicHttpClient();
	}
	/**
	 * 指定したURLからGETリクエストでテキストデータを取得して返します。
	 * @param i_url
	 * @return
	 */
	public String getText(String i_url)
	{
		return this._cl.getTextContents(i_url);
	}
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
	public String postText(String i_url,String i_key,String i_sign,String i_msg)
	{
		String[][] header={{"Key",i_key},{"Sign",i_sign}};
		return this._cl.postTextContents(i_url,"UTF-8",header,i_msg);
	}
}
