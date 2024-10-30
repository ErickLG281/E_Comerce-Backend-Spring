package fr.erick.ecom.order.domain.user.vo;

import fr.erick.ecom.shared.error.domain.Assert;

public record UserFirstname(String value) {

  public UserFirstname {
    Assert.field("value", value).maxLength(255);
  }
}
