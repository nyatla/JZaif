# JZaif

Java用のZaifAPIラッパーです。PublicAPI、ExchangeAPI、StreamingAPI用のラッパーがあります。
対応する通貨ペアは BTC-JPY/MONA-JPY/BTC-MONA/XEM-JPYです。

* <a href="https://zaif.jp/">Zaif.jp</a>
* <a href="https://corp.zaif.jp/api-docs/">Zaif Exchange APIドキュメント</a>


## セットアップ
zipをダウンロードして展開し、.projectをインポートしてください。

外部ライブラリは、libNyansat,JSON-java,tyrus-standalone-clientです。
すべてextlibディレクトリに同梱してます。

 * TwitterNyansat　https://github.com/nyatla/TwitterNyansat
 * JSON-java　https://github.com/stleary/JSON-java
 * tyrus-standalone-client https://tyrus.java.net/dependencies.html

## 使い方の例

### PublicAPI

#### 現在の終値を得る
    public static void main(String[] args)
    {
    	PublicApi lp=new PublicApi(CurrencyPair.BTCJPY);
    	double r1=lp.lastPrice();
    	System.out.println(r1);
    	return;
    }



### ExchangeAPI

ExchangeAPIにはAPIキーが必要です。

#### ビットコインを購入する

    public static void main(String[] args)
    {
    	ApiKey k=new ApiKey("YOUR API_KEY","YOUR SECRET KEY");
    	ExchangeApi lp=new ExchangeApi(k);
    	//30000JPYで0.01BTC
    	TradeResult r=lp.trade(CurrencyPair.BTCJPY,TradeType.BID,30000,0.01);
    	System.out.println(r.success?String.format("orderid=%d",r.order_id):"method failed");
    	return;
    }


### StreamingAPI

#### 取引所からBTCJPYの更新を受け取る

    public class StreamingApiTest {
    	static public class Sa extends StreamingApi
    	{
    		public Sa(CurrencyPair i_cpair) {
    			super(i_cpair);
    		}
    		@Override
    		public void onUpdate(String i_data)
    		{
    			//生JSONテキスト
    			System.out.println(i_data);
    		}
    		@Override
    		public void onUpdate(StreamingNotify i_data)
    		{
    			return;
    		}
    	}
    	public static void main(String[] args)
    	{
    		StreamingApi lp=new Sa(CurrencyPair.BTCJPY);
    		try {
    			Thread.sleep(100000000);
    		} catch (InterruptedException e) {
    		}
    		lp.shutdown();
    		return;
    	}
    }


## ライセンス
2条項BSDです。


BTC 15ALiKAYNLfye18Bh3jepxquy1azocWXk7

MONA MN7KgERFbUnWRXajP4hBa3GxAxSfkNsq7Y


