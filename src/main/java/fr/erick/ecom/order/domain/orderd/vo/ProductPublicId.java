package fr.erick.ecom.order.domain.orderd.vo;

import fr.erick.ecom.shared.error.domain.Assert;

import java.util.UUID;

public record ProductPublicId(UUID value) {

  public ProductPublicId {
    Assert.notNull("value", value);
  }
}
