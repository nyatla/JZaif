package jp.nyatla.jzaif.types;

public enum TradeType
{
	BID("bid"),
	ASK("ask");
	final public String strname;
	private TradeType(String i_strname)
	{
		this.strname=i_strname;
	}
	public static TradeType strToVal(String keystr)
	{
		for(TradeType i:TradeType.values()){
			if(i.strname.compareToIgnoreCase(keystr)==0){
				return i;
			}
		}
		throw new IllegalArgumentException();
	}
}
