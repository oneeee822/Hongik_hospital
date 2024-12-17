package umc.hongik_hospital.service;

import umc.hongik_hospital.domain.Patient;
import umc.hongik_hospital.web.dto.PatientRequestDTO;
import umc.hongik_hospital.web.dto.PatientResponseDTO;

public interface PatientService {
    Patient joinPatient(PatientRequestDTO.JoinDto request);
    PatientResponseDTO.LoginResponseDTO login(PatientRequestDTO.LoginRequestDTO request);
}
