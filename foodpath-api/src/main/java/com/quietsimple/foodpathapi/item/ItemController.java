package com.quietsimple.foodpathapi.item;

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
@RequestMapping("/items")
public class ItemController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	AnimalItemRelationService animalItemRelationService;
	
	@GetMapping()
	public List<ItemDTO> getAllItems() {
		List<Item> items = itemService.getAllItems();
		List<ItemDTO> itemDTOs = new ArrayList<ItemDTO>();
		for(Item item: items) {
			ItemDTO itemDTO = convertToDTO(item);
			itemDTOs.add(itemDTO);
		}
		return itemDTOs;
	}
	
	@GetMapping("/{id}")
	public ItemDTO getItem(@PathVariable Long id) {
		Item item = itemService.getItemById(id);
		return convertToDTO(item);
	}
	
	@PostMapping()
	public ItemDTO addItem(@RequestBody ItemDTO itemDTO) {
		Item item = convertToEntity(itemDTO);
		Item createdItem = itemService.addItem(item);
		return convertToDTO(createdItem);
	}
	
	@PutMapping()
	public void updateItem(@RequestBody ItemDTO itemDTO) {
		Item item = convertToEntity(itemDTO);
		itemService.updateItem(item);
	}
	
	@DeleteMapping(value="/{id}")
	public void deleteItem(@PathVariable Long id) {
		itemService.deleteItemById(id);
	}
	
	private ItemDTO convertToDTO(Item item) {
		ItemDTO itemDTO = modelMapper.map(item, ItemDTO.class);
		itemDTO = convertEntityRelationsForDTO(item, itemDTO);
		return itemDTO;
	}
	
	private ItemDTO convertEntityRelationsForDTO(Item item, ItemDTO itemDTO) {
		List<AnimalItemRelation> animalItemRelations = animalItemRelationService.getAnimalItemRelationsByItemId(item.getId());
		List<Long> animalItemRelationIds = animalItemRelations.stream().map(r -> r.getId()).toList();
		itemDTO.setAnimalItemRelationIds(animalItemRelationIds);
		return itemDTO;
	}
	
	private Item convertToEntity(ItemDTO itemDTO) {
		Item item = modelMapper.map(itemDTO, Item.class);
		if (item.getId() != null) {
			Item oldItem = itemService.getItemById(item.getId());
			item.setCreatedDate(oldItem.getCreatedDate());
			item.setAnimalItemRelations(oldItem.getAnimalItemRelations());
		}		
		return item;
	}
}
