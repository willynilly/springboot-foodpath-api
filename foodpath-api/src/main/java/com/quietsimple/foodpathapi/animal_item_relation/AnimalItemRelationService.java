package com.quietsimple.foodpathapi.animal_item_relation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quietsimple.foodpathapi.animal.Animal;
import com.quietsimple.foodpathapi.item.Item;

@Service
public class AnimalItemRelationService {

	/*	List<Animal> animals = Arrays.asList(
	new Animal(1, "pig", "looks healthy"),
	new Animal(2, "pig", "looks sick"),
	new Animal(3, "pig", "has hurt left foot")
	);*/
	
	@Autowired
	private AnimalItemRelationRepository animalItemRelationRepository;
	
	public List<AnimalItemRelation> getAllAnimalItemRelations() {
		List<AnimalItemRelation> animalItemRelations = new ArrayList<>();		
		animalItemRelationRepository.findAll().forEach(animalItemRelations::add); 
		return animalItemRelations;
	}
	
	public AnimalItemRelation addAnimalItemRelation(AnimalItemRelation animalItemRelation) {
		return animalItemRelationRepository.save(animalItemRelation);
	}
	
	public AnimalItemRelation updateAnimalItemRelation(AnimalItemRelation animalItemRelation) {
		return animalItemRelationRepository.save(animalItemRelation);
	}
	
	public void deleteAnimalItemRelationById(Long id) {
		animalItemRelationRepository.deleteById(id);
	}

	public AnimalItemRelation getAnimalItemRelationById(Long id) {
		return animalItemRelationRepository.findById(id).get();
	}
	
	public List<AnimalItemRelation> getAnimalItemRelationsByAnimalId(Long animalId) {
		List<AnimalItemRelation> animalItemRelations = animalItemRelationRepository.findByAnimalId(animalId);
		return animalItemRelations;
	}
	
	public List<AnimalItemRelation> getAnimalItemRelationsByItemId(Long itemId) {
		List<AnimalItemRelation> animalItemRelations = animalItemRelationRepository.findByItemId(itemId);
		return animalItemRelations;
	}

	public List<Item> getItemsByAnimalId(Long animalId) {
		List<AnimalItemRelation> animalItemRelations = animalItemRelationRepository.findByAnimalId(animalId);
		List<Item> items = animalItemRelations.stream().map(r -> r.getItem()).toList();
		return items;
	}
	
	public List<Animal> getAnimalsByItemId(Long itemId) {
		List<AnimalItemRelation> animalItemRelations = animalItemRelationRepository.findByItemId(itemId);
		List<Animal> animals = animalItemRelations.stream().map(r -> r.getAnimal()).toList();
		return animals;
	}
	
//	public List<AnimalItemRelation> getByAnimalId(Long animalId) {
//		List<AnimalItemRelation> animalItemRelations = new ArrayList<>();		
//		//animalItemRelationRepository.find().forEach(animalItemRelations::add); 
//		public List<Long> getMeatProductIds(Long animalId) {
//		TypedQuery<Tuple> q = entityManager.createQuery(
//                "SELECT mpa.meat_product_id AS meat_product_id "
//                + "FROM meat_products_animals AS mpa "
//                + "WHERE mpa.animal_id LIKE :animal_id",
//                Tuple.class);
//			q.setParameter("animal_id", "%1%");
//			List<Tuple> meatProductIdTuples = q.getResultList();
//			List<Long> meatProductIds = meatProductIdTuples.stream()
//				.map(t -> (Long)t.get("meat_product_id"))
//				.collect(Collectors.toList());
//			return meatProductIds;
//	}
		
		
		
//		return animalItemRelations;
//	}

}
