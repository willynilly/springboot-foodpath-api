package com.quietsimple.foodpathapi.animal_item_relation;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.quietsimple.foodpathapi.animal.Animal;
import com.quietsimple.foodpathapi.item.Item;

// Based on https://www.baeldung.com/jpa-many-to-many

@Entity
@Table(name="animal_item_relations")
public class AnimalItemRelation {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="animal_id")
	private Animal animal;
	
	@ManyToOne
	@JoinColumn(name="item_id")
	private Item item;
	
	private String kind;
	private String description;
	
	@JsonFormat(pattern="YYYY-MM-dd")
	private Date createdDate;
	
	public AnimalItemRelation() {
		this.createdDate = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
	
}
