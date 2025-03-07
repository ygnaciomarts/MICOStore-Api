package org.mico.micostoreapi.repository;

import org.mico.micostoreapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}