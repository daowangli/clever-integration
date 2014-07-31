package edu.zju.bme.clever.integration.service;

import edu.zju.bme.clever.integration.entity.Patient;

public interface PatientService {

	public Boolean integrate(String patientId);

	public Boolean integrate(int serialNo);
	
	public Patient getCachedOrIntegratePatient(String patientId);
	
}
