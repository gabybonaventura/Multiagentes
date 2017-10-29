// Agent diabetes_classifier_agent_mentor in project diabetes_mas

/* Initial beliefs and rules */

/* Initial goals */

/* Plans to teach agents */

+!perform_diabetes_diagnosis(PatientMeasure) : ml_algorithm(decision_tree) <-
	?model_path(decision_tree,ModelPath);
	jia.classifyDecisionTreeDiabetesDiagnosis(ModelPath,PatientMeasure,PartialResult);
	.println("Decision tree diabetes diagnosis result is ",PartialResult);
	voteOption(PartialResult).

+!perform_diabetes_diagnosis(PatientMeasure) : ml_algorithm(feed_forward_network) <-
    ?model_path(feed_forward_network,ModelPath);
	jia.classifyFeedforwardNeuralNetworkDiabetesDiagnosis(ModelPath,PatientMeasure,PartialResult);
	.println("Feedforward neural network diagnosis result is ",PartialResult);
	voteOption(PartialResult).


+model_path(AlgType)[source(AgentName)] <-
	jia.determineModelPath(AlgType,ModelPath);
	-+model_path_number(AlgType,Number + 1);
	.send(AgentName,tell,model_path(AlgType,ModelPath)).
	
+!mentor_presentation <-
	.println("Agents please pay attention... I am your mentor, you can ask me how to perform any diabetes diagnosis");
	.wait(1000).


{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have a agent that always complies with its organization  
{ include("$jacamoJar/templates/org-obedient.asl") }
