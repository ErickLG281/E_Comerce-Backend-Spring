package fr.erick.ecom.order.domain.orderd.repository;

import fr.erick.ecom.order.domain.orderd.aggregate.Order;
import fr.erick.ecom.order.domain.orderd.aggregate.StripeSessionInformation;
import fr.erick.ecom.order.domain.orderd.vo.OrderStatus;
import fr.erick.ecom.order.domain.user.vo.UserPublicId;
import fr.erick.ecom.product.domain.vo.PublicId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OrderRepository {

  void save(Order order);

  void updateStatusByPublicId(OrderStatus orderStatus, PublicId orderPublicId);

  Optional<Order> findByStripeSessionId(StripeSessionInformation stripeSessionInformation);

  Page<Order> findAllByUserPublicId(UserPublicId userPublicId, Pageable pageable);

  Page<Order> findAll(Pageable pageable);

}
