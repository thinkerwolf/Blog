package com.blog.controller.sysmgmt;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.controller.AbstractController;
import com.blog.modal.SysMenu;
import com.blog.service.sysmgmt.SysMgmtService;

@Controller
@RequestMapping("/sysmgmt")
public class SysMgmtController extends AbstractController {
	
	@Autowired
	private SysMgmtService sysMgmtService;
	
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
	@RequestMapping("/sysmenu.json")
	@ResponseBody
	public Map<String, Object> getSysMenu(){
		List<SysMenu> menus = sysMgmtService.getSysMenu();
		return this.renderSuccess(menus);
	}
	
	
	
}
