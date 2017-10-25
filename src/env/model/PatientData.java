package model;

import jason.asSyntax.ListTerm;
import jason.asSyntax.ListTermImpl;
import jason.asSyntax.NumberTermImpl;

public class PatientData {

	double numberOfTimesPregnant;
	double plasmaGlucoseConcentration;
	double diastolicBloodPressure;
	double tricepsSkinFoldThickness;
	double twoHourSerumInsulin;
	double bodyMassIndex;
	double diabetesPedigreeFunction;
	double age;
	
	public ListTerm getRawPatientData() {
		
		ListTerm rawDataList = new ListTermImpl();
		
		rawDataList.add(new NumberTermImpl(this.numberOfTimesPregnant));
		rawDataList.add(new NumberTermImpl(this.plasmaGlucoseConcentration));
		rawDataList.add(new NumberTermImpl(this.diastolicBloodPressure));
		rawDataList.add(new NumberTermImpl(this.tricepsSkinFoldThickness));
		rawDataList.add(new NumberTermImpl(this.twoHourSerumInsulin));
		rawDataList.add(new NumberTermImpl(this.bodyMassIndex));
		rawDataList.add(new NumberTermImpl(this.diabetesPedigreeFunction));
		rawDataList.add(new NumberTermImpl(this.age));
		
		return rawDataList;		
	}
	
	public double getNumberOfTimesPregnant() {
		return numberOfTimesPregnant;
	}
	public void setNumberOfTimesPregnant(double numberOfTimesPregnant) {
		this.numberOfTimesPregnant = numberOfTimesPregnant;
	}
	public double getPlasmaGlucoseConcentration() {
		return plasmaGlucoseConcentration;
	}
	public void setPlasmaGlucoseConcentration(double plasmaGlucoseConcentration) {
		this.plasmaGlucoseConcentration = plasmaGlucoseConcentration;
	}
	public double getDiastolicBloodPressure() {
		return diastolicBloodPressure;
	}
	public void setDiastolicBloodPressure(double diastolicBloodPressure) {
		this.diastolicBloodPressure = diastolicBloodPressure;
	}
	public double getTricepsSkinFoldThickness() {
		return tricepsSkinFoldThickness;
	}
	public void setTricepsSkinFoldThickness(double tricepsSkinFoldThickness) {
		this.tricepsSkinFoldThickness = tricepsSkinFoldThickness;
	}
	public double getTwoHourSerumInsulin() {
		return twoHourSerumInsulin;
	}
	public void setTwoHourSerumInsulin(double twoHourSerumInsulin) {
		this.twoHourSerumInsulin = twoHourSerumInsulin;
	}
	public double getBodyMassIndex() {
		return bodyMassIndex;
	}
	public void setBodyMassIndex(double bodyMassIndex) {
		this.bodyMassIndex = bodyMassIndex;
	}
	public double getDiabetesPedigreeFunction() {
		return diabetesPedigreeFunction;
	}
	public void setDiabetesPedigreeFunction(double diabetesPedigreeFunction) {
		this.diabetesPedigreeFunction = diabetesPedigreeFunction;
	}
	public double getAge() {
		return age;
	}
	public void setAge(double age) {
		this.age = age;
	}
	
	
}

