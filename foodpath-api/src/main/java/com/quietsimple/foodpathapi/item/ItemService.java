package com.quietsimple.foodpathapi.item;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

	/*	List<Animal> animals = Arrays.asList(
	new Animal(1, "pig", "looks healthy"),
	new Animal(2, "pig", "looks sick"),
	new Animal(3, "pig", "has hurt left foot")
	);*/
	
	@Autowired
	private ItemRepository itemRepository;
	
	public List<Item> getAllItems() {
		List<Item> items = new ArrayList<>();		
		itemRepository.findAll().forEach(items::add); 
		return items;
	}
	
	public Item addItem(Item item) {
		return itemRepository.save(item);
	}
	
	public Item updateItem(Item item) {
		return itemRepository.save(item);
	}
	
	public void deleteItemById(Long id) {
		itemRepository.deleteById(id);
	}

	public Item getItemById(Long id) {
		return itemRepository.findById(id).get();
	}

}
