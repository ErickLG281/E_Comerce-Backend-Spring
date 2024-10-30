package fr.erick.ecom.order.domain.orderd;

public class CartPaymentException extends RuntimeException {
  public CartPaymentException(String message) {
    super(message);
  }
}
