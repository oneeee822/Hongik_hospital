package umc.hongik_hospital.converter;

import umc.hongik_hospital.domain.Hospital;
import umc.hongik_hospital.web.dto.HospitalResponseDTO;


public class HospitalConverter {

    public static HospitalResponseDTO.ListResponseDTO toResponseDTO(Hospital hospital) {
        return HospitalResponseDTO.ListResponseDTO.builder()
                .hname(hospital.getHname())
                .address(hospital.getAddress())
                .build();
    }
}
