package fr.erick.ecom.order.domain.orderd.service;

import fr.erick.ecom.order.domain.orderd.aggregate.DetailCartItemRequest;
import fr.erick.ecom.order.domain.orderd.aggregate.Order;
import fr.erick.ecom.order.domain.orderd.aggregate.OrderedProduct;
import fr.erick.ecom.order.domain.orderd.repository.OrderRepository;
import fr.erick.ecom.order.domain.orderd.vo.StripeSessionId;
import fr.erick.ecom.order.domain.user.aggregate.User;
import fr.erick.ecom.order.infrastructure.secondary.service.stripe.StripeService;
import fr.erick.ecom.product.domain.aggregate.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderCreator {

  private final OrderRepository orderRepository;
  private final StripeService stripeService;

  public OrderCreator(OrderRepository orderRepository, StripeService stripeService) {
    this.orderRepository = orderRepository;
    this.stripeService = stripeService;
  }

  public StripeSessionId create(List<Product> productsInformations,
                                List<DetailCartItemRequest> items,
                                User connectedUser) {

    StripeSessionId stripeSessionId = this.stripeService.createPayment(connectedUser,
      productsInformations, items);

    List<OrderedProduct> orderedProducts = new ArrayList<>();

    for(DetailCartItemRequest itemRequest: items) {
      Product productDetails = productsInformations.stream()
        .filter(product -> product.getPublicId().value().equals(itemRequest.productId().value()))
        .findFirst().orElseThrow();

      OrderedProduct orderedProduct = OrderedProduct.create(itemRequest.quantity(), productDetails);
      orderedProducts.add(orderedProduct);
    }

    Order order = Order.create(connectedUser, orderedProducts, stripeSessionId);
    orderRepository.save(order);

    return stripeSessionId;
  }
}
