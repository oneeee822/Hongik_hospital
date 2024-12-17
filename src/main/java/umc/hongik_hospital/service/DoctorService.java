package umc.hongik_hospital.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.hongik_hospital.converter.DoctorConverter;
import umc.hongik_hospital.domain.Doctor;
import umc.hongik_hospital.repository.DoctorRepository;
import umc.hongik_hospital.web.dto.DoctorResponseDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public List<DoctorResponseDTO> getDoctorsByDepartmentId(Long departmentId) {
        List<Doctor> doctorList = doctorRepository.findByDepartmentId(departmentId);
        return DoctorConverter.toDoctorResponseDTOList(doctorList);
    }

}
