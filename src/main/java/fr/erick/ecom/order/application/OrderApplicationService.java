package fr.erick.ecom.order.application;


import fr.erick.ecom.order.domain.orderd.aggregate.*;
import fr.erick.ecom.order.domain.orderd.repository.OrderRepository;
import fr.erick.ecom.order.domain.orderd.service.CartReader;
import fr.erick.ecom.order.domain.orderd.service.OrderCreator;
import fr.erick.ecom.order.domain.orderd.service.OrderReader;
import fr.erick.ecom.order.domain.orderd.service.OrderUpdater;
import fr.erick.ecom.order.domain.orderd.vo.StripeSessionId;
import fr.erick.ecom.order.domain.user.aggregate.User;
import fr.erick.ecom.order.infrastructure.secondary.service.stripe.StripeService;
import fr.erick.ecom.product.application.ProductsApplicationService;
import fr.erick.ecom.product.domain.aggregate.Product;
import fr.erick.ecom.product.domain.vo.PublicId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderApplicationService {

  private final ProductsApplicationService productsApplicationService;
  private final CartReader cartReader;
  private final UsersApplicationService usersApplicationService;
  private final OrderCreator orderCreator;
  private final OrderUpdater orderUpdater;
  private final OrderReader orderReader;

  public OrderApplicationService(ProductsApplicationService productsApplicationService,
                                 UsersApplicationService usersApplicationService,
                                 OrderRepository orderRepository,
                                 StripeService stripeService) {
    this.productsApplicationService = productsApplicationService;
    this.usersApplicationService = usersApplicationService;
    this.cartReader = new CartReader();
    this.orderCreator = new OrderCreator(orderRepository, stripeService);
    this.orderUpdater = new OrderUpdater(orderRepository);
    this.orderReader = new OrderReader(orderRepository);
  }

  @Transactional(readOnly = true)
  public DetailCartResponse getCartDetails(DetailCartRequest detailCartRequest) {
    List<PublicId> publicIds = detailCartRequest.items().stream().map(DetailCartItemRequest::productId).toList();
    List<Product> productsInformation = productsApplicationService.getProductsByPublicIdsIn(publicIds);
    return cartReader.getDetails(productsInformation);
  }

  @Transactional
  public StripeSessionId createOrder(List<DetailCartItemRequest> items) {
    User authenticatedUser = usersApplicationService.getAuthenticatedUser();
    List<PublicId> publicIds = items.stream().map(DetailCartItemRequest::productId).toList();
    List<Product> productsInformation = productsApplicationService.getProductsByPublicIdsIn(publicIds);
    return orderCreator.create(productsInformation, items, authenticatedUser);
  }

  @Transactional
  public void updateOrder(StripeSessionInformation stripeSessionInformation) {
    List<OrderedProduct> orderedProducts = this.orderUpdater.updateOrderFromStripe(stripeSessionInformation);
    List<OrderProductQuantity> orderProductQuantities = this.orderUpdater.computeQuantity(orderedProducts);
    this.productsApplicationService.updateProductQuantity(orderProductQuantities);
    this.usersApplicationService.updateAddress(stripeSessionInformation.userAddress());
  }

  @Transactional(readOnly = true)
  public Page<Order> findOrdersForConnectedUser(Pageable pageable) {
    User authenticatedUser = usersApplicationService.getAuthenticatedUser();
    return orderReader.findAllByUserPublicId(authenticatedUser.getUserPublicId(), pageable);
  }

  @Transactional(readOnly = true)
  public Page<Order> findOrdersForAdmin(Pageable pageable) {
    return orderReader.findAll(pageable);
  }
}
