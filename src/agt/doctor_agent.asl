// Agent doctor_agent in project diabetes_mas

/* Initial beliefs and rules */

/* Initial goals */

!configure_my_result_artefact.

/* Plans */

+!configure_my_result_artefact : true <-
makeArtifact(doctor_result_artefact,"diabetes_mas.DoctorResultArtefact", [], ArtId);
focus(ArtId).

+partial_diagnosis_result(PatientTupleNumber,PositiveCases,NegativeCases) <-
.addPartialDiagnosisResult(PositiveCases,NegativeCases).


+!final_report: true <-
.buildFinalReport(FinalReport); 
.println("Positive cases: ",PositiveCases," Negative cases: ",NegativeCases).

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
{ include("$jacamoJar/templates/org-obedient.asl") }
