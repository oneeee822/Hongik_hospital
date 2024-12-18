package umc.hongik_hospital.converter;

import umc.hongik_hospital.domain.*;
import umc.hongik_hospital.domain.enums.Rstatus;
import umc.hongik_hospital.web.dto.ReserveRequestDTO;
import umc.hongik_hospital.web.dto.ReserveResponseDTO;

import java.time.LocalTime;

public class ReserveConverter {

    // 예약 엔티티 생성
    public static Reserve toEntity(ReserveRequestDTO.CreateDTO request, Patient patient, Doctor doctor) {
        return Reserve.builder()
                .rstatus(Rstatus.INCOMPLETE)
                .createdAt(LocalTime.now())
                .patient(patient)
                .doctor(doctor)
                .appointmentDate(request.getAppointmentDate())
                .build();
    }

    public static ReserveResponseDTO.CreateResponseDTO toResponseDTO(Reserve reserve) {
        return ReserveResponseDTO.CreateResponseDTO.builder()
                .pname(reserve.getPatient().getPname())
                .hname(reserve.getDoctor().getDepartment().getHospital().getHname())
                .dname(reserve.getDoctor().getDepartment().getDname())
                .docname(reserve.getDoctor().getDocname())
                .appointmentDate(reserve.getAppointmentDate())
                .createdAt(reserve.getCreatedAt())
                .build();
    }
}
