package com.igorkunicyn.minimarket.services.impl;

import com.igorkunicyn.minimarket.entities.Product;
import com.igorkunicyn.minimarket.repositories.CategoryRepository;
import com.igorkunicyn.minimarket.repositories.ProductRepository;
import com.igorkunicyn.minimarket.services.Serviceable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements Serviceable<Product> {

    private ProductRepository productRepo;
    private CategoryRepository categoryRepo;

    @Autowired
    public void setProductRepo(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @Autowired
    public void setCategoryRepo(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public Page<Product> findPaginated(int pageNum, long id) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return productRepo.findAllByCategoryId(pageable, id);
    }

    @Override
    public boolean save(Product newProduct){
        for (Product oldProduct: getList()) {
            if (newProduct.equals(oldProduct)){
                return true;
            }
        }
        categoryRepo.findById(newProduct.getNumberOfCategory())
                .addProduct(newProduct);
        productRepo.save(newProduct);
        return productRepo.existsById(newProduct.getId());
    }

    @Override
    public Product getById(long id){
        return productRepo.findProductById(id);
    }

    @Override
    public List<Product> getList() {
        return productRepo.findAll();
    }

    @Override
    public boolean delete(long productId){
        categoryRepo.findById(getById(productId).getNumberOfCategory())
            .removeProduct(getById(productId));
        productRepo.delete(getById(productId));
        return !productRepo.existsById(productId);
    }

}
