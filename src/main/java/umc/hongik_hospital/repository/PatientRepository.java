package umc.hongik_hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.hongik_hospital.domain.Patient;
import umc.hongik_hospital.domain.enums.Gender;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByPnameAndResidentNumAndGender(String pname, String residentNum, Gender gender);
    Optional<Patient> findByIdAndPname(Long id, String pname);
}
