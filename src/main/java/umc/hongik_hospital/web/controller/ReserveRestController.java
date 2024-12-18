package umc.hongik_hospital.web.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.hongik_hospital.apiPayload.ApiResponse;
import umc.hongik_hospital.service.ReserveService;
import umc.hongik_hospital.web.dto.ReserveRequestDTO;
import umc.hongik_hospital.web.dto.ReserveResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reserves")
@SecurityRequirement(name = "BearerAuth")
public class ReserveRestController {

    private final ReserveService reserveService;

    @PostMapping("/")
    @PreAuthorize("isAuthenticated()") // 로그인한 사용자만 접근 가능
    public ApiResponse<ReserveResponseDTO.CreateResponseDTO> createReserve(@RequestBody ReserveRequestDTO.CreateDTO request) {
        ReserveResponseDTO.CreateResponseDTO response = reserveService.createReserve(request);
        return ApiResponse.onSuccess(response);
    }
}
