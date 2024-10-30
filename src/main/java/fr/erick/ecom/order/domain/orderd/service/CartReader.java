package fr.erick.ecom.order.domain.orderd.service;

import fr.erick.ecom.order.domain.orderd.aggregate.DetailCartResponse;
import fr.codecake.ecom.order.domain.order.aggregate.DetailCartResponseBuilder;
import fr.erick.ecom.product.domain.aggregate.Product;
import fr.erick.ecom.product.domain.aggregate.ProductCart;

import java.util.List;

public class CartReader {

  private Object DetailCartResponseBuilder;

  public CartReader() {
  }

  public DetailCartResponse getDetails(List<Product> products) {
    List<ProductCart> cartProducts = products.stream().map(ProductCart::from).toList();
    return DetailCartResponseBuilder.detailCartResponse().products(cartProducts)
      .build();
  }
}
