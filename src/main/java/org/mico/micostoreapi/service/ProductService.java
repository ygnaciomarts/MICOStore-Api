package org.mico.micostoreapi.service;

import org.mico.micostoreapi.dto.ProductDTO;
import org.mico.micostoreapi.model.Product;
import org.mico.micostoreapi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> new ProductDTO(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getStock(),
                        product.getImageUrl(),
                        product.getCreatedAt(),
                        product.getCategory()
                ))
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        Optional<Product> productOpt = productRepository.findById(Math.toIntExact(id));
        return productOpt.map(product -> new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getImageUrl(),
                product.getCreatedAt(),
                product.getCategory()
        )).orElse(null);
    }
}