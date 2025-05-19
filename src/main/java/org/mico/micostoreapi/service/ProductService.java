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

    public ProductDTO createProduct(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setImageUrl(dto.getImageUrl());
        product.setCreatedAt(dto.getCreatedAt());
        product.setCategory(dto.getCategory());
        Product saved = productRepository.save(product);
        return new ProductDTO(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.getPrice(),
                saved.getStock(),
                saved.getImageUrl(),
                saved.getCreatedAt(),
                saved.getCategory()
        );
    }

    public ProductDTO updateProduct(Long id, ProductDTO dto) {
        Optional<Product> productOpt = productRepository.findById(id.intValue());
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setPrice(dto.getPrice());
            product.setStock(dto.getStock());
            product.setImageUrl(dto.getImageUrl());
            product.setCreatedAt(dto.getCreatedAt());
            product.setCategory(dto.getCategory());
            Product updated = productRepository.save(product);
            return new ProductDTO(
                    updated.getId(),
                    updated.getName(),
                    updated.getDescription(),
                    updated.getPrice(),
                    updated.getStock(),
                    updated.getImageUrl(),
                    updated.getCreatedAt(),
                    updated.getCategory()
            );
        }
        return null;
    }

    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id.intValue())) {
            productRepository.deleteById(id.intValue());
            return true;
        }
        return false;
    }
}