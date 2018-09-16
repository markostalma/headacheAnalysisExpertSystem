package com.headcheExpertSystem;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Rule;

public class HeadcheExpertSystem {
	public static void main(String[] args) {
		String fileName = "fcl/rules.fcl";
		FIS fis = FIS.load(fileName, true);
		
		if (fis == null){
			System.err.println("Can't load file: '" + fileName + "'");
			return;
		}
		
		fis.setVariable("gender", 9);
		fis.setVariable("intensity", 8);
		fis.setVariable("duration", 3);
		
		fis.evaluate();
		
		System.out.println(fis.getVariable("migraine").defuzzify());
		System.out.println(fis.getVariable("tension").defuzzify());
		System.out.println(fis.getVariable("claster").defuzzify());
		
		JFuzzyChart.get().chart(fis);
		
		for( Rule r : fis.getFunctionBlock("headcheAnalysis").getFuzzyRuleBlock("headcheRules").getRules() )
		      System.out.println(r);
		
		
	
	}
}
