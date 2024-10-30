package fr.erick.ecom.order.domain.user.vo;

import fr.erick.ecom.shared.error.domain.Assert;

public record UserLastname(String value) {

  public UserLastname {
    Assert.field("value", value).maxLength(255);
  }
}
