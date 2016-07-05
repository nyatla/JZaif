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

import org.json.JSONObject;

/**
 * ExchangeAPIのベースクラスです。
 */
public class ExchangeCommonResult {
	/** APIが成功したかのフラグ値です。*/
	final public boolean success;
	/** 失敗理由を格納するテキスト。{@link #success}がfalseのときのみ有効。*/
	final public String error_text;
	public ExchangeCommonResult(boolean i_success,String i_error_text)
	{
		this.success=i_success;
		this.error_text=i_error_text;
	}
	/** パース済みJSONからインスタンスを構築します。*/
	public ExchangeCommonResult(JSONObject i_jso)
	{
		boolean s=i_jso.getInt("success")==1;
		String e=(s?null:i_jso.getString("error"));
		this.success=s;
		this.error_text=e;
	}
	public class Funds{
		final public double jpy;
		final public double btc;
		final public double mona;
		public Funds(JSONObject i_jso)
		{
			this.jpy=i_jso.getDouble("jpy");
			this.btc=i_jso.getDouble("btc");
			this.mona=i_jso.getDouble("mona");
		}
	}	
}

