package org.booleanuk.app.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    // GET ALL PRODUCTS
    public List<ProductDto> getAllProducts() {
        List<Product> productList = productRepo.findAll();

        return productList.stream().map(product -> {
            return new ProductDto(
                    product.getName(),
                    product.getPrice()
                    // set order list if needed/wanted
            );
        }).toList();
    }

    // CREATE PRODUCT
    public Product createProduct(ProductDto dto) {
        Product product = new Product(dto.getName(), dto.getPrice());
        return productRepo.save(product);
    }

    // GET PRODUCT
    public ProductDto getProduct(int id) {
        Optional<Product> productOpt = productRepo.findById(id);

        if (productOpt.isPresent()) {
            Product p = productOpt.get();
            return new ProductDto(p.getName(), p.getPrice());
        } else
            throw new NoSuchElementException("Product not found.");
    }

    // UPDATE PRODUCT
    public ProductDto updateProduct(int id, ProductDto body) {
        Optional<Product> productOptional = productRepo.findById(id);

        if (productOptional.isPresent()) {
            productOptional.get().setName(body.getName());
            productOptional.get().setPrice(body.getPrice());
            productRepo.save(productOptional.get());
            return body;
        } else
            throw new NoSuchElementException("Could not find product");
    }

    // DELETE PRODUCT
    public ProductDto deleteProduct(int id) {
        Optional<Product> productOptional = productRepo.findById(id);

        if (productOptional.isPresent()) {
            ProductDto dto = new ProductDto(productOptional.get().getName(), productOptional.get().getPrice());
            productRepo.delete(productOptional.get());
            return dto;
        } else
            throw new NoSuchElementException("Could not find customer");
    }

    // Get number of sold products per product
    public List<ProductDto> getNumberOfSoldForProduct() {
        List<Product> products = productRepo.findAll();

        return products.stream().map(p -> new ProductDto(
                        p.getName(), p.getOrderList().size())).toList();
    }

}
