package org.fresh.basket.dao;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.fresh.basket.dto.SearchDto;
import org.fresh.basket.entity.CategoryEntity;
import org.fresh.basket.entity.ItemEntity;
import org.springframework.util.StringUtils;

public class ItemDaoImpl implements ItemDao{
	@PersistenceContext
	private EntityManager em;
	public List<ItemEntity> searchItems(SearchDto searchDto) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ItemEntity> cq = cb.createQuery(ItemEntity.class);
		
		Root<ItemEntity> root = cq.from(ItemEntity.class);
		Join<ItemEntity, CategoryEntity> join = root.join("category", JoinType.LEFT);
		
		cq.select(root);
		List<Predicate> predList = new LinkedList<>();
		
		if(StringUtils.hasText(searchDto.getName())) {
			predList.add(cb.like(cb.lower(root.get("name")), "%"+searchDto.getName().toLowerCase()+"%"));
		}
		if(searchDto.getRating() != null) {
			predList.add(cb.greaterThanOrEqualTo(root.get("rating"), searchDto.getRating()));
		}
		if(StringUtils.hasText(searchDto.getCategory())) {
			predList.add(cb.equal(cb.lower(join.get("name")), searchDto.getCategory().toLowerCase()));
		}
		Predicate[] predArray = new Predicate[predList.size()];
		predList.toArray(predArray);
		cq.where(predArray);
		
		Order order = null;
		if(searchDto.getOrder() != null) {
			String[] strings = searchDto.getOrder().split(":");
			order = sort(cb, root, strings);
		} else {
			order = cb.desc(root.get("rating"));
		}
		
		cq.orderBy(order);
		
		TypedQuery<ItemEntity> query = em.createQuery(cq);
		
		List<ItemEntity> results = query.getResultList();
		return results;
	}
	private Order sort(CriteriaBuilder cb, Root<ItemEntity> root, String[] strings) {
		String validList[] = {"name","rating","price"};
		if(!Arrays.stream(validList).anyMatch(strings[0]::equalsIgnoreCase)) {
			return cb.desc(root.get("rating"));
		}
		if(strings.length > 1 && strings[1].equalsIgnoreCase("desc")) {
			return cb.desc(root.get(strings[0]));
		} else {
			return cb.asc(root.get(strings[0]));
		}
	}
}
