package com.quietsimple.foodpathapi.animal_item_relation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalItemRelationRepository extends JpaRepository<AnimalItemRelation, Long> {
	List<AnimalItemRelation> findByAnimalId(Long animalId);
	List<AnimalItemRelation> findByItemId(Long itemId);
}
