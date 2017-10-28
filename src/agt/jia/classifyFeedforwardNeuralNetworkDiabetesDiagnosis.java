// Internal action code for project diabetes_mas

package jia;

import classifiers.FeedforwardClassifier;
import classifiers.TreeClassifier;
import jason.*;
import jason.asSemantics.*;
import jason.asSyntax.*;
import model.DiabetesDiagnosisResultEnum;
import utils.JasonUtils;

public class classifyFeedforwardNeuralNetworkDiabetesDiagnosis extends DefaultInternalAction {

    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
    	
    	if (args[0].isString() == false ){
    		throw new JasonException("first argument is not a string");
    	}

    	StringTerm modelPath = (StringTermImpl) args[0];
    	
    	FeedforwardClassifier classifier = new FeedforwardClassifier(modelPath.getString());
    	
    	if (args[1].isList() == false) {
    		throw new JasonException("second argument is not a list");
    	}
    	
    	double [] patientDataTuple = JasonUtils.getDoubleArrayfrom(args[1]);
    	
    	String result = classifier.ClassifyInstance(patientDataTuple); 
    	Term resultTerm = new LiteralImpl(result.toUpperCase());
    	
        return un.unifies(resultTerm,args[2]);
    }
}
