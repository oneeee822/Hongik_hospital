package umc.hongik_hospital.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReserveResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResponseDTO{
        private String pname;
        private String hname;
        private String dname;
        private String docname;
        private LocalDate appointmentDate;
        private LocalTime createdAt;
    }
}
