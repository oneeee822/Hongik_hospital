package umc.hongik_hospital.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.hongik_hospital.converter.HospitalConverter;
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
}
