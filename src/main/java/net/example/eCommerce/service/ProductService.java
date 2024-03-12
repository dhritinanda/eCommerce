package net.example.eCommerce.service;

import net.example.eCommerce.entity.ProductDetails;

import java.util.List;

public interface ProductService {
    ProductDetails addProduct(ProductDetails product);

   List<ProductDetails> getAllProducts();
    ProductDetails getProductById(Long id);

    List<ProductDetails> getProductsByCategory(String category);

    List<ProductDetails> getProductsByPriceRange(double minPrice, double maxPrice);


    List<ProductDetails>getProductsGreaterThanPrice(double price);

    List<ProductDetails>getProductsLessThanPrice(double price);

    void deleteProduct(Long id);

}
