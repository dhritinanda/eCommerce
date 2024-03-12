package net.example.eCommerce.service.impl;

import net.example.eCommerce.entity.ProductDetails;
import net.example.eCommerce.repository.ProductRepository;
import net.example.eCommerce.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    @Override
    public ProductDetails addProduct(ProductDetails product){
        ProductDetails savedProduct=productRepository.save(product);
        return savedProduct;
    }

    @Override
    public List<ProductDetails> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductDetails getProductById(Long id){
        ProductDetails productDetails = productRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Product does not exists"));
        return productDetails;

    }

    public List<ProductDetails> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }


    @Override
    public List<ProductDetails> getProductsByPriceRange(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }


    @Override
    public List<ProductDetails> getProductsGreaterThanPrice(double price) {
        return productRepository.findGreaterThanPrice(price);
    }

    @Override
    public List<ProductDetails>getProductsLessThanPrice(double price){
        return productRepository.findLessThanPrice(price);
    }

    @Override
    public void deleteProduct(Long id) {
        ProductDetails productDetails= productRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account does not exists"));

        productRepository.deleteById(id);
    }





}
