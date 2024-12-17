package umc.hongik_hospital.service;

import umc.hongik_hospital.domain.Patient;
import umc.hongik_hospital.web.dto.PatientRequestDTO;

public interface PatientService {
    Patient joinPatient(PatientRequestDTO.JoinDto request);
}
