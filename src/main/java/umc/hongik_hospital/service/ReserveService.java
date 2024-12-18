package umc.hongik_hospital.service;

import umc.hongik_hospital.web.dto.ReserveRequestDTO;
import umc.hongik_hospital.web.dto.ReserveResponseDTO;

public interface ReserveService {
    ReserveResponseDTO.CreateResponseDTO createReserve(ReserveRequestDTO.CreateDTO request);
    ReserveResponseDTO.CreateResponseDTO getReserveById(Long id);
    void deleteReserve(Long reserveId);
}
