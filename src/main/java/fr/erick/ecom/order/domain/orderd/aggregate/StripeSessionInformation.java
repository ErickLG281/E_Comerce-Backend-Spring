package fr.erick.ecom.order.domain.orderd.aggregate;

import fr.erick.ecom.order.domain.orderd.vo.StripeSessionId;
import fr.erick.ecom.order.domain.user.vo.UserAddressToUpdate;
import org.jilt.Builder;

import java.util.List;

@Builder
public record StripeSessionInformation(StripeSessionId stripeSessionId,
                                       UserAddressToUpdate userAddress,
                                       List<OrderProductQuantity> orderProductQuantity) {
}
