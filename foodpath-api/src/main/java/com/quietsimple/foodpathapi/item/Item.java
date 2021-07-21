package com.quietsimple.foodpathapi.item;

import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.quietsimple.foodpathapi.animal_item_relation.AnimalItemRelation;

@Entity
@Table(name="items")
public class Item {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	private String barcode;
	private String kind;
	private String description;
	
	@JsonFormat(pattern="YYYY-MM-dd")
	private Date createdDate;
	
	@OneToMany(mappedBy="item")
	private Set<AnimalItemRelation> animalItemRelations;
	
	public Item() {
		this.createdDate = new Date();
	}
	
	public Item(Long id, String barcode, String kind, String description, Set<AnimalItemRelation> animalItemRelations) {
		super();
		this.id = id;
		this.kind = kind;
		this.barcode = barcode;
		this.description = description;
		this.createdDate = new Date();
	}
	
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
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public Set<AnimalItemRelation> getAnimalItemRelations() {
		return animalItemRelations;
	}

	public void setAnimalItemRelations(Set<AnimalItemRelation> animalItemRelations) {
		this.animalItemRelations = animalItemRelations;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
}
