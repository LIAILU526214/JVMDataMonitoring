package com.i5lu.properties;

public class JstatResultTableProperties {
	/**
	 * jstat -gcutil pid <br/>
	 * 统计gc信息统计。
	 */
	public final static String[] JSTAT_GCUTIL_TAB = {"S0","S1","E","O","P","YGC","YGCT","FGC","FGCT","GCT"};
	/**
	 * jstat -gc pid <br/>
	 * 显示gc的信息，查看gc的次数，及时间。其中最后五项，分别是young gc的次数，young gc的时间，full gc的次数，full
	 * gc的时间，gc的总时间
	 */
	public final static String[] JSTAT_GC_TAB = {"S0C","S1C","S0U","S1U","EC","EU","OC","OU","PC","PU","YGC","YGCT","FGC","FGCT","GCT"};
	/**
	 * jstat -gccapacity pid <br/>
	 * 显示VM内存中三代（young,old,perm）对象的使用和占用大小，如：PGCMN显示的是最小perm的内存使用量，PGCMX显示的是perm的内存最大使用量，PGC是当前新生成的perm内存占用量，PC是但前perm内存占用量。其他的可以根据这个类推，
	 * OC是old内纯的占用量。
	 */
	public final static String[] JSTAT_GCCAPACITY_TAB = {"NGCMN","NGCMX","NGC","S0C","S1C","EC","OGCMN","OGCMX","OGC","OC","PGCMN","PGCMX","PGC","PC","YGC","FGC"};
	/**
	 * jstat -gcnew pid <br/>
	 * 年轻代对象的信息
	 */
	public final static String[] JSTAT_GCNEW_TAB = {"S0C","S1C","S0U","S1U","TT","MTT","DSS","EC","EU","YGC","YGCT"};
	/**
	 * jstat -gcnewcapacity pid <br/>
	 * 年轻代对象的信息及其占用量
	 */
	public final static String[] JSTAT_GCNEWCAPACITY_TAB = {"NGCMN","NGCMX","NGC","S0CMX","S0C","S1CMX","S1C","ECMX","EC","YGC","FGC"};
	/**
	 * jstat -gcold pid <br/>
	 * old代对象的信息
	 */
	public final static String[] JSTAT_GCOLD_TAB = {"PC","PU","OC","OU","YGC","FGC","FGCT","GCT"};
	/**
	 * jstat -gcpermcapacity pid <br/>
	 * perm对象的信息及其占用量
	 */
	public final static String[] JSTAT_GCPERMCAPACITY_TAB = {"OGCMN","OGCMX","OGC","OC","YGC","FGC","FGCT","GCT"};
	/**
	 * jstat -gcoldcapacity pid <br/>
	 * old代对象的信息及其占用量
	 */
	public final static String[] JSTAT_GCOLDCAPACITY_TAB = {"PGCMN","PGCMX","PGC","PC","YGC","FGC","FGCT","GCT"};
	/**
	 * jstat -class pid <br/>
	 * 显示加载class的数量，及所占空间等信息
	 */
	public final static String[] JSTAT_CLASS_TAB = {"Loaded","Bytes","Unloaded","Bytes","Time"};
	/**
	 * jstat -compiler pid <br/>
	 * 显示VM实时编译的数量等信息
	 */
	public final static String[] JSTAT_COMPILER_TAB = {"Compiled","Failed","Invalid","Time","FailedType","FailedMethod"};
	/**
	 * jstat -printcompilation pid <br/>
	 * 当前VM执行的信息
	 */
	public final static String[] JSTAT_PRINTCOMPILATION_TAB = {"Compiled","Size","Type","Method"};
}
