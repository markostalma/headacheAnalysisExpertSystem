package com.headacheExpertSystem;

import java.awt.Font;

import javax.swing.JLabel;

public class JLabelHeadache extends JLabel {
	private static final long serialVersionUID = 1L;
	
	public JLabelHeadache(String text) {
		labelInit();
		setText(text);
	}
	private void labelInit() {
		setHorizontalAlignment(JLabelHeadache.CENTER);
		setFont(new Font("Helvetica Neue", Font.BOLD, 12));
	}

}
