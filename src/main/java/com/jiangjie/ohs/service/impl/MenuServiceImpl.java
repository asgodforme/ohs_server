package com.jiangjie.ohs.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jiangjie.ohs.dto.Menu;
import com.jiangjie.ohs.dto.SubMenu;
import com.jiangjie.ohs.entity.OhsMenu;
import com.jiangjie.ohs.repository.OhsMenuRepository;
import com.jiangjie.ohs.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private OhsMenuRepository ohsMenuRepository;
	
	/**
	 * 组装前台需要的菜单结构
	 */
	public List<Menu> packageMenu() {
		OhsMenu qryOhsMenu = new OhsMenu();
		qryOhsMenu.setIsHide("0");
		List<OhsMenu> ohsMenus = ohsMenuRepository.findAll(Example.of(qryOhsMenu));
		List<Menu> menus = new ArrayList<>();
		ohsMenus.stream().filter(ohsMenu -> StringUtils.isEmpty(ohsMenu.getParentMenuId())).forEach(ohsMenu -> {
			Menu menu = new Menu();
			menu.setId(String.valueOf(ohsMenu.getId()));
			menu.setParentMenuName(ohsMenu.getParentMenuName());
			menu.setRole(ohsMenu.getRole());
			menu.setParentMenuDesc(ohsMenu.getParentMenuDesc());
			List<SubMenu> subMenus = new ArrayList<>();
			menu.setSubMenus(subMenus);
			for (OhsMenu om : ohsMenus) {
				if (!StringUtils.isEmpty(om.getParentMenuId()) && om.getParentMenuId().equals(String.valueOf(ohsMenu.getId()))) {
					SubMenu subMenu = new SubMenu();
					subMenu.setId(String.valueOf(om.getId()));
					subMenu.setSubMenuName(om.getSubMenuName());
					subMenu.setSubMenuUrl(om.getSubMenuUrl());
					subMenu.setRole(om.getRole());
					subMenus.add(subMenu);
				}
			}
			menus.add(menu);
		});
		return menus;
	}
}
