package sn.youdev.repository;

import org.springframework.data.repository.CrudRepository;
import sn.youdev.entities.TacoOrder;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder,Long> {
    List<TacoOrder> readByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
}
