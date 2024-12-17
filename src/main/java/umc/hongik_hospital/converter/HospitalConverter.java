package umc.hongik_hospital.converter;

import umc.hongik_hospital.domain.Hospital;
import umc.hongik_hospital.web.dto.DepartmentResponseDTO;
import umc.hongik_hospital.web.dto.HospitalResponseDTO;

import java.util.List;
import java.util.stream.Collectors;


public class HospitalConverter {

    public static HospitalResponseDTO.ListResponseDTO toResponseDTO(Hospital hospital) {
        return HospitalResponseDTO.ListResponseDTO.builder()
                .hname(hospital.getHname())
                .address(hospital.getAddress())
                .build();
    }

    public static HospitalResponseDTO.DepartmentListDTO toHospitalWithDepartmentsDTO(Hospital hospital) {
        List<DepartmentResponseDTO> departmentDTOList = hospital.getDepartmentList().stream()
                .map(department -> DepartmentResponseDTO.builder()
                        .dname(department.getDname())
                        .phoneNum(department.getPhoneNum())
                        .build())
                .collect(Collectors.toList());

        return HospitalResponseDTO.DepartmentListDTO.builder()
                .hname(hospital.getHname())
                .address(hospital.getAddress())
                .departmentList(departmentDTOList)
                .build();
    }
}
