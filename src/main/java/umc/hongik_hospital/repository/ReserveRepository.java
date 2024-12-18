package umc.hongik_hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.hongik_hospital.domain.Reserve;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {
}
