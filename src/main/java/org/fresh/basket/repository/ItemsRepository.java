package org.fresh.basket.repository;

import org.fresh.basket.dao.ItemDao;
import org.fresh.basket.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends JpaRepository<ItemEntity, Long>, ItemDao {
}
