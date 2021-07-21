package com.quietsimple.foodpathapi.animal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quietsimple.foodpathapi.animal_item_relation.AnimalItemRelation;
import com.quietsimple.foodpathapi.animal_item_relation.AnimalItemRelationService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AnimalService animalService;
	
	@Autowired
	AnimalItemRelationService animalItemRelationService;
	
	@GetMapping()
	public List<AnimalDTO> getAllAnimals() {
		List<Animal> animals = animalService.getAllAnimals();
		List<AnimalDTO> animalDTOs = new ArrayList<AnimalDTO>();
		for(Animal animal: animals) {
			AnimalDTO animalDTO = convertToDTO(animal);
			animalDTOs.add(animalDTO);
		}
		return animalDTOs;
	}
	
	@GetMapping("/{id}")
	public AnimalDTO getAnimal(@PathVariable Long id) {
		Animal animal = animalService.getAnimalById(id);
		return convertToDTO(animal);
	}
	
	@PostMapping()
	public AnimalDTO addAnimal(@RequestBody AnimalDTO animalDTO) {
		Animal animal = convertToEntity(animalDTO);
		Animal createdAnimal = animalService.addAnimal(animal);
		return convertToDTO(createdAnimal);
	}
	
	@PutMapping()
	public void updateAnimal(@RequestBody AnimalDTO animalDTO) {
		Animal animal = convertToEntity(animalDTO);
		animalService.updateAnimal(animal);
	}
	
	@DeleteMapping(value="/{id}")
	public void deleteAnimal(@PathVariable Long id) {
		animalService.deleteAnimalById(id);
	}
	
	private AnimalDTO convertToDTO(Animal animal) {
		AnimalDTO animalDTO = modelMapper.map(animal, AnimalDTO.class);
		animalDTO = convertEntityRelationsForDTO(animal, animalDTO);
		return animalDTO;
	}
	
	private AnimalDTO convertEntityRelationsForDTO(Animal animal, AnimalDTO animalDTO) {
		List<AnimalItemRelation> animalItemRelations = animalItemRelationService.getAnimalItemRelationsByAnimalId(animal.getId());
		List<Long> animalItemRelationIds = animalItemRelations.stream().map(r -> r.getId()).toList();
		animalDTO.setAnimalItemRelationIds(animalItemRelationIds);
		return animalDTO;
	}
	
	private Animal convertToEntity(AnimalDTO animalDTO) {
		Animal animal = modelMapper.map(animalDTO, Animal.class);
		if (animal.getId() != null) {
			Animal oldAnimal = animalService.getAnimalById(animal.getId());
			animal.setCreatedDate(oldAnimal.getCreatedDate());
			animal.setAnimalItemRelations(oldAnimal.getAnimalItemRelations());
		}		
		return animal;
	}
}
