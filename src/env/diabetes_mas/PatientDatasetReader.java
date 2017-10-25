// CArtAgO artifact code for project diabetes_mas

package diabetes_mas;

import java.util.List;
import cartago.Artifact;
import cartago.OPERATION;
import cartago.ObsProperty;
import cartago.OpFeedbackParam;
import jason.NoValueException;
import jason.asSyntax.ListTerm;
import jason.asSyntax.NumberTerm;
import jason.asSyntax.NumberTermImpl;
import model.PatientData;
import utils.CSVReaderUtil;

/**
 * @author Martin
 *
 */
public class PatientDatasetReader extends Artifact {
	
	
	private static String filePath = "diabetes.csv";
	
	private String NUMBER_OF_TUPLES_PROPERTY = "number_of_tuples";
	private String CURRENT_PATIENT_TUPLE_PROPERTY = "current_patient_tuple";
	private String NUMBER_OF_LAST_TUPLE_READ_PROPERTY = "number_of_last_tuple_read";
	
	List<PatientData> patientTuples;
	String header;
	
	
	void init() {
		
		CSVReaderUtil csvReader = new CSVReaderUtil(filePath);
		this.patientTuples = csvReader.parse();
		this.header = csvReader.getHeader();
	}
	
	/**
	 * 
	 * Contract: 
	 * precondition: CSV file has been loaded
	 * postcondition: -
	 * invariant: -
	 * 
	 * @param header is filled with csv file header
	 * 
	 */
	
	@OPERATION
	void readHeader(OpFeedbackParam<String> header) {
		header.set(this.header);
	}
	
	/**
	 * 
	 * Contract: 
	 * precondition: CSV file has been loaded
	 * postcondition: CURRENT_PATIENT_TUPLE_PROPERTY, NUMBER_OF_LAST_TUPLE_READ_PROPERTY, NUMBER_OF_TUPLES_PROPERTY, loaded in artefact
	 * invariant: NUMBER_OF_LAST_TUPLE_READ_PROPERTY between 0..<n, CURRENT_PATIENT_TUPLE_PROPERTY has tuple attributes, NUMBER_OF_TUPLES_PROPERTY is the number of tuples loaded 
	 * 
	 */
	@OPERATION
	void startTuplesReader() {
		defineObsProperty(NUMBER_OF_TUPLES_PROPERTY, this.patientTuples.size());
		defineObsProperty(CURRENT_PATIENT_TUPLE_PROPERTY, this.patientTuples.get(0).getRawPatientData());
		int currentIndex = 0;
		defineObsProperty(NUMBER_OF_LAST_TUPLE_READ_PROPERTY, currentIndex);	
	}
	

	/**
	 * 
	 * Contract: 
	 * precondition: index is an element in 0...n; n is number of patient data tuples  
	 * postcondition: CURRENT_PATIENT_TUPLE_PROPERTY & NUMBER_OF_LAST_TUPLE_READ_PROPERTY must be refreshed with desired tuple & its index value.
	 * invariant: NUMBER_OF_LAST_TUPLE_READ_PROPERTY between 0..<n, CURRENT_PATIENT_TUPLE_PROPERTY has tuple attributes,
	 * 
	 * @param index is the number of desired patient data tuple
	 * @return list of number terms which contains PatientData object attributes desired, ordered by getRawPatientData method
	 * @throws NoValueException 
	 * 
	 */
	@OPERATION
	void readTuple(int index) throws NoValueException {
		
		PatientData tuple = this.patientTuples.get(index);
		ListTerm patientDataListTerm = tuple.getRawPatientData();
		
		ObsProperty lastTupleReadProp = getObsProperty(CURRENT_PATIENT_TUPLE_PROPERTY);
		ObsProperty numberOfLastTupleReadProp = getObsProperty(NUMBER_OF_LAST_TUPLE_READ_PROPERTY);
		
		lastTupleReadProp.updateValue(patientDataListTerm);
		numberOfLastTupleReadProp.updateValue(index);
	}
	
	
}

