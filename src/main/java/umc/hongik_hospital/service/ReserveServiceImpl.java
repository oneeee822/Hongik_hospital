package umc.hongik_hospital.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.hongik_hospital.converter.ReserveConverter;
import umc.hongik_hospital.domain.*;
import umc.hongik_hospital.repository.*;
import umc.hongik_hospital.web.dto.ReserveRequestDTO;
import umc.hongik_hospital.web.dto.ReserveResponseDTO;

@Service
@RequiredArgsConstructor
public class ReserveServiceImpl implements ReserveService {

    private final ReserveRepository reserveRepository;
    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;
    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public ReserveResponseDTO.CreateResponseDTO createReserve(ReserveRequestDTO.CreateDTO request) {
        // SecurityContext에서 사용자 ID 가져오기
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Authenticated User ID: " + userId);

        // 환자 조회
        Patient patient = patientRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("환자를 찾을 수 없습니다."));

        // 병원, 부서, 의사 조회
        Hospital hospital = hospitalRepository.findById(request.getHospitalId())
                .orElseThrow(() -> new IllegalArgumentException("병원을 찾을 수 없습니다."));

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("부서를 찾을 수 없습니다."));

        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new IllegalArgumentException("의사를 찾을 수 없습니다."));

        // 예약 생성 및 저장
        Reserve reserve = ReserveConverter.toEntity(request, patient, doctor);
        reserveRepository.save(reserve);

        // 예약 응답 DTO 생성
        return ReserveConverter.toResponseDTO(reserve);
    }

    @Override
    public ReserveResponseDTO.CreateResponseDTO getReserveById(Long id) {
        Reserve reserve = reserveRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 예약을 찾을 수 없습니다."));
        return ReserveConverter.toResponseDTO(reserve);
    }

    @Override
    public void deleteReserve(Long reserveId) {
        // SecurityContext에서 사용자 ID 가져오기
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 예약 조회
        Reserve reserve = reserveRepository.findById(reserveId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 예약을 찾을 수 없습니다."));

        // 예약을 생성한 환자인지 확인
        if (!reserve.getPatient().getId().equals(userId)) {
            throw new IllegalArgumentException("자신의 예약만 삭제할 수 있습니다.");
        }

        // 예약 삭제
        reserveRepository.delete(reserve);
    }
}
