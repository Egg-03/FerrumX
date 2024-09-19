package com.ferrumx.ui.customthemes;

import com.formdev.flatlaf.FlatLightLaf;

public class BellLaf extends FlatLightLaf {
	
 static final long serialVersionUID = -463444598715390747L;

	public static boolean setup() {
        return setup( new BellLaf() );
    }
	
	@Override
    public String getName() {
        return "BellLaf";
    }

}
