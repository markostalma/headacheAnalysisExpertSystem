package com.headacheExpertSystem;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.util.Hashtable;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Rule;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class HeadacheExpertSystem {

	// JFuzzy Logic Staff
	String fileName;
	FIS fis;
	
	// Swing GUI Components
	private JFrame frame;
	private JPanel content;
	private JMenuBar menuBar;
	private JMenu about, help, exit;
	private JMenuItem aboutProgram, aboutAuthor, helpHelp, exitExit;
	private JPanelHeadache panelGender, panelAge, panelIntensity, panelDuration, panelPulsating, panelPressing, panelNauseaVomiting, panelNoiseLight, panelHeating, panelMonthlyHeadaches, panelHeadPart, panelWorsenHeadache, panelSetData;
	private JLabelHeadache labelGender, labelAge, labelIntensity, labelDuration, labelPulsating, labelPressing, labelNauseaVomiting, labelNoiseLight, labelHeating, labelMonthlyHeadaches, labelHeadPart, labelWorsenHeadache, labelSetData;
	private JButton buttonGender, buttonAge, buttonIntensity, buttonDuration, buttonPulsating, buttonPressing, buttonNauseaVomiting, buttonNoiseLight, buttonHeating, buttonMonthlyHeadaches, buttonHeadPart, buttonWorsenHeadache, buttonSetData;
	private JRadioButton rdbMale, rdbFemale, physicalActivity, coldStuff, timeChanges, stressEffort, none;
	private JSliderHeadache sliderIntensity, sliderDuration, sliderPulsating, sliderPressing, sliderNauseaVomiting, sliderNoiseLight, sliderHeating, sliderMonthlyHeadaches, sliderHeadPart;
	private ButtonGroup buttonGroupGender, buttonGroupWorsenHeadache;
	private Hashtable<Integer, JComponent> tableIntensity, tableDuration, tablePulsating, tablePressing, tableNauseaVomiting, tableNoiseLight, tableHeating, tableHeadPart;
	private JTextFieldHeadache textFieldAge;

	// Init values from answers
	public static Double valueGender;
	public String inpVal = "";
	public static Double valueAge;
	public static Double valueWorsenHeadache;
	public String dur_first;
	public String dur_second;
	public String dur_third;
	public String dur_fourth;
	public double duration;

	
	public HeadacheExpertSystem() {	

		fileName = "rules.fcl";
		fis = FIS.load(fileName, true);
		if (fis == null) {
			System.err.println("Can't load file: '" + fileName + "'");
			return;
		}
		
		frame = new JFrame("Headche Type - Expert System");
		content = new JPanel();
		menuBar = new JMenuBar();
		about = new JMenu("About");
		aboutProgram = new JMenuItem("About Program");
		aboutAuthor = new JMenuItem("About Author");
		help = new JMenu("Help");
		helpHelp = new JMenuItem("Quick Help");
		exit = new JMenu("Exit");
		exitExit = new JMenuItem("Click to Exit");
		
		panelGender = new JPanelHeadache();
		labelGender = new JLabelHeadache("Please select your gender");
		rdbMale = new JRadioButton("Male");
		rdbFemale = new JRadioButton("Female");
		buttonGroupGender = new ButtonGroup();
		buttonGender = new JButton("Next Question");
		
		panelAge = new JPanelHeadache();
		labelAge = new JLabelHeadache("What is your age?");
		textFieldAge = new JTextFieldHeadache();
		textFieldAge .setColumns(15);
		textFieldAge .setFormat(JTextFieldHeadache.NUMERIC);
		textFieldAge .setMaxLength(2);
		buttonAge = new JButton("Next Question");
		
		panelIntensity = new JPanelHeadache();
		labelIntensity = new JLabelHeadache("Select intensity of your headaches");
		sliderIntensity = new JSliderHeadache();
		sliderIntensity.setMinorTickSpacing(1);
		sliderIntensity.setMinimum(1);
		tableIntensity = new Hashtable<Integer, JComponent>();
		tableIntensity.put(new Integer(1), new JLabel("Low"));
		tableIntensity.put(new Integer(5), new JLabel("Medium"));
		tableIntensity.put(new Integer(10), new JLabel("High"));
		buttonIntensity = new JButton("Next Question");
		
		panelDuration = new JPanelHeadache();
		labelDuration = new JLabelHeadache("Select usually duration of your headaches:");
		JRadioButton rdbMinutes = new JRadioButton("Minutes");
		JRadioButton rdbHours = new JRadioButton("Hours");
		rdbMinutes.setMnemonic(KeyEvent.VK_M);
		rdbHours.setMnemonic(KeyEvent.VK_H);
		rdbMinutes.setSelected(true);
		sliderDuration = new JSliderHeadache();
		sliderDuration.setMajorTickSpacing(20);
		sliderDuration.setMinorTickSpacing(1);
		sliderDuration.setMaximum(10);
		if ( rdbMinutes.isSelected() ) {
			dur_first = "Couple of minutes";
			dur_second = "Couple of minutes";
			dur_third = "Couple of minutes";
			dur_fourth = "Couple of minutes";
		}
		else if ( rdbHours.isSelected() ) {
			dur_first = "Couple of hours";
			dur_second = "One day";
			dur_third = "More than 48h";
			dur_fourth = "More than 7 days";
		}
		tableDuration = new Hashtable<Integer, JComponent>();
		tableDuration.put(new Integer(0), new JLabel(dur_first));
		tableDuration.put(new Integer(7), new JLabel(dur_second));
		tableDuration.put(new Integer(14), new JLabel(dur_third));
		tableDuration.put(new Integer(20), new JLabel(dur_fourth));
		sliderDuration.setLabelTable(tableDuration);
		buttonDuration = new JButton("Next Question");
		
        panelPulsating = new JPanelHeadache();
		labelPulsating = new JLabelHeadache("Do you have a pulsating headaches?");
		sliderPulsating = new JSliderHeadache();
		sliderPulsating.setMaximum(11);
		tablePulsating = new Hashtable<Integer, JComponent>();
		tablePulsating.put(new Integer(1), new JLabel("<html>No<br>Rarely</html>"));
		tablePulsating.put(new Integer(4), new JLabel("Sometimes"));
		tablePulsating.put(new Integer(8), new JLabel("Often"));
		tablePulsating.put(new Integer(11), new JLabel("Always"));
		buttonPulsating = new JButton("Next Question");
		
		panelPressing = new JPanelHeadache();
		labelPressing = new JLabelHeadache("Do you have a pressing with headache?");
		sliderPressing = new JSliderHeadache();
		sliderPressing.setMaximum(11);
		tablePressing = new Hashtable<Integer, JComponent>();
		tablePressing.put(new Integer(1), new JLabel("<html>No<br>Rarely</html>"));
		tablePressing.put(new Integer(4), new JLabel("Sometimes"));
		tablePressing.put(new Integer(8), new JLabel("Often"));
		tablePressing.put(new Integer(11), new JLabel("Always"));
		buttonPressing = new JButton("Next Question");
		
		panelNauseaVomiting = new JPanelHeadache();
		labelNauseaVomiting = new JLabelHeadache("Do you have a nausea and vomiting with headache?");
		sliderNauseaVomiting = new JSliderHeadache();
		sliderNauseaVomiting.setMaximum(11);
		tableNauseaVomiting = new Hashtable<Integer, JComponent>();
		tableNauseaVomiting.put(new Integer(1), new JLabel("No"));
		tableNauseaVomiting.put(new Integer(3), new JLabel("<html><br>Rarely</html>"));
		tableNauseaVomiting.put(new Integer(6), new JLabel("Sometimes"));
		tableNauseaVomiting.put(new Integer(9), new JLabel("<html><br>Often</html>"));
		tableNauseaVomiting.put(new Integer(11), new JLabel("Always"));
		buttonNauseaVomiting = new JButton("Next Question");
		
		panelNoiseLight = new JPanelHeadache();
		labelNoiseLight = new JLabelHeadache("Select Noise and Light of your headache");
		sliderNoiseLight = new JSliderHeadache();
		sliderNoiseLight.setMaximum(11);
		tableNoiseLight = new Hashtable<Integer, JComponent>();
		tableNoiseLight.put(new Integer(1), new JLabel("No"));
		tableNoiseLight.put(new Integer(3), new JLabel("<html><br>Rarely</html>"));
		tableNoiseLight.put(new Integer(6), new JLabel("Sometimes"));
		tableNoiseLight.put(new Integer(9), new JLabel("<html><br>Often</html>"));
		tableNoiseLight.put(new Integer(11), new JLabel("Always"));
		buttonNoiseLight = new JButton("Next Question");
		
		panelHeating = new JPanelHeadache();
		labelHeating = new JLabelHeadache("Do you have body heating with headache?");
		tableHeating = new Hashtable<Integer, JComponent>();
		tableHeating.put(new Integer(1), new JLabel("No"));
		tableHeating.put(new Integer(5), new JLabel("Sometimes"));
		tableHeating.put(new Integer(10), new JLabel("Yes"));
		sliderHeating = new JSliderHeadache();
		buttonHeating = new JButton("Next Question");
		
		panelMonthlyHeadaches = new JPanelHeadache();
		labelMonthlyHeadaches = new JLabelHeadache("Select number of headaches per month:");
		sliderMonthlyHeadaches = new JSliderHeadache();
		sliderMonthlyHeadaches.setMinorTickSpacing(1);
		buttonMonthlyHeadaches = new JButton("Next Question");
		
		panelHeadPart = new JPanelHeadache();
		labelHeadPart = new JLabelHeadache("Select usually painful head part:");
		sliderHeadPart = new JSliderHeadache();
		tableHeadPart = new Hashtable<Integer, JComponent>();
		tableHeadPart.put(new Integer(0), new JLabel("<html>One<br>side</html>"));
		tableHeadPart.put(new Integer(3), new JLabel("<html>Around<br>Eye</html> "));
		tableHeadPart.put(new Integer(7), new JLabel("Forehead"));
		tableHeadPart.put(new Integer(10), new JLabel("<html>Both<br>side</html>"));
		buttonHeadPart = new JButton("Next Question");
		
		panelWorsenHeadache = new JPanelHeadache();
		labelWorsenHeadache = new JLabelHeadache("What can make your headache worse?");
		physicalActivity = new JRadioButton("Physicial Activity");
		coldStuff = new JRadioButton("Cold Stuff");
		timeChanges = new JRadioButton("Time Changes");
		stressEffort = new JRadioButton("Stress Effort");
		none = new JRadioButton("None");
		buttonGroupWorsenHeadache = new ButtonGroup();
		buttonWorsenHeadache = new JButton("Next Question");
		
		panelSetData = new JPanelHeadache();
		labelSetData = new JLabelHeadache("Check a your results");		
		buttonSetData = new JButton("Set data");
		buttonSetData.setSize(60, 20);
		
		GridBagConstraints gbcLabel = new GridBagConstraints();
		gbcLabel.insets = new Insets(5,5,5,5);
		gbcLabel.gridwidth = 2;
		gbcLabel.gridx = 0;
		gbcLabel.gridy = 0;
		GridBagConstraints gbcInput = new GridBagConstraints();
		gbcInput.gridx = 0;
		gbcInput.gridy = 1;
		GridBagConstraints gbcBtn = new GridBagConstraints();
		gbcBtn.insets = new Insets(15, 15, 15, 15);
		gbcBtn.gridwidth = 5;
		gbcBtn.gridx = 0;
		gbcBtn.gridy = 2;
		GridBagConstraints gbcRBtnLeft = new GridBagConstraints();
		gbcRBtnLeft.gridx = 0;
		gbcRBtnLeft.gridy = 1;
		GridBagConstraints gbcRBtnRight = new GridBagConstraints();
		gbcRBtnRight.gridx = 1; 
		gbcRBtnRight.gridy = 1;
		GridBagConstraints gbcRBtn2 = new GridBagConstraints();
		gbcRBtn2.gridx = 2;
		gbcRBtn2.gridy = 1;
		GridBagConstraints gbcRBtn3 = new GridBagConstraints();
		gbcRBtn3.gridx = 3;
		gbcRBtn3.gridy = 1;
		GridBagConstraints gbcRBtn4 = new GridBagConstraints();
		gbcRBtn4.gridx = 4;
		gbcRBtn4.gridy = 1;
		GridBagConstraints gbcBtnLast = new GridBagConstraints();
		gbcBtn.insets = new Insets(15, 15, 15, 15);
		gbcBtn.gridwidth = 5;
		gbcBtn.gridx = 0;
		gbcBtn.gridy = 3;
		
		// Content Panel
		content.setPreferredSize(new Dimension(400,300));
		content.setLayout(new GridBagLayout());
		content.add(panelGender);
		content.add(panelAge);
		content.add(panelIntensity);
		content.add(panelDuration);
		content.add(panelPulsating);
		content.add(panelPressing);
		content.add(panelNauseaVomiting);
		content.add(panelNoiseLight);
		content.add(panelHeating);
		content.add(panelMonthlyHeadaches);
		content.add(panelHeadPart);
		content.add(panelWorsenHeadache);
		content.add(panelSetData);
		
		// Menu Bar
		menuBar.add(about);
		menuBar.add(help);
		menuBar.add(exit);
		about.add(aboutProgram);
		about.add(aboutAuthor);
		help.add(helpHelp);
		exit.add(exitExit);
		frame.setJMenuBar(menuBar);
	
		// Questions panels
		panelGender.add(labelGender, gbcLabel);
		buttonGroupGender.add(rdbMale);
		buttonGroupGender.add(rdbFemale);
		panelGender.add(rdbMale, gbcRBtnLeft);
		panelGender.add(rdbFemale, gbcRBtnRight);
		panelGender.add(buttonGender,gbcBtn);
		panelGender.setVisible(true);
		
		panelAge.add(labelAge, gbcLabel);
		panelAge.add(textFieldAge, gbcInput);
		panelAge.add(buttonAge, gbcBtn);
		
		panelIntensity.add(labelIntensity, gbcLabel);
		panelIntensity.add(sliderIntensity, gbcInput);
		sliderIntensity.setLabelTable(tableIntensity);
		panelIntensity.add(buttonIntensity, gbcBtn);
		
		panelDuration.add(labelDuration, gbcLabel);
		buttonGroupGender.add(rdbMinutes);
		buttonGroupGender.add(rdbHours);
		panelDuration.add(rdbMinutes, gbcRBtnLeft);
		panelDuration.add(rdbHours, gbcRBtnRight);
		panelDuration.add(sliderDuration, gbcBtn);
		panelDuration.add(buttonDuration, gbcBtnLast);
		
        panelPulsating.add(labelPulsating, gbcLabel);
		panelPulsating.add(sliderPulsating, gbcInput);
		sliderPulsating.setLabelTable(tablePulsating);
		panelPulsating.add(buttonPulsating, gbcBtn);
       
        panelPressing.add(labelPressing, gbcLabel);
		panelPressing.add(sliderPressing, gbcInput);
		sliderPressing.setLabelTable(tablePressing);
		panelPressing.add(buttonPressing, gbcBtn);
		
		panelNauseaVomiting.add(labelNauseaVomiting, gbcLabel);
		panelNauseaVomiting.add(sliderNauseaVomiting, gbcInput);
		sliderNauseaVomiting.setLabelTable(tableNauseaVomiting);
		panelNauseaVomiting.add(buttonNauseaVomiting, gbcBtn);
		
        panelNoiseLight.add(labelNoiseLight, gbcLabel);
        panelNoiseLight.add(sliderNoiseLight, gbcInput);
        sliderNoiseLight.setLabelTable(tableNoiseLight);
        panelNoiseLight.add(buttonNoiseLight, gbcBtn);
		
        panelHeating.add(labelHeating, gbcLabel);
        panelHeating.add(sliderHeating, gbcInput);
        sliderHeating.setLabelTable(tableHeating);
        panelHeating.add(buttonHeating, gbcBtn);
		
        panelMonthlyHeadaches.add(labelMonthlyHeadaches, gbcLabel);
        panelMonthlyHeadaches.add(sliderMonthlyHeadaches, gbcInput);
        panelMonthlyHeadaches.add(buttonMonthlyHeadaches, gbcBtn);
		
		panelHeadPart.add(labelHeadPart, gbcLabel);
		panelHeadPart.add(sliderHeadPart, gbcInput);
		sliderHeadPart.setLabelTable(tableHeadPart);
		panelHeadPart.add(buttonHeadPart, gbcBtn);
		
        panelWorsenHeadache.add(labelWorsenHeadache, gbcLabel);
		buttonGroupWorsenHeadache.add(physicalActivity);
		buttonGroupWorsenHeadache.add(coldStuff);
		buttonGroupWorsenHeadache.add(timeChanges);
		buttonGroupWorsenHeadache.add(stressEffort);
		buttonGroupWorsenHeadache.add(none);
		panelWorsenHeadache.add(physicalActivity, gbcRBtnLeft);
		panelWorsenHeadache.add(coldStuff, gbcRBtnRight);
		panelWorsenHeadache.add(timeChanges, gbcRBtn2);
		panelWorsenHeadache.add(stressEffort, gbcRBtn3);
		panelWorsenHeadache.add(none, gbcRBtn4);
		panelWorsenHeadache.add(buttonWorsenHeadache, gbcBtn);
		
		panelSetData.add(labelSetData, gbcLabel);
		panelSetData.add(buttonSetData, gbcBtn);

		// Frame
		frame.add(content);		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		
		// Menu items events
		aboutProgram.addActionListener((e) -> {
			JOptionPane.showMessageDialog(null, "About Program", "About Program", JOptionPane.INFORMATION_MESSAGE);
		});
		aboutAuthor.addActionListener((e) -> {
			JOptionPane.showMessageDialog(null, "About Author", "About Author", JOptionPane.INFORMATION_MESSAGE);
		});
		helpHelp.addActionListener((e) -> {
			JOptionPane.showMessageDialog(null, "Quick Help", "Quick Help", JOptionPane.INFORMATION_MESSAGE);
		});
		exitExit.addActionListener((e) -> {
			System.exit(0);
		});
		
		// Gender get value and next question button
		buttonGender.addActionListener((e) -> {
			if(rdbMale.isSelected()) {	
				valueGender = 0.0;
				panelGender.setVisible(false);
				panelAge.setVisible(true);
			}
			else if(rdbFemale.isSelected()) {
				valueGender = 1.0;
				panelGender.setVisible(false);
				panelAge.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(null, "Select your gender", "Please select one option", JOptionPane.WARNING_MESSAGE);
			}
			System.out.println(valueGender);
		});
		
		// Age get value and next question button
		buttonAge.addActionListener((e) -> {
			inpVal = textFieldAge.getText();
			if(inpVal.isEmpty() ) {
				JOptionPane.showMessageDialog(null, "Select your age", "Please fill a input", JOptionPane.WARNING_MESSAGE);
			}	
			else if( Double.parseDouble(inpVal) <= 17 ) {
				JOptionPane.showMessageDialog(null, "You must be least 18 year old", "Select your age", JOptionPane.WARNING_MESSAGE);
			}
			else {
				valueAge = Double.parseDouble(inpVal);
				panelAge.setVisible(false);
				panelIntensity.setVisible(true);
				System.out.println(valueAge);
			}
		});
		
		// Intensity get value and next question button
		buttonIntensity.addActionListener((e) -> {
			if(sliderIntensity.getValue() <= 0) {
				JOptionPane.showMessageDialog(null, "Select your intensity", "Please choose a higher value than zero", JOptionPane.WARNING_MESSAGE);
			}
			else {
				System.out.println(sliderIntensity.getValue());
				panelIntensity.setVisible(false);
				panelDuration.setVisible(true);
			}
		});
		
		// Duration get value and next question button
		/*buttonDuration.addActionListener((e) -> {
			if(sliderDuration.getValue() <= 0) {
				JOptionPane.showMessageDialog(null, "Select your Duration", "Please fill a input", JOptionPane.WARNING_MESSAGE);
			}		
			else {
				System.out.println(sliderDuration.getValue());
				panelDuration.setVisible(false);
				panelPulsating.setVisible(true);
			}
		});
		*/
		
		buttonDuration.addActionListener((e) -> {
			if(sliderDuration.getValue() <= 0) {
				JOptionPane.showMessageDialog(null, "Select your Duration", "Please fill a input", JOptionPane.WARNING_MESSAGE);
			}	
			else if(rdbMinutes.isSelected()) {
				System.out.println(sliderDuration.getValue() / 60);
				duration = sliderDuration.getValue() / 60;
				panelDuration.setVisible(false);
				panelPulsating.setVisible(true);
			}
			else if(rdbHours.isSelected()) {
				System.out.println(sliderDuration.getValue());
				duration = sliderDuration.getValue();
				panelDuration.setVisible(false);
				panelPulsating.setVisible(true);
			}
		});
		
		// Pulsating get value and next question button
		buttonPulsating.addActionListener((e) -> {
			if(sliderPulsating.getValue() <= 0) {
				JOptionPane.showMessageDialog(null, "Select your Pulsating", "Please choose a higher value than zero", JOptionPane.WARNING_MESSAGE);
			}
			else {
				System.out.println(sliderPulsating.getValue());
				panelPulsating.setVisible(false);
				panelPressing.setVisible(true);
			}
		});
		
		// Pressing get value and next question button
		buttonPressing.addActionListener((e) -> {
			if(sliderPressing.getValue() <= 0) {
				JOptionPane.showMessageDialog(null, "Select your Pressing", "Please choose a higher value than zero", JOptionPane.WARNING_MESSAGE);
			}
			else {
				System.out.println(sliderPressing.getValue());
				panelPressing.setVisible(false);
				panelNauseaVomiting.setVisible(true);
			}
		});
		
		// Nausea and Vomiting get value and next question button
		buttonNauseaVomiting.addActionListener((e) -> {
			if(sliderNauseaVomiting.getValue() <= 0) {
				JOptionPane.showMessageDialog(null, "Select your Nausea and Vomiting", "Please choose a higher value than zero", JOptionPane.WARNING_MESSAGE);
			}
			else {
				System.out.println(sliderNauseaVomiting.getValue());
				panelNauseaVomiting.setVisible(false);
				panelNoiseLight.setVisible(true);
			}
		});
		
		// Noise and Light get value and next question button
		buttonNoiseLight.addActionListener((e) -> {
			if(sliderNoiseLight.getValue() <= 0) {
				JOptionPane.showMessageDialog(null, "Select your Noise and Light", "Please choose a higher value than zero", JOptionPane.WARNING_MESSAGE);
			}
			else {
				System.out.println(sliderNoiseLight.getValue());
				panelNoiseLight.setVisible(false);
				panelHeating.setVisible(true);
			}
		});
		
		// Heating get value and next question button
		buttonHeating.addActionListener((e) -> {
			if(sliderHeating.getValue() <= 0) {
				JOptionPane.showMessageDialog(null, "Select your Heating", "Please choose a higher value than zero", JOptionPane.WARNING_MESSAGE);
			}
			else {
				System.out.println(sliderNoiseLight.getValue());
				panelHeating.setVisible(false);
				panelMonthlyHeadaches.setVisible(true);
			}
		});
		
		// Monthly Headches get value and next question button
		buttonMonthlyHeadaches.addActionListener((e) -> {
			if(sliderMonthlyHeadaches.getValue() <= 0) {
				JOptionPane.showMessageDialog(null, "Select your Monthly Headache", "Please choose a higher value than zero", JOptionPane.WARNING_MESSAGE);
			}
			else {
				System.out.println(sliderNoiseLight.getValue());
				panelMonthlyHeadaches.setVisible(false);
				panelHeadPart.setVisible(true);
			}
		});
		
		// Head Part get value and next question button
		buttonHeadPart.addActionListener((e) -> {
			if(sliderHeadPart.getValue() <= 0) {
				JOptionPane.showMessageDialog(null, "Select your Head Part", "Please select one option", JOptionPane.WARNING_MESSAGE);
			}
			else {
				System.out.println(sliderHeadPart.getValue());
				panelHeadPart.setVisible(false);
				panelWorsenHeadache.setVisible(true);
			}
		});
		
		// Worsen Headache get value and next question button		
		buttonWorsenHeadache.addActionListener((e) -> {
			if(physicalActivity.isSelected()) {	
				valueWorsenHeadache = 2.0;
				panelWorsenHeadache.setVisible(false);
				panelSetData.setVisible(true);
			}
			else if(coldStuff.isSelected()) {
				valueWorsenHeadache = 4.0;
				panelWorsenHeadache.setVisible(false);
				panelSetData.setVisible(true);
			}
			else if(timeChanges.isSelected()) {
				valueWorsenHeadache = 6.0;
				panelWorsenHeadache.setVisible(false);
				panelSetData.setVisible(true);
			}
			else if(stressEffort.isSelected()) {
				valueWorsenHeadache = 8.0;
				panelWorsenHeadache.setVisible(false);
				panelSetData.setVisible(true);
			}
			else if(none.isSelected()) {
				valueWorsenHeadache = 10.0;
				panelWorsenHeadache.setVisible(false);
				panelSetData.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(null, "Select your Worsen Headache", "Please select one option", JOptionPane.WARNING_MESSAGE);
			}
			System.out.println(valueWorsenHeadache);
		});
		
		// Set Data and show results
		buttonSetData.addActionListener((e) -> {
			runSystem();
		});
	}

	private void show() {
		frame.setVisible(true);
	}
	
	// Run System method for showing a final results with diagrams
	public void runSystem() {
		
		// Collecting value from answers 
		setData();

		// Evaluate
		fis.evaluate();

		// Show charts
		JFuzzyChart.get().chart(fis);

		// Defuzzify Headache Type
		System.out.println(fis.getVariable("headcheType").defuzzify());

		// Show output variables chart
		Variable tip = fis.getVariable("headcheType");
		JFuzzyChart.get().chart(tip, tip.getDefuzzifier(), true);     


		// Get Rules
		for (Rule r : fis.getFunctionBlock("headcheAnalysis").getFuzzyRuleBlock("headcheRules").getRules())
			System.out.println(r);

		// Print ruleSet
		System.out.println(fis);
	}

	public void setData() {  // double value
		// Set inputs for all questions
		fis.setVariable("gender", valueGender);
		fis.setVariable("age", valueAge);
		fis.setVariable("intensity", sliderIntensity.getValue());
		fis.setVariable("duration", duration);
		fis.setVariable("pulsating", sliderPulsating.getValue());
		fis.setVariable("pressing", sliderPressing.getValue());
		fis.setVariable("nauseaVomiting", sliderNauseaVomiting.getValue());
		fis.setVariable("noiseLight", sliderNoiseLight.getValue());
		fis.setVariable("heating", sliderHeating.getValue());
		fis.setVariable("monthlyHeadches", sliderMonthlyHeadaches.getValue());
		fis.setVariable("headPart", sliderHeadPart.getValue());
		fis.setVariable("worsenHeadches", valueWorsenHeadache);
	}

	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		UIManager.put("buttonGender.defaultButtonFollowsFocus", Boolean.TRUE);
		HeadacheExpertSystem expertSystem = new HeadacheExpertSystem();
		expertSystem.show();

	}
}

