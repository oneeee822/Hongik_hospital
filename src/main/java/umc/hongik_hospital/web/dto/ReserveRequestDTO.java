package umc.hongik_hospital.web.dto;

import lombok.Getter;

import java.time.LocalDate;

public class ReserveRequestDTO {

    @Getter
    public static class CreateDTO {
        private Long patientId;
        private Long hospitalId;
        private Long departmentId;
        private Long doctorId;
        private LocalDate appointmentDate;
    }
}
