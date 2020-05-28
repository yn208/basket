package org.fresh.basket.service;

import java.util.List;
import java.util.stream.Collectors;

import org.fresh.basket.dto.CategoryDto;
import org.fresh.basket.dto.ItemsDto;
import org.fresh.basket.dto.SearchDto;
import org.fresh.basket.entity.CategoryEntity;
import org.fresh.basket.entity.ItemEntity;
import org.fresh.basket.repository.CategoryRepository;
import org.fresh.basket.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class BasketService {

	@Autowired
	ItemsRepository itemsRepository;
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<ItemsDto> searchItems(SearchDto searchDto) {
		
		List<ItemEntity> itemEntities = itemsRepository.searchItems(searchDto);

		return itemEntities.stream().map(item -> item.toDto()).collect(Collectors.toList());
	}

	public List<CategoryDto> searchCategory(String name, String order) {
		Sort.Direction direction = Sort.Direction.ASC;
		if(StringUtils.hasText(order) && order.equalsIgnoreCase("desc")) {
			direction = Sort.Direction.DESC;
		}
		List<CategoryEntity> categories = null;
		if(StringUtils.hasText(name)) {
			categories = categoryRepository.findByNameContainsIgnoreCase(name, sortByIdAsc(direction, "name"));
		} else {
			categories = categoryRepository.findAll(sortByIdAsc(direction, "name"));		
		}
		return categories.stream().map(m -> m.toDto()).collect(Collectors.toList());
	}
	private Sort sortByIdAsc(Sort.Direction direction, String property) {
        return new Sort(direction, property);
    }
}
