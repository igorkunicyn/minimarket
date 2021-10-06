package com.igorkunicyn.minimarket.controllers;

import com.igorkunicyn.minimarket.entities.Category;
import com.igorkunicyn.minimarket.entities.Product;
import com.igorkunicyn.minimarket.services.impl.CategoryServiceImpl;
import com.igorkunicyn.minimarket.services.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController implements Controllable<Product> {

    private ProductServiceImpl productServiceImpl;
    private CategoryServiceImpl categoryServiceImpl;

    @Autowired
    public void setProductService(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @Autowired
    public  void setCategoryService(CategoryServiceImpl categoryServiceImpl){this.categoryServiceImpl = categoryServiceImpl;}

    @RequestMapping(path = "/showProductById", method = RequestMethod.GET)
    @ResponseBody
    public String showProductById(@RequestParam int id) {
        Product product = productServiceImpl.getById(id);
        if (product != null) {
            return String.format("id: %s, title: %s, cost: %s", product.getId(), product.getTitleRussian(), product.getPrice());
        }
        return "Product with id = " + id + " not exists";
    }

    @GetMapping("/list/{categoryId}")
    public String createListProductsFromCategory(@PathVariable(name = "categoryId")long categoryId,
                                                 Model model) {
        return pageProducts(1,model,categoryId);
    }

    @GetMapping(value = "/list/{categoryId}/page/{pageNum}")
    public String pageProducts(@PathVariable(name = "pageNum") int pageNum, Model model,
                               @PathVariable(name = "categoryId") long categoryId) {
        Page<Product> productPage = productServiceImpl.findPaginated(pageNum, categoryId);
        List<Product> productList = productPage.getContent();
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("totalItems", productPage.getTotalElements());
        model.addAttribute("listProducts", productList);
        model.addAttribute("id",categoryId);
        return "product/list";

    }

    @GetMapping("/list/{categoryId}/new")
    public String createNewProduct(@PathVariable(name = "categoryId")long categoryId, Model model) {
        Product product = new Product();
        Category category = categoryServiceImpl.getById(categoryId);
        model.addAttribute("category", category);
        model.addAttribute("product", product);
        return "product/new";
    }


    @Override
    public String getList(Model model) {
        return null;
    }

    @Override
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("product") Product product){
        productServiceImpl.save(product);
        String path = "/product/list/" + product.getNumberOfCategory();
        return "redirect:" + path;
    }


    @Override
    @RequestMapping("/list/{categoryId}/edit/{productId}")
    public ModelAndView edit(@PathVariable(name = "categoryId")long categoryId,
                                        @PathVariable(name = "productId") long productId) {
        ModelAndView modelAndView = new ModelAndView("product/edit");
        Product product = productServiceImpl.getById(productId);
        Category category = categoryServiceImpl.getById(categoryId);
        modelAndView.addObject("category", category);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @Override
    @RequestMapping("/list/{categoryId}/delete/{productId}")
    public String delete(@PathVariable(name = "categoryId") long categoryId,
                                @PathVariable(name = "productId") long productId) {
        productServiceImpl.delete(productId);
        String path = "/product/list/" + categoryId;
        return "redirect:" + path;
    }


}
