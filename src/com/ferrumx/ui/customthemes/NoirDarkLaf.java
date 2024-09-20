package com.ferrumx.ui.customthemes;

import com.formdev.flatlaf.FlatDarkLaf;

public class NoirDarkLaf extends FlatDarkLaf {

	private static final long serialVersionUID = 3401914753511117885L;

	public static boolean setup() {
        return setup( new NoirDarkLaf() );
    }
	
	@Override
    public String getName() {
        return "NoirDarkLaf";
    }
}

