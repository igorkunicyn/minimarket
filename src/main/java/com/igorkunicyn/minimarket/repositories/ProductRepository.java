package com.igorkunicyn.minimarket.repositories;

import com.igorkunicyn.minimarket.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByCategoryId(Pageable pageable, Long id);

    @Override
    <S extends Product> S save(S s);

    Product findProductById(long id);
}
