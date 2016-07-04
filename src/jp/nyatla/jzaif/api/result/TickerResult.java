package jp.nyatla.jzaif.api.result;

import org.json.JSONObject;

public class TickerResult
{
	/** 終値(last_priceに相当)*/
	final public double last;
	/** 過去24時間の高値*/
	final public double high;
	/** 過去24時間の安値*/
	final public double low;
	/** 過去24時間の加重平均*/
	final public double vwap;
	/** 過去24時間の出来高*/
	final public double volume;
	/** 買気配値*/
	final public double bid;
	/** 売気配値*/
	final public double ask;
	public TickerResult(JSONObject i_jso)
	{
		this.last=i_jso.getDouble("last");
		this.high=i_jso.getDouble("high");
		this.low=i_jso.getDouble("low");
		this.vwap=i_jso.getDouble("vwap");
		this.volume=i_jso.getDouble("volume");
		this.bid=i_jso.getDouble("bid");
		this.ask=i_jso.getDouble("ask");
		return;
	}
	@Override
	public String toString()
	{
		return String.format("last:%f\thigh:%f\tlow:%f\tvwap:%f\tvoulme:%f\tbid:%f\task:%f\t",
				this.last,this.high,this.low,this.vwap,this.volume,this.bid,this.ask);
	}
}
