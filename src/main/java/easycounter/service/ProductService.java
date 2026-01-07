package easycounter.service;

import easycounter.dto.ProductDTO;
import easycounter.mapper.ProductMapper;
import easycounter.model.Product;
import easycounter.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public Product create(ProductDTO product) {
        var newProduct = productMapper.productDtoToProduct(product);
        productRepository.save(newProduct);
        return newProduct;
    }

    public boolean update(ProductDTO product) {
        var newProduct = productMapper.productDtoToProduct(product);
        return productRepository.update(newProduct) > 0L;
    }

    @Transactional()
    public List<ProductDTO> findAll() {
        log.debug("Request to get all Products");
        return productRepository.findAll()
                .stream()
                .map(productMapper::productToProductDTO)
                .collect(Collectors.toList());
    }
}
