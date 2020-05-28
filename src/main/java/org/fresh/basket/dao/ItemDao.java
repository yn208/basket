package org.fresh.basket.dao;

import java.util.List;

import org.fresh.basket.dto.SearchDto;
import org.fresh.basket.entity.ItemEntity;

public interface ItemDao {
	public List<ItemEntity> searchItems(SearchDto searchDto);
}
