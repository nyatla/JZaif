package jp.nyatla.jzaif.io;

import java.io.IOException;
import java.net.URI;

import javax.websocket.*;



public class JsrWebsocketClient implements IWebsocketClient{
	private Session _session;
	public JsrWebsocketClient() {
	}

	public void connect(String i_url, IWebsocketObserver i_observer) {
		assert(i_observer!=null);
		if (this._session != null) {
			throw new RuntimeException();
		}
		URI uri = URI.create(i_url);
		WebSocketContainer cn = ContainerProvider.getWebSocketContainer();
		try {
			Session session = cn.connectToServer(new WsClient(i_observer), uri);
			// OpenCheck?
			long s=System.currentTimeMillis();
			//10secの接続待ち
			do{
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
				if(session.isOpen()){
					this._session = session;
					return;
				}
			}while(System.currentTimeMillis()-s<10*1000);
			this._session.close();
			throw new RuntimeException();
		} catch (DeploymentException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
	public void disconnect() {
		if (this._session != null) {
			try {
				this._session.close();
			} catch (IOException e) {
			}
			this._session = null;
		}
	}
	@Override
	public boolean isConnectionEnable()
	{
		return this._session.isOpen();
	}
	
	@ClientEndpoint
	public class WsClient {
		final private IWebsocketObserver _observer;	
		public WsClient(IWebsocketObserver i_observer)
		{
			this._observer=i_observer;
		}

		@OnOpen
		public void onOpen(Session session) {
		}
		@OnMessage
		public void onMessage(String message)
		{
			this._observer.onStringPacket(message);
			//しくじったときここでcloseかけられるのかな？
		}
		@OnError
		public void onError(Throwable t)
		{
			this._observer.onError("An error was occurred.");
		}
		@OnClose
		public void onClose(Session session) {
		}
	}




}
