package easycounter.repository;

import easycounter.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Transactional
    @Modifying
    @Query("""
        update Product p
        set p.name = :#{#product.name},
        p.caloriesPer100g = :#{#product.caloriesPer100g},
        p.protein = :#{#product.protein},
        p.fat = :#{#product.fat},
        p.carb = :#{#product.carb},
        p.description = :#{#product.description}
        where p.id = :#{#product.id}
        """)
    int update(@Param("product") Product product);
}
