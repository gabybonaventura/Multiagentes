// Agent measurement_agent in project diabetes_mas

/* Initial beliefs and rules */

/* Initial goals */

//!start.
!configure_patient_dataset_reader.

/* Plans */

+!focus(ArtId) // goal sent by the Agents to focus artefacts
 <- lookupArtifact(ArtId,MediumId);
 focus(MediumId).
+!configure_patient_dataset_reader : true <-
makeArtifact(patient_dataset_reader,
"diabetes_mas.PatientDatasetReader", [], ArtId);
focus(ArtId);
.println("measurement agent started")
println("Dataset to load:");
readHeader(DatasetHeader);
println(DatasetHeader).

+commitment(AgentName, MisId, SchId): MisId == diagnosis_session_manager_mission <-
+session_manager(AgentName).
+!start_reading_tuples <-
+no_tuples_to_read(false)
startTuplesReader.
+!read_next_patient_data_tuple : number_of_last_tuple_read(CurrentTupleNumber) &
number_of_tuples(NumberOfTuples) & (CurrentTupleNumber + 1) < NumberOfTuples <-
readTuple(CurrentTupleNumber + 1).
+!read_next_patient_data_tuple : number_of_last_tuple_read(CurrentTupleNumber) &
number_of_tuples(NumberOfTuples) & (CurrentTupleNumber + 1) == NumberOfTuples <-
-+no_tuples_to_read(true);
!inform_no_tuples_to_read.
+current_patient_tuple(PatientDataTuple) : true
 <-
  clearPatientMeasureData;
  .length(PatientDataTuple,NumberOfFields);
   for(.range(Index,0,NumberOfFields - 1)){
              jia.getDoubleItemFromArrayAtIxdex(Index,PatientDataTuple,Measure);
           addPatientField(Measure)    
   };
 .println("patient data measures: ",PatientDataTuple);
  sendPatientMeasureData.

-!inform_no_tuples_to_read : no_tuples_to_read(false) <-
.println("waiting to read all tuples to inform the end of the session").
+!inform_no_tuples_to_read : no_tuples_to_read(true) <-
.println("No tuples to read");
goalAchieved(inform_no_tuples_to_read).

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have a agent that always complies with its organization  
{ include("$jacamoJar/templates/org-obedient.asl") }
