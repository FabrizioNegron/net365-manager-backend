package com.net365.manager.catalog;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) { this.repository = repository; }

    public List<Product> findAll() { return repository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")); }

    public Product findById(String id) { return repository.findById(id).orElseThrow(() -> notFound(id)); }

    public Product create(ProductRequest request) {
        Product product = new Product();
        apply(request, product);
        return repository.save(product);
    }

    public Product update(String id, ProductRequest request) {
        Product product = findById(id);
        apply(request, product);
        return repository.save(product);
    }

    public void delete(String id) { repository.delete(findById(id)); }

    private void apply(ProductRequest source, Product target) {
        target.setName(source.name().trim());
        target.setDescription(source.description() == null ? "" : source.description().trim());
        target.setPrice(source.price());
        target.setImageUrl(source.imageUrl());
        target.setCategory(source.category().trim());
        target.setStock(source.stock());
    }

    private ResponseStatusException notFound(String id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado: " + id);
    }
}
