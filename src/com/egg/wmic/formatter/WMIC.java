package com.egg.wmic.formatter;

import java.io.IOException;

public class WMIC {
	public static String getValues(String WMIC_Class, String WMIC_Attribute) throws IOException {
		return WMICFormat.accessrunCommand(WMIC_Class, WMIC_Attribute);
	}
}
