package sn.youdev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sn.youdev.entities.TacoOrder;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<TacoOrder,Long> {
    List<TacoOrder> readByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
}
