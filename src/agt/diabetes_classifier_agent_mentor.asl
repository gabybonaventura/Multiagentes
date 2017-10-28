// Agent diabetes_classifier_agent_mentor in project diabetes_mas

/* Initial beliefs and rules */

/* Initial goals */

+start_vote_registration [source(JudgeAgent)] <- .send(JudgeAgent,tell,new_voter_registration). 
  
+!focus(ArtId) <-  // goal sent by the Agents to focus artefacts 
	lookupArtifact(ArtId,MediumId);      
	focus(MediumId). 
 
+mentor(MentorAgent) <- !ask_model_path. 
 
+patient_measures(PatientMeasure): ml_algorithm(AlgType) <-  !perform_diabetes_diagnosis(PatientMeasure). 

/* Plans to teach agents */

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
