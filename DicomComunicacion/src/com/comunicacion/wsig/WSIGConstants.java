
package com.comunicacion.wsig;

import java.text.SimpleDateFormat;

public interface WSIGConstants {

	public static final String AGENT_TYPE = "WSIG Agent";
	public static final String WSIG_FLAG = "wsig";
	public static final String WSIG_MAPPER = "wsig-mapper";
	public static final String WSIG_PREFIX = "wsig-prefix";
	
	public static final SimpleDateFormat ISO8601_DATE_FORMAT = new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss.SSS");
}
