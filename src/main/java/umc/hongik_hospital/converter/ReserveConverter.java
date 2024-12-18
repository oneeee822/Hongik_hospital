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

    public static ReserveResponseDTO.CreateResponseDTO toResponseDTO(Reserve reserve, Hospital hospital, Department department) {
        return ReserveResponseDTO.CreateResponseDTO.builder()
                .pname(reserve.getPatient().getPname())    // 환자 이름
                .hname(hospital.getHname())               // 병원 이름
                .dname(department.getDname())           // 부서 이름
                .docname(reserve.getDoctor().getDocname())       // 의사 이름
                .appointmentDate(reserve.getAppointmentDate())   // 진료 날짜
                .createdAt(reserve.getCreatedAt())               // 예약 생성 시간
                .build();
    }
}
