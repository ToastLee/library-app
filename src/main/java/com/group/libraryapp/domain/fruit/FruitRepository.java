package com.group.libraryapp.domain.fruit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FruitRepository extends JpaRepository<Fruit, Long> {
    List<Fruit> findByName(String name);
    List<Fruit> findByPriceGreaterThanEqual(long price);
    List<Fruit> findByPriceLessThanEqual(long price);

    long countByName(String name);

    @Query("SELECT SUM(f.price) FROM Fruit f WHERE f.name = :name AND f.status = :status")
    Long sumPriceByNameAndStatus(@Param("name") String name, @Param("status") String status);
}

