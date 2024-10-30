package fr.erick.ecom.order.domain.orderd.vo;

import fr.erick.ecom.shared.error.domain.Assert;

public record OrderQuantity(long value) {

  public OrderQuantity {
    Assert.field("value", value).positive();

  }
}
