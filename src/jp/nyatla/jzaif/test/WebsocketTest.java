package jp.nyatla.jzaif.test;

import jp.nyatla.jzaif.io.IWebsocketObserver;
import jp.nyatla.jzaif.io.JsrWebsocketClient;

public class WebsocketTest {
	private class TestOvserver implements IWebsocketObserver{

		@Override
		public void onStringPacket(String i_value) {
			System.out.println(i_value);
		}

		@Override
		public void onError(String i_message) {
		}
	}
	public static void main(String[] args)
	{
		JsrWebsocketClient lp=new JsrWebsocketClient();
		lp.connect("ws://api.zaif.jp:8888/stream?currency_pair=btc_jpy",new IWebsocketObserver(){
			@Override
			public void onStringPacket(String i_value) {
				System.out.println(i_value);
			}
			@Override
			public void onError(String i_message) {
			}});
		try {
			Thread.sleep(1000000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
}
