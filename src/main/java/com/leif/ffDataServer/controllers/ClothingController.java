package com.leif.ffDataServer.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leif.ffDataServer.domain.stock.Clothing;

@RestController
@RequestMapping("/inventory/clothing")
//@Secured("USER")
public class ClothingController extends InventoryController<Clothing>
{
	public ClothingController()
	{
		System.out.println(">>> ctor ClothingController");
	}
}
