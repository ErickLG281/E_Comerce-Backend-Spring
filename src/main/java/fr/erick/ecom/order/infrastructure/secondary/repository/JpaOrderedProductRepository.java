package fr.erick.ecom.order.infrastructure.secondary.repository;

import fr.erick.ecom.order.infrastructure.secondary.entity.OrderedProductEntity;
import fr.erick.ecom.order.infrastructure.secondary.entity.OrderedProductEntityPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderedProductRepository extends JpaRepository<OrderedProductEntity, OrderedProductEntityPk> {

}
