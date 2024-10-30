package fr.erick.ecom.order.domain.orderd.service;

import fr.erick.ecom.order.domain.orderd.aggregate.Order;
import fr.erick.ecom.order.domain.orderd.repository.OrderRepository;
import fr.erick.ecom.order.domain.user.vo.UserPublicId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class OrderReader {

  private final OrderRepository orderRepository;

  public OrderReader(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public Page<Order> findAllByUserPublicId(UserPublicId userPublicId, Pageable pageable) {
    return orderRepository.findAllByUserPublicId(userPublicId, pageable);
  }

  public Page<Order> findAll(Pageable pageable) {
    return orderRepository.findAll(pageable);
  }
}
