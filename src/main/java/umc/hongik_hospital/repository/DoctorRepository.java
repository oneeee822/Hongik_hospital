package umc.hongik_hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.hongik_hospital.domain.Doctor;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByDepartmentId(Long departmentId);
}
