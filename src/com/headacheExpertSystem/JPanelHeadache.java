package com.headacheExpertSystem;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class JPanelHeadache extends JPanel {
	private static final long serialVersionUID = 1L;

	public JPanelHeadache() {
		panelInit();
	}
	
	private void panelInit() {
		setVisible(false);
		setLayout(new GridBagLayout());
	}
}
