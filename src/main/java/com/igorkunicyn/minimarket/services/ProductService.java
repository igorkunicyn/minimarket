package com.igorkunicyn.minimarket.services;

import com.igorkunicyn.minimarket.entities.Category;
import com.igorkunicyn.minimarket.entities.Product;
import com.igorkunicyn.minimarket.enums.AddProduct;
import com.igorkunicyn.minimarket.repositories.CategoryRepository;
import com.igorkunicyn.minimarket.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

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

    public String addProduct(Product product) {
        for (Product products : productRepo.findAll()) {
            if (product.getTitleEnglish().equals(products.getTitleEnglish()) && product.getPrice() == products.getPrice()) {
                return AddProduct.PRODUCT_EXISTS.getName();
            }
        }
        productRepo.save(product);
        return AddProduct.ADD_PRODUCT.getName();
    }

    public Page<Product> findPaginated(int pageNum) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return productRepo.findAll(pageable);
    }

    public Page<Product> findPaginated(int pageNum, long id) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return productRepo.findAllByCategoryId(pageable, id);
    }

    public void saveProduct(Product product){
        Category category = categoryRepo.findById(product.getNumberOfCategory());
        category.addProduct(product);
        productRepo.save(product);

    }

    public Product getProductById(long id){
        return productRepo.findProductById(id);
    }

    public void deleteProduct(long categoryId, long productId){
        Product product = getProductById(productId);
        Category category = categoryRepo.findById(categoryId);
        category.removeProduct(product);
        productRepo.delete(product);
    }

}
