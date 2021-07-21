package com.quietsimple.foodpathapi.animal;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AnimalDTO {
	private Long id;
	private String barcode;
	private String kind;
	private String description;
	
	@JsonProperty("animalItemRelationIds")	
	@JsonAlias({"animal_item_relation_ids"})
	private List<Long> animalItemRelationIds;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getBarcode() {
		return barcode;
	}
	
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	public String getKind() {
		return kind;
	}
	
	public void setKind(String kind) {
		this.kind = kind;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Long> getAnimalItemRelationIds() {
		return animalItemRelationIds;
	}
	
	public void setAnimalItemRelationIds(List<Long> animalItemRelationIds) {
		this.animalItemRelationIds = animalItemRelationIds;
	}
}
