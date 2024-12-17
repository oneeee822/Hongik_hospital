package umc.hongik_hospital.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.hongik_hospital.apiPayload.ApiResponse;
import umc.hongik_hospital.service.HospitalService;
import umc.hongik_hospital.web.dto.HospitalResponseDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hospitals")
public class HospitalRestController {

    private final HospitalService hospitalService;

    @GetMapping("/")
    public ApiResponse<List<HospitalResponseDTO.ListResponseDTO>> getAllHospitals() {
        List<HospitalResponseDTO.ListResponseDTO> response = hospitalService.getAllHospitals();
        return ApiResponse.onSuccess(response);
    }

    @GetMapping("/{id}")
    public ApiResponse<HospitalResponseDTO.DepartmentListDTO> getHospitalWithDepartments(@PathVariable Long id) {
        HospitalResponseDTO.DepartmentListDTO response = hospitalService.getHospitalWithDepartments(id);
        return ApiResponse.onSuccess(response);
    }
}

