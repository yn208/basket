package org.fresh.basket.controller;

import java.util.List;

import javax.validation.Valid;

import org.fresh.basket.dto.ItemsDto;
import org.fresh.basket.dto.SearchDto;
import org.fresh.basket.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController(value = "basket")
public class ApiController {
	
	@Autowired
	private BasketService basketService;
	
	@PostMapping(value="createItem", consumes = "application/json")
	public ResponseEntity<ItemsDto> createItem(@RequestBody @Valid ItemsDto itemsDto) {
		validateItem(itemsDto);
		ItemsDto itemsDto2 = basketService.createItem(itemsDto);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(itemsDto2);
	}
	
	@PutMapping(value="updateItem", consumes = "application/json")
	public ResponseEntity<ItemsDto> updateItem(@RequestBody @Valid ItemsDto itemsDto) {
		validateItem(itemsDto);
		ItemsDto itemsDto2 = basketService.updateItem(itemsDto);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(itemsDto2);
	}
	
	@DeleteMapping(value="deleteItem")
	public ResponseEntity<Object> deleteItem(Long itemId) {
		basketService.deleteItem(itemId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	@GetMapping(value="searchItems", produces = "application/json")
	public ResponseEntity<List<ItemsDto>> searchItems(@RequestParam(required = false) String name,
			@RequestParam(required = false) Long itemId,
			@RequestParam(defaultValue = "0") Integer rating
//			@RequestParam String category,
//			@RequestParam String order
			) {
		
		if(rating < 0) {
			throw new IllegalArgumentException("Rating must be a positive number");
		}
		
		SearchDto searchDto= new SearchDto();
		searchDto.setItemId(itemId);
		searchDto.setName(name);
		searchDto.setRating(rating);
//		searchDto.setCategory(category);
//		searchDto.setOrder(order);
		
		List<ItemsDto> itemsDtos = basketService.searchItems(searchDto);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(itemsDtos);
	}
	
	
	private static void validateItem(ItemsDto itemsDto) {
		if(itemsDto.getPrice() < 0) {
			throw new IllegalArgumentException("Price must be a positive number");
		}
		if(itemsDto.getRating() < 0) {
			throw new IllegalArgumentException("Rating must be a positive number");
		}
	}
	
	//@GetMapping(value="searchCategory")
//	public ResponseEntity<List<CategoryDto>> searchCategory(@RequestParam(required=false) String name,
//			@RequestParam(name="orderBy", required=false, defaultValue="asc") String order){
//		List<CategoryDto> categoryDtos = basketService.searchCategory(name, order);
//		return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoryDtos);
//	}
}
