// Agent diabetes_diagnosis_vote_judge in project diabetes_mas

/* Initial goals */
!start_diabetes_session_org("diabetes_session_id").

/* Plans */
+!start_diabetes_session_org(SessionId) <-
// creates a scheme to coordinate the diagnosis session
.concat("sch_",SessionId,SchName);
makeArtifact(SchName, "ora4mas.nopl.SchemeBoard",["src/org/diabetes_diagnosis_team.xml",diagnosis_session_scheme],SchArtId);
debug(inspector_gui(on))[artifact_id(SchArtId)];
.my_name(Me); setOwner(Me)[artifact_id(SchArtId)]; // I am the owner of this scheme!
focus(SchArtId);
addScheme(SchName); // set the group as responsible for the scheme
commitMission(diagnosis_session_manager_mission)[artifact_id(SchArtId)].

+!start_vote_session[scheme(SchArtId)] <-
!create_diagnosis_depository(SchArtId);
!create_measure_communication_medium(SchArtId);
+vote_session_started.
+!create_diagnosis_depository(SchArtId) <-
	makeArtifact(diagnosis_result_depository, "diabetes_mas.DiagnosisResultDepository", [], ArtId);
	focus(ArtId);
	setArgumentValue(diag_res_dep,"Diagnosis_result_medium_id",diagnosis_result_depository)[artifact_id(SchArtId)].
+!create_measure_communication_medium(SchArtId) <-
	makeArtifact(measure_comm_medium,"diabetes_mas.MeasuresCommunicationMedium",[],MediumId);
	setArgumentValue(comm_medium,"Measure_comm_medium_id",measure_comm_medium)[artifact_id(SchArtId)].
	

+number_of_votes(CurrentNumberOfVotes): vote_session_started & number_of_agents_subcribed(NumberOfAgents) & CurrentNumberOfVotes == NumberOfAgents <-
	getVotationResults(PatientTupleNumber,PositiveVotes,NegativeVotes);
	.println("Positive votes: ",PositiveVotes," Negative votes: ",NegativeVotes);
	?final_judge_agent(DoctorAgent);
	//.broadcast(tell,partial_diagnosis_result(PatientTupleNumber, PositiveVotes,NegativeVotes));
	.send(DoctorAgent,tell,partial_diagnosis_result(PatientTupleNumber, PositiveVotes,NegativeVotes));
	!read_next_patient_dataset_tuple.

+!read_next_patient_dataset_tuple: tuple_reader_agent(TupleReaderAgent) <-
	.send(TupleReaderAgent,achieve,read_next_patient_data_tuple).

+!close_vote_session <-
	.println("Vote session ended").

+commitment(AgentName, MisId, SchId): MisId == final_judge_mission <-
	+final_judge_agent(AgentName). 


{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have a agent that always complies with its organization  
{ include("$jacamoJar/templates/org-obedient.asl") }
