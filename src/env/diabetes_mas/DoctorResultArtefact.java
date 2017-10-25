// CArtAgO artifact code for project diabetes_mas

package diabetes_mas;

import cartago.*;

public class DoctorResultArtefact extends Artifact {
	
	static String NUMBER_OF_PATIENT_DIAGNOSIS = "number_of_patient_diagnosis";
	static String NUMBER_OF_POSITIVE_CASES = "number_of_positive_cases";
	static String NUMBER_OF_NEGATIVE_CASES = "number_of_negative_cases";
	
	int numberOfPatientDiagnosis = 0;
	int numberOfPositiveCases    = 0;
	int numberOfNegativeCases    = 0;
	
	void init() {
		defineObsProperty(NUMBER_OF_PATIENT_DIAGNOSIS, numberOfPatientDiagnosis);
		defineObsProperty(NUMBER_OF_POSITIVE_CASES, numberOfPositiveCases);
		defineObsProperty(NUMBER_OF_NEGATIVE_CASES, numberOfNegativeCases);
	}
	
	@OPERATION
	void addPartialDiagnosisResult(int positiveVotes, int negativeVotes) {
		
		this.numberOfPatientDiagnosis++;
	
		
		if (positiveVotes > negativeVotes) {
			this.numberOfPositiveCases++;
		}
		else {
			this.numberOfNegativeCases++;
		}
		
		this.updatePerceptions();
	}
	
	private void updatePerceptions() {
		
		getObsProperty(NUMBER_OF_PATIENT_DIAGNOSIS)
		.updateValue(numberOfPatientDiagnosis);
		
		getObsProperty(NUMBER_OF_POSITIVE_CASES)
		.updateValue(numberOfPositiveCases);
		
		getObsProperty(NUMBER_OF_NEGATIVE_CASES)
		.updateValue(numberOfNegativeCases);
	}
	
	@OPERATION
	void buildFinalReport(OpFeedbackParam<String> finalReport){
		
		String finalReportString =
				"=============================== FINAL REPORT ===============================\n" 
				+ "Number of patient tuples analized " + numberOfPatientDiagnosis 
				+ "\n" + "Number of positive cases "   + numberOfPositiveCases
				+ "\n" + "Number of negative cases "   + numberOfNegativeCases;
		
		finalReport.set(finalReportString);
	}
	
}

