package umc.hongik_hospital.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class HospitalResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListResponseDTO {
        private String hname;
        private String address;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DepartmentListDTO {
        private String hname;
        private String address;
        private List<DepartmentResponseDTO> departmentList;
    }
}
