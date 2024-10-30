package fr.erick.ecom.product.domain.aggregate;

import fr.erick.ecom.product.domain.vo.ProductSize;
import fr.erick.ecom.product.domain.vo.PublicId;
import org.jilt.Builder;

import java.util.List;

@Builder
public record FilterQuery(PublicId categoryId, List<ProductSize> sizes) {
}
