package org.booleanuk.app.service;

import org.booleanuk.app.model.dto.product.ProductSalesStatisticsDto;
import org.booleanuk.app.model.dto.product.ProductRequestDto;
import org.booleanuk.app.model.pojo.Product;
import org.booleanuk.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }
    public Product getById(long id) {
        return productRepository.findById(id).orElse(null);
    }
    public Product createProduct(ProductRequestDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setOrderList(new ArrayList<>());
        productRepository.save(product);

        return product;
    }
    public Product updateProduct(long id, ProductRequestDto productDto) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setOrderList(productDto.getOrderList());
            productRepository.save(product);
        }
        return product;
    }
    public Product deleteProduct(long id) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if(existingProduct!=null) {
            productRepository.delete(existingProduct);
        }
        return existingProduct;
    }
    public ProductSalesStatisticsDto productWithSelLNumbers(long id) {

        return new ProductSalesStatisticsDto(productRepository.findById(id).orElse(null));

    }
}