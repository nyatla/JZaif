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
package jp.nyatla.jzaif.types;

import java.math.BigDecimal;

/**
 * 通貨ペアを列挙するクラスです。
 */
public enum CurrencyPair implements NamedEnum.Interface
{
	BTCJPY(Currency.BTC,Currency.JPY){
		public double toOrderPrice(double s){
			return Math.round(s/5f)*5;
		}
	},
	MONAJPY(Currency.MONA,Currency.JPY){
		public double toOrderPrice(double s){
			return new BigDecimal(s).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
	},
	XEMJPY(Currency.XEM,Currency.JPY){
		public double toOrderPrice(double s){
			return new BigDecimal(s).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
	},
	XEMBTC(Currency.XEM,Currency.BTC){
		public double toOrderPrice(double s){
			return new BigDecimal(s).setScale(8, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
	},
	MONABTC(Currency.MONA,Currency.BTC){
		public double toOrderPrice(double s){
			return new BigDecimal(s).setScale(8, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
	};

	/** Zaifでの通貨ペア名を文字列で保持します。*/
	final public String symbol;
	final public int id;
	private CurrencyPair(Currency l,Currency r)
	{
		this(l.symbol+"_"+r.symbol,((l.id<<8) |r.id));
	}
	private CurrencyPair(String i_symbol,int i_id)
	{
		this.symbol=i_symbol;
		this.id=i_id;
	}
	@Override
	final public int getId(){
		return this.id;
	}
	@Override
	final public String getSymbol() {
		return this.symbol;
	}
	/** この通貨ペアの取引通貨単位に正規化します。*/
	public abstract double toOrderPrice(double s);
	public static CurrencyPair toEnum(String i_symbol) {
		return NamedEnum.toEnum(CurrencyPair.class,i_symbol);
	}
	public static CurrencyPair toEnum(int i_id) {
		return NamedEnum.toEnum(CurrencyPair.class,i_id);
	}
	public static void main(String[] args)
	{
		for(int i=0;i<10;i++){
			System.out.println(CurrencyPair.BTCJPY.toOrderPrice(Math.random()*100));
		}
		System.out.println();
		for(int i=0;i<10;i++){
			System.out.println(CurrencyPair.MONAJPY.toOrderPrice(Math.random()*10));
		}
		System.out.println();
		for(int i=0;i<10;i++){
			System.out.println(CurrencyPair.XEMJPY.toOrderPrice(Math.random()*2));
		}
		System.out.println();
		for(int i=0;i<10;i++){
			System.out.println(CurrencyPair.XEMBTC.toOrderPrice(Math.random()*1));
		}
		for(int i=0;i<10;i++){
			System.out.println(CurrencyPair.MONABTC.toOrderPrice(Math.random()*1));
		}
		return;
	}

}
