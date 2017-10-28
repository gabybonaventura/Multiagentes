package classifiers;

import java.util.ArrayList;

import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class FeedforwardClassifier {

	private MultilayerPerceptron mlp;
	private Instances testData;

	public FeedforwardClassifier(String modelFilePath) throws Exception {
		this.mlp = (MultilayerPerceptron) weka.core.SerializationHelper.read(modelFilePath);

		ArrayList<Attribute> attributes = new ArrayList<>(9);
		attributes.add(new Attribute("Number of times pregnant"));
		attributes.add(new Attribute("Plasma glucose concentration"));
		attributes.add(new Attribute("Diastolic blood pressure"));
		attributes.add(new Attribute("Triceps skin fold thickness"));
		attributes.add(new Attribute("2-Hour serum insulin"));
		attributes.add(new Attribute("Body mass index"));
		attributes.add(new Attribute("Diabetes pedigree function"));
		attributes.add(new Attribute("Age"));

		ArrayList<String> classValues = new ArrayList<>();
		classValues.add("positive");
		classValues.add("negative");
		attributes.add(new Attribute("Class", classValues));

		this.testData = new Instances("Test-Set-A", attributes, 1);

		this.testData.setClassIndex(testData.numAttributes() - 1);
	}

	public String ClassifyInstance(double[] attributeValues) throws Exception {
		this.testData.clear();

		Instance instance = new DenseInstance(9);
		for (int i = 0; i < attributeValues.length; i++) {
			instance.setValue(i, attributeValues[i]);
		}

		instance.setDataset(this.testData);
		this.testData.add(instance);

		return this.testData.classAttribute().value((int) this.mlp.classifyInstance(instance));

	}
}
