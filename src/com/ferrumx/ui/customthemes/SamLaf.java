package com.ferrumx.ui.customthemes;

import com.formdev.flatlaf.FlatDarkLaf;

public class SamLaf extends FlatDarkLaf {

	private static final long serialVersionUID = -7048312963822249856L;

	public static boolean setup() {
        return setup( new SamLaf() );
    }
	
	@Override
    public String getName() {
        return "SamLaf";
    }
}

