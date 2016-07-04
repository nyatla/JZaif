package jp.nyatla.jzaif.types;

public enum SortOrder {
	ASC("ASC"),
	DESC("DESC");
	final public String strname;
	private SortOrder(String i_strname)
	{
		this.strname=i_strname;
	}
	public static SortOrder strToVal(String keystr)
	{
		for(SortOrder i:SortOrder.values()){
			if(i.strname.compareToIgnoreCase(keystr)==0){
				return i;
			}
		}
		throw new IllegalArgumentException();
	}
}
