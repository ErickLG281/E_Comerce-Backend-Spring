package fr.erick.ecom.order.domain.orderd.vo;

import fr.erick.ecom.shared.error.domain.Assert;

public record StripeSessionId(String value) {

  public StripeSessionId {
    Assert.notNull("value", value);
  }
}
