package easycounter.mapper;

import easycounter.dto.ProductDTO;
import easycounter.model.Product;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProductMapper {

    ProductDTO productToProductDTO(Product product);

    Product productDtoToProduct(ProductDTO productDto);
}
