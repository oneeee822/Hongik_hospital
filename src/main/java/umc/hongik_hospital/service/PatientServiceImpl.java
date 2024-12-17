package umc.hongik_hospital.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import umc.hongik_hospital.converter.PatientConverter;
import umc.hongik_hospital.domain.Patient;
import umc.hongik_hospital.repository.PatientRepository;
import umc.hongik_hospital.web.dto.PatientRequestDTO;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final PatientRepository patientRepository;

    @Override
    public Patient joinPatient(PatientRequestDTO.JoinDto request) {

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        Patient patient = PatientConverter.toPatient(request, encodedPassword);

        return patientRepository.save(patient);
    }
}
