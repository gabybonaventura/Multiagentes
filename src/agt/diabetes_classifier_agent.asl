// Agent diabetes_diagnosis_agent in project diabetes_mas

/* Initial beliefs and rules */

/* Initial goals */

//!start.
/* Initial goals */

+start_vote_registration [source(JudgeAgent)] <-
.send(JudgeAgent,tell,new_voter_registration).
+!focus(ArtId) <- // goal sent by the Agents to focus artefacts
lookupArtifact(ArtId,MediumId);
 focus(MediumId).
+mentor(MentorAgent) <-
!ask_model_path.
+patient_measures(PatientMeasure): ml_algorithm(AlgType) <-
!perform_diabetes_diagnosis(PatientMeasure).


/* Plans */

//+!start : true <- .print("hello world.").
-!perform_diabetes_diagnosis(PatientMeasure)[error(no_relevant)]:
mentor(Mentor) <-
.send(Mentor, askHow, { +!perform_diabetes_diagnosis(PatientMeasure)},
Plan);
.add_plan(Plan);
!perform_diabetes_diagnosis(PatientMeasure).


+!perform_diabetes_diagnosis(PatientMeasure) : ml_algorithm(descision_tree) <-
	?model_path(decision_tree,ModelPath);
	jia.classifyDecisionTreeDiabetesDiagnosis(ModelPath,PatientMeasure,PartialResult);
	.println("Decision tree diabetes diagnosis result is ", PartialResult);
	voteOption(PartialResult).

+!perform_diabetes_diagnosis(PatientMeasure) : ml_algorithm(feed_fordward_network) <-
	?model_path(feed_forward_network,ModelPath);
	jia.classifyFeedforwardNeuralNetworkDiabetesDiagnosis(ModelPath,PatientMeasure,PartialResult);
	.println("Feedforward neural network diagnosis result is ", PartialResult);
	voteOption(PartialResult).		

+!ask_model_path : mentor(Mentor) & ml_algorithm(AlgType) <-
.send(Mentor,tell,model_path(AlgType)).

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }
// uncomment the include below to have a agent that always complies with its organization  
{ include("$jacamoJar/templates/org-obedient.asl") }
