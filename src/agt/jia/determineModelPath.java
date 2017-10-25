// Internal action code for project diabetes_mas

package jia;

import java.util.Date;
import java.util.Random;

import jason.*;
import jason.asSemantics.*;
import jason.asSyntax.*;
import jason.stdlib.term2string;
import utils.JasonUtils;

public class determineModelPath extends DefaultInternalAction {

    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {

    	Random random = new Random(new Date().getTime());
    	String basePath = "models\\";    	
    	String[] treeModelNames = {"tree_model_name_here","","",""};
    	String[] neuralNetworkModelPaths = {"mlp_hl_4.model","mlp_hl_5.model"};
    	
    	String algType  = args[0].toString();
    	String modelPath = "";
    	
    	if (algType.equals("decision_tree") ) {
    		int normalizedPosition =  (int)(Math.abs(random.nextInt()) % treeModelNames.length);
    		modelPath = basePath + treeModelNames[normalizedPosition];
    	}
    	else {
    		int normalizedPosition =  (int)(Math.abs(random.nextInt()) % neuralNetworkModelPaths.length);
    		modelPath = basePath + neuralNetworkModelPaths[normalizedPosition];
    	}
    	
       	StringTerm strTerm = new StringTermImpl(modelPath);
       	
        // everything ok, so returns true
        return un.unifies(strTerm, args[1]);
    }
}
