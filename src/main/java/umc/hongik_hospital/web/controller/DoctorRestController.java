package umc.hongik_hospital.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import umc.hongik_hospital.apiPayload.ApiResponse;
import umc.hongik_hospital.service.DoctorService;
import umc.hongik_hospital.web.dto.DoctorResponseDTO;
import umc.hongik_hospital.web.dto.HospitalResponseDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DoctorRestController {

    private final DoctorService doctorService;

    @GetMapping("/hospitals/{hospitalId}/departments/{departmentId}/doctors")
    public ApiResponse<List<DoctorResponseDTO>> getDoctorsByHospitalAndDepartment(@PathVariable Long hospitalId, @PathVariable Long departmentId) {
        List<DoctorResponseDTO> doctors = doctorService.getDoctorsByDepartmentId(departmentId);
        return ApiResponse.onSuccess(doctors);
    }
}
