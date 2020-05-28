package org.fresh.basket.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.fresh.basket.dto.ItemsDto;

@Entity
@Table(name="Items")
public class ItemEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="item_id")
	private Long itemId;
	private String name;
	@Column(name="description")
	private String desc;
	private Long price;
	private Integer rating;
	@ManyToOne
	@JoinColumn(name="category_id")
	private CategoryEntity category;
	
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
	public CategoryEntity getCategory() {
		return category;
	}
	public void setCategory(CategoryEntity category) {
		this.category = category;
	}
	
	public ItemsDto toDto() {
		ItemsDto itemsDto = new ItemsDto();
		itemsDto.setItemId(this.getItemId());
		itemsDto.setName(this.getName());
		itemsDto.setDesc(this.getDesc());
		itemsDto.setPrice(this.getPrice());
		itemsDto.setRating(this.getRating());
		if(this.getCategory() != null)
			itemsDto.setCategory(this.getCategory().toDto());
		return itemsDto;
	}
}
