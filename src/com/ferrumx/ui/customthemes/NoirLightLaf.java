package com.ferrumx.ui.customthemes;

import com.formdev.flatlaf.FlatLightLaf;

public class NoirLightLaf extends FlatLightLaf {
	
	private static final long serialVersionUID = -1478279311994811632L;

	public static boolean setup() {
        return setup( new NoirLightLaf() );
    }
	
	@Override
    public String getName() {
        return "NoirLightLaf";
    }
}
