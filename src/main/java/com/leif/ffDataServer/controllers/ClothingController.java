package com.leif.ffDataServer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leif.ffDataServer.domain.stock.Clothing;
import com.leif.ffDataServer.repositories.InventoryRepository;

@RestController
@RequestMapping("/inventory/clothing")
//@RestResource(description="gets the clothings", path="")
//@Secured("USER")
public class ClothingController extends InventoryController<Clothing>
{
	@Autowired
	public ClothingController(InventoryRepository<Clothing> repository)
	{
		super(repository);
		
		System.out.println(">>> ctor ClothingController");
	}
}
