// CArtAgO artifact code for project diabetes_mas

package diabetes_mas;

import java.util.ArrayList;
import java.util.List;

import cartago.*;
import model.DiabetesDiagnosisResultEnum;

/**
 * @author Martin
 *
 */
public class DiagnosisResultDepository extends Artifact {
	
	static String NUMBER_OF_AGENTS_SUBSCRIBED = "number_of_agents_subcribed";  
	static String NUMBER_OF_VOTES_PROPERTY = "number_of_votes";  
	
	List<String> agentSubscribed;
	
	int numberOfDataTuple;
	int numberOfVotes;
	int numberOfPositiveVotes;
	int numberOfNegativeVotes;
	
	void init() {
		
		this.numberOfDataTuple = 0;
		this.agentSubscribed = new ArrayList<>();
		this.resetVotes();
		defineObsProperty(NUMBER_OF_VOTES_PROPERTY, numberOfVotes);
		defineObsProperty(NUMBER_OF_AGENTS_SUBSCRIBED, this.agentSubscribed.size());
	}
	
	@OPERATION
	void subscribe() {
		String agentName = getCurrentOpAgentBody().getName();
		
		this.agentSubscribed.add(agentName);
		
		getObsProperty(NUMBER_OF_AGENTS_SUBSCRIBED)
		.updateValue(this.agentSubscribed.size());
		
	}
	
	/**
	 * Contract
	 * Precondition: diabetesDiagnosisResult must be an string element in DiabetesDiagnosisResultEnum 
	 * PostCondition: Positive or negative vote is counted.
	 * Invariant: -
	 * @param diabetesDiagnosisResult is an string case of DiabetesDiagnosisResultEnum
	 */
	@OPERATION
	void voteOption(String diabetesDiagnosisResult){
	
		ObsProperty prop = getObsProperty(NUMBER_OF_VOTES_PROPERTY);
		prop.updateValue(++this.numberOfVotes);
		
		if (diabetesDiagnosisResult.equals(DiabetesDiagnosisResultEnum.POSITIVE.toString())) {
			numberOfPositiveVotes++;
		}
		
		if (diabetesDiagnosisResult.equals(DiabetesDiagnosisResultEnum.NEGATIVE.toString())) {
			numberOfNegativeVotes++;
		}
	
	}
	
	/**
	 * Contract
	 * Precondition: Vote session ended 
	 * PostCondition: Number of positive and negative votes are returned 
	 * 				  and number of votes, number of positive votes & number of negative votes are reseted to zero.
	 * Invariant: -
	 * @param diabetesDiagnosisResult is an string case of DiabetesDiagnosisResultEnum
	 */
	
	@OPERATION
	void getVotationResults(OpFeedbackParam<Integer> tupleNumber,OpFeedbackParam<Integer> positiveVotes, OpFeedbackParam<Integer> negativeVotes) {
		
		tupleNumber.set(this.numberOfDataTuple);
		positiveVotes.set(numberOfPositiveVotes);
		negativeVotes.set(numberOfNegativeVotes);
		
		this.numberOfDataTuple++;
		
		this.resetVotes();
		
	}
	
	void resetVotes(){
		
		this.numberOfVotes = 0;
		this.numberOfPositiveVotes = 0;
		this.numberOfNegativeVotes = 0;
	}
	
}

