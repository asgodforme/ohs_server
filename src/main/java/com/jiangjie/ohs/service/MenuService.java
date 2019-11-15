package com.jiangjie.ohs.service;

import java.util.List;

import com.jiangjie.ohs.dto.Menu;

public interface MenuService {
	
	/**
	 * 组装前台需要的菜单结构
	 */
	public List<Menu> packageMenu(String tokenName);

}
