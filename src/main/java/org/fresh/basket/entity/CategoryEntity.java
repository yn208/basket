package org.fresh.basket.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.fresh.basket.dto.CategoryDto;

@Entity
@Table(name="category")
public class CategoryEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="category_id")
	private Long categoryId;
	private String name;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="category")
	private List<ItemEntity> items;
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ItemEntity> getItems() {
		return items;
	}
	public void setItems(List<ItemEntity> items) {
		this.items = items;
	}
	public CategoryDto toDto() {
		CategoryDto dto = new CategoryDto();
		dto.setCategoryId(this.getCategoryId());
		dto.setName(this.getName());
		return dto;
	}
}
