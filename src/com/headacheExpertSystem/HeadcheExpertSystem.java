package com.headcheExpertSystem;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Rule;
import net.sourceforge.jFuzzyLogic.rule.Variable;



public class HeadcheExpertSystem {
	public static void main(String[] args) {
		String fileName = "rules.fcl";
		FIS fis = FIS.load(fileName, true);
		
		if (fis == null){
			System.err.println("Can't load file: '" + fileName + "'");
			return;
		}
				
		// Set inputs
		fis.setVariable("gender", 1);
		fis.setVariable("age", 38);
		fis.setVariable("intensity", 5);
		fis.setVariable("duration", 1.5);
		fis.setVariable("pulsating", 6);
		fis.setVariable("pressing", 0);
		fis.setVariable("nauseaVomiting", 5.5);
		fis.setVariable("noiseLight", 6);
		fis.setVariable("heating", 4);
		fis.setVariable("monthlyHeadches", 1);
		fis.setVariable("headPart", 5);
		fis.setVariable("worsenHeadches", 2);
		
		// Evaluate
		fis.evaluate();
		
		// Show charts
		JFuzzyChart.get().chart(fis);
		
		// Defuzzify Headche Type
		System.out.println(fis.getVariable("headcheType").defuzzify());		
		
        // Show output variables chart
        Variable tip = fis.getVariable("headcheType");
        JFuzzyChart.get().chart(tip, tip.getDefuzzifier(), true);
		
        // Get Rules
		for( Rule r : fis.getFunctionBlock("headcheAnalysis").getFuzzyRuleBlock("headcheType").getRules() )
		      System.out.println(r);

        // Print ruleSet
        System.out.println(fis);

	}
}
