package jp.nyatla.jzaif.types;

public enum Currency {
	JPY("jpy"),
	BTC("btc"),
	MONA("mona"),
	XEM("xem");
	final public String shortname;
	private Currency(String i_strname)
	{
		this.shortname=i_strname;
	}
	public static Currency strToVal(String keystr)
	{
		for(Currency i:Currency.values()){
			if(i.shortname.compareToIgnoreCase(keystr)==0){
				return i;
			}
		}
		throw new IllegalArgumentException();
	}
}
