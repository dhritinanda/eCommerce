package net.example.eCommerce.repository;

import net.example.eCommerce.entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductDetails,Long> {
    List<ProductDetails> findByCategory(String category);

    @Query(value = "select * from product where price between :minPrice and :maxPrice ", nativeQuery = true)
    public List<ProductDetails> findByPriceBetween(@Param("minPrice") double minPrice,@Param("maxPrice")double maxPrice);

    @Query(value = "select * from product where price >= :price", nativeQuery = true)
    List<ProductDetails> findGreaterThanPrice(@Param("price") double price);


    @Query(value = "select * from product where price <= :price", nativeQuery = true)
    List<ProductDetails> findLessThanPrice(double price);
}
