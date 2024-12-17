package umc.hongik_hospital.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import umc.hongik_hospital.converter.PatientConverter;
import umc.hongik_hospital.domain.Patient;
import umc.hongik_hospital.repository.PatientRepository;
import umc.hongik_hospital.util.JwtUtil;
import umc.hongik_hospital.web.dto.PatientRequestDTO;
import umc.hongik_hospital.web.dto.PatientResponseDTO;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final PatientRepository patientRepository;
    private final JwtUtil jwtUtil;

    @Override
    public Patient joinPatient(PatientRequestDTO.JoinDto request) {

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        Patient patient = PatientConverter.toPatient(request, encodedPassword);

        return patientRepository.save(patient);
    }

    @Override
    public PatientResponseDTO.LoginResponseDTO login(PatientRequestDTO.LoginRequestDTO request) {
        // 사용자 정보 검증
        Patient patient = patientRepository.findByPnameAndResidentNumAndGender(
                        request.getPname(), request.getResidentNum(), request.getGender())
                .orElseThrow(() -> new RuntimeException("사용자 정보가 일치하지 않습니다."));

        // 비밀번호 검증
        if (!passwordEncoder.matches(request.getPassword(), patient.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        // JWT 토큰 생성
        String token = jwtUtil.generateToken(request.getPname());


        return PatientConverter.toLoginResponseDTO(patient.getId(), token);
    }
}
