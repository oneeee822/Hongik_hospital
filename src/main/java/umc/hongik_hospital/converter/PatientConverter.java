package umc.hongik_hospital.converter;

import umc.hongik_hospital.domain.Patient;
import umc.hongik_hospital.domain.enums.Gender;
import umc.hongik_hospital.web.dto.PatientRequestDTO;
import umc.hongik_hospital.web.dto.PatientResponseDTO;

public class PatientConverter {

    public static PatientResponseDTO.JoinResultDTO toJoinResultDTO(Patient patient) {
        return PatientResponseDTO.JoinResultDTO.builder()
                .patientId(patient.getId())
                .build();
    }

    public static Patient toPatient(PatientRequestDTO.JoinDto request, String encodedPassword) {

        Gender gender = null;

        switch (request.getGender().ordinal()){
            case 0:
                gender = Gender.MALE;
                break;
            case 1:
                gender = Gender.FEMALE;
                break;
        }

        return Patient.builder()
                .pname(request.getPname())
                .residentNum(request.getResidentNum())
                .gender(gender)
                .password(encodedPassword)
                .build();
    }
}
