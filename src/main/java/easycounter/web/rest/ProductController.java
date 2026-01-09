package easycounter.web.rest;

import easycounter.dto.ProductDTO;
import easycounter.model.Product;
import easycounter.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/product/create")
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO product) {
        productService.create(product);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(uri)
                .body(product);
    }

    @PutMapping("/product/update")
    public ResponseEntity<Void> update(@RequestBody ProductDTO product) {
        if (productService.update(product)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        log.debug("REST request to get all Products");
        List<ProductDTO> entityList = productService.findAll();
        return ResponseEntity.ok().body(entityList);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> get(@PathVariable Long id) {
        log.debug("REST request to get Product by Id: {}", id);
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete Product: {}", id);
        if (productService.deleteById(id)) {
            ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
