package umc.hongik_hospital.converter;

import umc.hongik_hospital.domain.Doctor;
import umc.hongik_hospital.web.dto.DoctorResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class DoctorConverter {
    public static DoctorResponseDTO toDoctorResponseDTO(Doctor doctor) {
        return DoctorResponseDTO.builder()
                .name(doctor.getDocname())
                .career(doctor.getCareer())
                .build();
    }

    public static List<DoctorResponseDTO> toDoctorResponseDTOList(List<Doctor> doctors) {
        return doctors.stream()
                .map(DoctorConverter::toDoctorResponseDTO)
                .collect(Collectors.toList());
    }
}
