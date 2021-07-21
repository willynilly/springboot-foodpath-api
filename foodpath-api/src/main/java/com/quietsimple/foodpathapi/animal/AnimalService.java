package com.quietsimple.foodpathapi.animal;

import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;


//import javax.persistence.EntityManager;
//import javax.persistence.Tuple;
//import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

	/*	List<Animal> animals = Arrays.asList(
	new Animal(1, "pig", "looks healthy"),
	new Animal(2, "pig", "looks sick"),
	new Animal(3, "pig", "has hurt left foot")
	);*/
	
	@Autowired
	private AnimalRepository animalRepository;
	
	public List<Animal> getAllAnimals() {
		List<Animal> animals = new ArrayList<>();		
		animalRepository.findAll().forEach(animals::add); 
		return animals;
	}
	
	public Animal addAnimal(Animal animal) {
		return animalRepository.save(animal);
	}
	
	public Animal updateAnimal(Animal animal) {
		return animalRepository.save(animal);
	}
	
	public void deleteAnimalById(Long id) {
		animalRepository.deleteById(id);
	}

	public Animal getAnimalById(Long id) {
		return animalRepository.findById(id).get();
	}

}
