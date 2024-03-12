package net.example.eCommerce.controller;

import net.example.eCommerce.entity.ProductDetails;
import net.example.eCommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;



    public ProductController(ProductService productService){
        this.productService=productService;
    }

    //ADD Account REST API
    @PostMapping
    public ResponseEntity<ProductDetails> addProduct(@RequestBody ProductDetails productDetails){
        return new ResponseEntity<>(productService.addProduct(productDetails), HttpStatus.CREATED);
    }

    //   //Get Account REST API


        @GetMapping("/{id}")
        public ResponseEntity<ProductDetails> getaProductById(@PathVariable Long id){
            ProductDetails productDetails= productService.getProductById(id);
            return ResponseEntity.ok(productDetails);
        }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("Account is deleted successfully ");
    }

    @GetMapping
    public List<ProductDetails> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/category/{category}")
    public List<ProductDetails> getProductsByCategory(@PathVariable String category) {
        return productService.getProductsByCategory(category);
    }


    @GetMapping("/productsrange")
    public ResponseEntity<List<ProductDetails>> getProductsByPriceRange(
            @RequestParam double minPrice,
            @RequestParam double maxPrice) {

        // Call your service to retrieve products based on the minPrice and maxPrice parameters
        List<ProductDetails> products = productService.getProductsByPriceRange(minPrice, maxPrice);

        // Return the products in a ResponseEntity with appropriate status code
        return ResponseEntity.ok(products);
    }



//    @GetMapping(path = "/greaterThanPrice/{price}")
//    public ResponseEntity<?> getProductGreaterThanPrice(@PathVariable(name = "price") double price){
//
//        List<ProductDetails> products = productService.getProductGreaterThanPrice(price);
//        if(!products.isEmpty()){
//            return new ResponseEntity<>(products, HttpStatus.OK);
//        }
//        return ResponseEntity.ok().body("No Products Present for Price >= " + price);
//
//    }

    @GetMapping("/greaterThanPrice/{price}")
    public ResponseEntity<?> getProductGreaterThanPrice(@PathVariable(name = "price") double price) {
        List<ProductDetails> products = productService.getProductsGreaterThanPrice(price);

        if (!products.isEmpty()) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products found with price greater than " + price);
        }
    }

    @GetMapping("/lessThanPrice/{price}")
    public ResponseEntity<?> getProductLessThanPrice(@PathVariable(name = "price") double price) {
        List<ProductDetails> products = productService.getProductsLessThanPrice(price);

        if (!products.isEmpty()) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products found with price greater than " + price);
        }
    }



}
