package fr.erick.ecom.order.domain.user.vo;

import fr.erick.ecom.shared.error.domain.Assert;

public record AuthorityName(String name) {

  public AuthorityName {
    Assert.field("name", name).notNull();
  }
}
