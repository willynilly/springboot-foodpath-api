package com.quietsimple.foodpathapi.animal_item_relation;

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

import com.quietsimple.foodpathapi.animal.AnimalService;
import com.quietsimple.foodpathapi.item.ItemService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/animal-item-relations")
public class AnimalItemRelationController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AnimalService animalService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private AnimalItemRelationService animalItemRelationService;
	
	@GetMapping()
	public List<AnimalItemRelationDTO> getAllAnimalItemRelations() {
		List<AnimalItemRelation> animalItemRelations = animalItemRelationService.getAllAnimalItemRelations();
		List<AnimalItemRelationDTO> animalItemRelationDTOs = new ArrayList<AnimalItemRelationDTO>();
		for(AnimalItemRelation animalItemRelation: animalItemRelations) {
			AnimalItemRelationDTO animalItemRelationDTO = convertToDTO(animalItemRelation);
			animalItemRelationDTOs.add(animalItemRelationDTO);
		}
		return animalItemRelationDTOs;		
	}
	
	@GetMapping("/by-animal/{animal_id}")
	public List<AnimalItemRelationDTO> getAnimalItemRelationsByAnimalId(@PathVariable Long animalId) {
		List<AnimalItemRelation> animalItemRelations = animalItemRelationService.getAnimalItemRelationsByAnimalId(animalId);
		List<AnimalItemRelationDTO> animalItemRelationDTOs = new ArrayList<AnimalItemRelationDTO>();
		for(AnimalItemRelation animalItemRelation: animalItemRelations) {
			AnimalItemRelationDTO animalItemRelationDTO = convertToDTO(animalItemRelation);
			animalItemRelationDTOs.add(animalItemRelationDTO);
		}
		return animalItemRelationDTOs;
	}
	
	@GetMapping("/by-item/{item_id}")
	public List<AnimalItemRelationDTO> getAnimalItemRelationsByItemId(@PathVariable Long itemId) {
		List<AnimalItemRelation> animalItemRelations = animalItemRelationService.getAnimalItemRelationsByItemId(itemId);
		List<AnimalItemRelationDTO> animalItemRelationDTOs = new ArrayList<AnimalItemRelationDTO>();
		for(AnimalItemRelation animalItemRelation: animalItemRelations) {
			AnimalItemRelationDTO animalItemRelationDTO = convertToDTO(animalItemRelation);
			animalItemRelationDTOs.add(animalItemRelationDTO);
		}
		return animalItemRelationDTOs;		
	}
	
	@GetMapping("/{id}")
	public AnimalItemRelationDTO getAnimalItemRelation(@PathVariable Long id) {
		AnimalItemRelation animalItemRelation = animalItemRelationService.getAnimalItemRelationById(id);
		return convertToDTO(animalItemRelation);
	}
	
	@PostMapping()
	public AnimalItemRelationDTO addAnimalItemRelation(@RequestBody AnimalItemRelationDTO animalItemRelationDTO) {
		AnimalItemRelation animalItemRelation = convertToEntity(animalItemRelationDTO);
		AnimalItemRelation createdAnimalItemRelation = animalItemRelationService.addAnimalItemRelation(animalItemRelation);
		return convertToDTO(createdAnimalItemRelation);
	}
	
	@PutMapping()
	public void updateAnimalItemRelation(@RequestBody AnimalItemRelationDTO animalItemRelationDTO) {
		AnimalItemRelation animalItemRelation = convertToEntity(animalItemRelationDTO);
		animalItemRelationService.updateAnimalItemRelation(animalItemRelation);		
	}
	
	@DeleteMapping(value="/{id}")
	public void deleteAnimalItemRelation(@PathVariable Long id) {
		animalItemRelationService.deleteAnimalItemRelationById(id);
	}
	
	private AnimalItemRelationDTO convertToDTO(AnimalItemRelation animalItemRelation) {
		AnimalItemRelationDTO animalItemRelationDTO = modelMapper.map(animalItemRelation, AnimalItemRelationDTO.class);
		animalItemRelationDTO.setAnimalId(animalItemRelation.getAnimal().getId());
		animalItemRelationDTO.setItemId(animalItemRelation.getItem().getId());
		return animalItemRelationDTO;
	}

	private AnimalItemRelation convertToEntity(AnimalItemRelationDTO animalItemRelationDTO) {
		AnimalItemRelation animalItemRelation = modelMapper.map(animalItemRelationDTO, AnimalItemRelation.class);
		if (animalItemRelation.getId() != null) {
			AnimalItemRelation oldAnimalItemRelation = animalItemRelationService.getAnimalItemRelationById(animalItemRelation.getId());			
			animalItemRelation.setKind(oldAnimalItemRelation.getKind());
			animalItemRelation.setDescription(oldAnimalItemRelation.getDescription());
			animalItemRelation.setCreatedDate(oldAnimalItemRelation.getCreatedDate());
		}
		animalItemRelation.setAnimal(animalService.getAnimalById(animalItemRelationDTO.getAnimalId()));
		animalItemRelation.setItem(itemService.getItemById(animalItemRelationDTO.getItemId()));
		
		return animalItemRelation;
	}
}
