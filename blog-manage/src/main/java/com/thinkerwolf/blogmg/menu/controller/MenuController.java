package com.thinkerwolf.blogmg.menu.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkerwolf.blog.common.json.JsonBuilder;
import com.thinkerwolf.blogmg.menu.model.Menu;
import com.thinkerwolf.blogmg.menu.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	/**
	 * 获取前台页面栏目
	 * @return
	 */
	@RequestMapping("/syscolumn.json")
	@ResponseBody
	public Map<String, Object> getSysColumn(){
		
		return null;
	}
	
	/**
	 * 后台管理菜单（树形）
	 * @return
	 */
	@RequestMapping("/menus.json")
	@ResponseBody
	public Map<String, Object> getMenus(){
		List<Menu> menus = menuService.getMenus();
		return JsonBuilder.getSucJson(menus);
	}
	
	
	
}
