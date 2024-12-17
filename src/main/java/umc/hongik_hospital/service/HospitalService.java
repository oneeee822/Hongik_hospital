package umc.hongik_hospital.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.hongik_hospital.converter.HospitalConverter;
import umc.hongik_hospital.domain.Hospital;
import umc.hongik_hospital.repository.HospitalRepository;
import umc.hongik_hospital.web.dto.HospitalResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public List<HospitalResponseDTO.ListResponseDTO> getAllHospitals() {
        return hospitalRepository.findAll().stream()
                .map(HospitalConverter::toResponseDTO)
                .collect(Collectors.toList());
    }

    public HospitalResponseDTO.DepartmentListDTO getHospitalWithDepartments(Long hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new IllegalArgumentException("병원을 찾을 수 없습니다. ID: " + hospitalId));

        return HospitalConverter.toHospitalWithDepartmentsDTO(hospital);
    }
}
