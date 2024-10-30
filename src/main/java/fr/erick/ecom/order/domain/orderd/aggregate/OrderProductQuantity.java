package fr.erick.ecom.order.domain.orderd.aggregate;

import fr.erick.ecom.order.domain.orderd.vo.OrderQuantity;
import fr.erick.ecom.order.domain.orderd.vo.ProductPublicId;
import org.jilt.Builder;

@Builder
public record OrderProductQuantity(OrderQuantity quantity, ProductPublicId productPublicId) {
}
