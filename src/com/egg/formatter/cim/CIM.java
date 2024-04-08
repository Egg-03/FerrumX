package com.egg.formatter.cim;

import java.io.IOException;

public class CIM {
	private CIM() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static String getValues(String WMI_Class, String WMI_Attribute) throws IOException {
		return CIMFormat.accessrunCommand(WMI_Class, WMI_Attribute);
	}
	
	public static String getValues(String WMI_Class, String whereCondition, String WMI_Attribute) throws IOException{
		return CIMFormat.accessrunCommand(WMI_Class, whereCondition, WMI_Attribute);
	}
}
