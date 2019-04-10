package com.thinkerwolf.blogmg.menu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkerwolf.blogmg.menu.mapper.MenuMapper;
import com.thinkerwolf.blogmg.menu.model.Menu;

@Service
@Transactional
public class MenuService {
	@Autowired
	private MenuMapper menuMapper;

	/**
	 * 获取后台管理Menu
	 * 
	 * @return
	 */
	public List<Menu> getMenus() {
		List<Menu> sysMenus = menuMapper.selectByExample(null);
		List<Menu> menus = new ArrayList<>();
		/**
		 * 循环遍历，形成树形结构
		 */
		for (Menu sysMenu : sysMenus) {
			// 顶级菜单
			if (sysMenu.getParentId() <= 0) {
				menus.add(sysMenu);
			}
			for (Menu menu : sysMenus) {
				if (sysMenu.getId() == menu.getParentId()) {
					sysMenu.getChildren().add(menu);
				}
			}
		}
		return menus;
	}

}
