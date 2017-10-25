package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import model.PatientData;

public class CSVReaderUtil {
	
	static String CSV_SEPARATOR = ",";
	String filePath;
	String header;
	
	public String getHeader() {
		return header;
	}

	public CSVReaderUtil(String filePath) {
		this.filePath = filePath;
	}
	
	public List<PatientData> parse(){
		return this.processInputFile(filePath);
	}
	
	private Double parseStringToDouble(String str){
		return Double.parseDouble(str);
	}
	
	private List<PatientData> processInputFile(String inputFilePath) {

	    List<PatientData> inputList = new ArrayList<>();

	    try{

	      File inputF = new File(inputFilePath);

	      InputStream inputFS = new FileInputStream(inputF);

	      BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

	      this.header = br.readLine();
	      
	      inputList = br
	    		  .lines()
	    		  .map(mapToItem)
	    		  .collect(Collectors.toList());

	      br.close();

	    } catch (IOException e) {

	    }

	    return inputList ;

	}
	
	public Function<String, PatientData> mapToItem = (line) -> {

		  String[] tupleFields = line.split(CSV_SEPARATOR);// a CSV has comma separated lines

		  PatientData item = new PatientData();
		  
		  if (tupleFields.length < 9 ){
			  return item;
		  }
		  
		  Double numberOfTimesPregnant = this.parseStringToDouble(tupleFields[0]);
		  item.setNumberOfTimesPregnant(numberOfTimesPregnant);
		  
		  Double plasmaGlucoseConcentration = this.parseStringToDouble(tupleFields[1]);
		  item.setPlasmaGlucoseConcentration(plasmaGlucoseConcentration);
		  
		  Double diastolicBloodPressure = this.parseStringToDouble(tupleFields[2]);
		  item.setDiastolicBloodPressure(diastolicBloodPressure);
		  
		  Double tricepsSkinFoldThickness = this.parseStringToDouble(tupleFields[3]);
		  item.setTricepsSkinFoldThickness(tricepsSkinFoldThickness);
		  
		  Double twoHourSerumInsulin = this.parseStringToDouble(tupleFields[4]);
		  item.setTwoHourSerumInsulin(twoHourSerumInsulin);
		  
		  Double bodyMassIndex = this.parseStringToDouble(tupleFields[5]);
		  item.setBodyMassIndex(bodyMassIndex);
		  
		  Double diabetesPedigreeFunction = this.parseStringToDouble(tupleFields[6]);
		  item.setDiabetesPedigreeFunction(diabetesPedigreeFunction);
		  
		  Double age = this.parseStringToDouble(tupleFields[7]);
		  item.setAge(age);
		  
		  return item;

		};

}
