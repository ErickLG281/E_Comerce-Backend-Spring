package fr.erick.ecom.order.domain.orderd.aggregate;

import fr.erick.ecom.product.domain.vo.PublicId;
import org.jilt.Builder;

@Builder
public record DetailCartItemRequest(PublicId productId, long quantity) {
}
