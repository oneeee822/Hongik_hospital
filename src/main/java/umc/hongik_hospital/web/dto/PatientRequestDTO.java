package umc.hongik_hospital.web.dto;

import lombok.Getter;
import umc.hongik_hospital.domain.enums.Gender;

public class PatientRequestDTO {

    @Getter
    public static class JoinDto {
        String pname;
        Gender gender;
        String residentNum;
        String password;
    }
}
