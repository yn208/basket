package org.fresh.basket.repository;

import java.util.List;

import org.fresh.basket.entity.CategoryEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
	List<CategoryEntity> findByNameContainsIgnoreCase(String name, Sort sort);
}
