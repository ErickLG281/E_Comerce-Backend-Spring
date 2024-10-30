package fr.erick.ecom.order.domain.orderd.vo;

import fr.erick.ecom.shared.error.domain.Assert;

public record OrderPrice(double value) {

  public OrderPrice {
    Assert.field("value", value).strictlyPositive();
  }
}
