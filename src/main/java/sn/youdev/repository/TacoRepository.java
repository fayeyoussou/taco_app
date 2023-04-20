package sn.youdev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.youdev.entities.Taco;
@Repository
public interface TacoRepository extends JpaRepository<Taco,Long> {
}
