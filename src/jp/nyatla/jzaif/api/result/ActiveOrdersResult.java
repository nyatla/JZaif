/*
 * Copyright (c) 2016, nyatla
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met: 
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer. 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution. 
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * The views and conclusions contained in the software and documentation are those
 * of the authors and should not be interpreted as representing official policies, 
 * either expressed or implied, of the FreeBSD Project.
 */
package jp.nyatla.jzaif.api.result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.nyatla.jzaif.types.CurrencyPair;
import jp.nyatla.jzaif.types.TradeType;

import org.json.JSONObject;

/**
 * ExchangeAPIのactive_ordersコマンドの戻り値を格納するクラスです。
 * 拡張パラメータは{@link #success}がtrueのときのみ有効です。
 */
public class ActiveOrdersResult extends ExchangeCommonResult
{
	/** order1項目分のデータを格納します。*/
	public class Item
	{
		final public long id;
		final public CurrencyPair currency_pair;
		final public TradeType action;
		final public double amount;
		final public double price;
		final public Date timestamp;
		public Item(long i_id,JSONObject i_jso)
		{
			this.id=i_id;
			this.currency_pair=CurrencyPair.toEnum(i_jso.getString("currency_pair"));
			this.action=TradeType.toEnum(i_jso.getString("action"));
			this.amount=i_jso.getDouble("amount");
			this.price=i_jso.getDouble("price");
			this.timestamp=new Date(i_jso.getLong("timestamp")*1000);
		}
	}
	/** orders要素の配列です。*/
	final public List<Item> orders;
	/** パース済みJSONからインスタンスを構築します。*/
	public ActiveOrdersResult(JSONObject i_jso)
	{
		super(i_jso);
		if(!this.success){			
			this.orders=null;
			return;
		}
		this.orders=new ArrayList<Item>();
		JSONObject jso=i_jso.getJSONObject("return");
		for(String n : jso.keySet())
		{
			this.orders.add(new Item(Long.parseLong(n),jso.getJSONObject(n)));
		}
		return;	 
	}
	/**
	 * オーダーidに一致するオーダーアイテムを返します。
	 * @param i_id
	 * @return
	 * ないときはNULL
	 */
	public Item getOrderItemById(long i_id)
	{
		for(Item i:this.orders){
			if(i.id==i_id){
				return i;
			}
		}
		return null;
	}

}
