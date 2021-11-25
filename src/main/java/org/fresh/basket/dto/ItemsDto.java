package org.fresh.basket.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;

@ApiModel
public class ItemsDto {
    @NotNull(message = "itemId is mandatory")
    @ApiParam(required = true)
	private Long itemId;
    @NotBlank(message = "Name is mandatory")
    @ApiParam(required = true)
	private String name;
	private String desc;
	private Long price;
	private Integer rating;
	//	private CategoryDto category;

	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	//	public CategoryDto getCategory() {
	//		return category;
	//	}
	//	public void setCategory(CategoryDto category) {
	//		this.category = category;
	//	}
}
