package jp.nyatla.jzaif.types;

public enum CurrencyPair {
	BTCJPY(Currency.BTC,Currency.JPY),
	MONAJPY(Currency.MONA,Currency.JPY),
	MONABTC(Currency.MONA,Currency.BTC),
	XEMJPY(Currency.XEM,Currency.JPY);
	final public String shortname;
	private CurrencyPair(Currency l,Currency r)
	{
		this(l.shortname+"_"+r.shortname);
		
	}
	private CurrencyPair(String i_strname)
	{
		this.shortname=i_strname;
	}
	public static CurrencyPair strToVal(String keystr)
	{
		for(CurrencyPair i:CurrencyPair.values()){
			if(i.shortname.compareToIgnoreCase(keystr)==0){
				return i;
			}
		}
		throw new IllegalArgumentException();
	}
}
