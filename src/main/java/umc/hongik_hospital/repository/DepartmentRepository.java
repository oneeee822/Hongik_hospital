package umc.hongik_hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.hongik_hospital.domain.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
