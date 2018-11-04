package com.headacheExpertSystem;
import javax.swing.JSlider;

public class JSliderHeadache extends JSlider {
	private static final long serialVersionUID = 1L;

	public JSliderHeadache() {
		sliderInit();
	}

	private void sliderInit() {
		setMajorTickSpacing(5);
		setMinorTickSpacing(1);
		setPaintLabels(true);
		setPaintTicks(true);
		setOrientation(HORIZONTAL);
		setMinimum(0);
		setMaximum(10);
		setValue(0);
	}

}
