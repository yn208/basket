package org.fresh.basket.service;

import java.util.List;
import java.util.Optional;
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
	private ItemsRepository itemsRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	public ItemsDto createItem(ItemsDto itemsDto) {

		Optional<ItemEntity> optional = itemsRepository.findById(itemsDto.getItemId());

		if(optional.isEmpty()) {
			ItemEntity itemEntity = new ItemEntity();
			itemEntity.setItemId(itemsDto.getItemId());
			itemEntity.setName(itemsDto.getName());
			itemEntity.setDesc(itemsDto.getDesc());
			itemEntity.setPrice(itemsDto.getPrice());
			itemEntity.setRating(itemsDto.getRating());
			//		if(itemsDto.getCategory() != null)
			//			itemEntity.setCategory(itemsDto.getCategory().toDto());

			ItemEntity itemEntity2 = itemsRepository.save(itemEntity);

			return itemEntity2.toDto();
		}else {
			throw new RuntimeException("Item already present.");			
		}

	}

	public ItemsDto updateItem(ItemsDto itemsDto) {

		Optional<ItemEntity> optional = itemsRepository.findById(itemsDto.getItemId());

		if(optional.isPresent()) {
			optional.get().setItemId(itemsDto.getItemId());
			optional.get().setName(itemsDto.getName());
			optional.get().setDesc(itemsDto.getDesc());
			optional.get().setPrice(itemsDto.getPrice());
			optional.get().setRating(itemsDto.getRating());
			//		if(itemsDto.getCategory() != null)
			//			itemEntity.setCategory(itemsDto.getCategory().toDto());

			ItemEntity itemEntity2 = itemsRepository.save(optional.get());

			return itemEntity2.toDto();
		}else {
			throw new RuntimeException("Item not found.");			
		}

	}

	public void deleteItem(Long itemId) {
		Optional<ItemEntity> optional = itemsRepository.findById(itemId);

		if(optional.isPresent()) {
			itemsRepository.deleteById(optional.get().getItemId());
		}else {
			throw new RuntimeException("Item not found.");			
		}
	}

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
		return Sort.by(direction, property);
	}
}
