package fr.erick.ecom.order.infrastructure.primary.order;

import fr.erick.ecom.order.domain.orderd.aggregate.Order;
import fr.erick.ecom.order.domain.orderd.vo.OrderStatus;
import org.jilt.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record RestOrderRead(UUID publicId,
                            OrderStatus status,
                            List<RestOrderedItemRead> orderedItems) {

  public static RestOrderRead from(Order order) {
    return RestOrderReadBuilder.restOrderRead()
      .publicId(order.getPublicId().value())
      .status(order.getStatus())
      .orderedItems(RestOrderedItemRead.from(order.getOrderedProducts()))
      .build();
  }
}
