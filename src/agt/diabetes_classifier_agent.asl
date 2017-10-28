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

+!ask_model_path : mentor(Mentor) & ml_algorithm(AlgType) <-
.send(Mentor,tell,model_path(AlgType)).

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }
// uncomment the include below to have a agent that always complies with its organization  
{ include("$jacamoJar/templates/org-obedient.asl") }
