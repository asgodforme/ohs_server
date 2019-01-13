package com.jiangjie.ohs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiangjie.ohs.dto.Menu;
import com.jiangjie.ohs.service.MenuService;


@RestController
@RequestMapping("/api/menu")
public class OhsMenuController {
	
	@Autowired
	private MenuService menuService;
	
	@GetMapping("/getAllMenu")
	public List<Menu> getAllMenu() {
		return menuService.packageMenu();
	}

}
