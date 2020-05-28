package org.fresh.basket.controller;

import java.util.List;

import org.fresh.basket.dto.CategoryDto;
import org.fresh.basket.dto.ItemsDto;
import org.fresh.basket.dto.SearchDto;
import org.fresh.basket.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
	
	@Autowired
	BasketService basketService;  
	
	@GetMapping(value="searchItems")
	public ResponseEntity<List<ItemsDto>> searchItems(SearchDto searchDto) {
		List<ItemsDto> itemsDtos = basketService.searchItems(searchDto);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(itemsDtos);
	}
	
	@GetMapping(value="searchCategory")
	public ResponseEntity<List<CategoryDto>> searchCategory(@RequestParam(required=false) String name,
			@RequestParam(name="orderBy", required=false, defaultValue="asc") String order){
		List<CategoryDto> categoryDtos = basketService.searchCategory(name, order);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoryDtos);
	}
}
