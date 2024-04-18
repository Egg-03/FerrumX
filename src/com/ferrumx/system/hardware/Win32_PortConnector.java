package com.ferrumx.system.hardware;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ferrumx.formatter.cim.CIM_ML;

//represents a set of all the ports available in a motherboard
public class Win32_PortConnector {
	private static String classname = "Win32_PortConnector";
	private static String attributes = "Tag, ExternalReferenceDesignator, InternalReferenceDesignator";
	private Win32_PortConnector() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getBaseboardPortID() throws IOException, IndexOutOfBoundsException{
		return CIM_ML.getID(classname, "Tag");
	}
	
	public static Map<String, String> getBaseboardPorts(String portID) throws IOException, IndexOutOfBoundsException{
		return CIM_ML.getWhere(classname, "Tag", portID, attributes);	
	}
}
