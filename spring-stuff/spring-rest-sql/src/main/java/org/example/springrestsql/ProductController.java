package org.example.springrestsql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @PostMapping("/create")
    public ResponseEntity<Void> createProduct(@RequestBody Product product) {
        productRepository.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/read")
    public List<Product> readProducts() {
        return productRepository.findAll();
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateProduct(@RequestParam Long id, @RequestParam String name) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        product.get().setName(name);
        productRepository.save(product.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        productRepository.delete(product.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
