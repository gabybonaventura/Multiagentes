// CArtAgO artifact code for project diabetes_mas

package diabetes_mas;

import cartago.*;
import jason.asSyntax.ListTerm;
import jason.asSyntax.ListTermImpl;
import jason.asSyntax.NumberTermImpl;
import jason.asSyntax.Term;

public class MeasuresCommunicationMedium extends Artifact {
	
	static String PATIENT_MEASURE_PROPERTY = "patient_measures";

	ListTerm terms = new ListTermImpl();
	
	void init() {
		
	}
	
	@OPERATION
	void addPatientField(double field) {
		terms.add(new NumberTermImpl(field));
	}
	
	@OPERATION
	void sendPatientMeasureData() {
	
		ObsProperty prop = getObsProperty(PATIENT_MEASURE_PROPERTY);	
		
		if (prop == null) {
			defineObsProperty(PATIENT_MEASURE_PROPERTY,terms);
			return;
			
		}

		prop.updateValue(terms);
	}
	
	@OPERATION
	void clearPatientMeasureData() {
		this.terms.clear();
	}
	
	
}

