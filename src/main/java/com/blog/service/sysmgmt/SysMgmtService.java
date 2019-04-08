package com.blog.service.sysmgmt;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.blog.modal.SysMenu;

@Service
@Transactional
public class SysMgmtService {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	/**
	 * 获取后台管理Menu
	 * @return
	 */
	public List<SysMenu> getSysMenu(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<SysMenu> sysMenus = sqlSession.selectList("com.blog.sysmgmt.getSysMenu");
		sqlSession.close();
		List<SysMenu> menus = new ArrayList<SysMenu>();
		/**
		 * 循环遍历，形成树形结构
		 */
		for(SysMenu sysMenu : sysMenus) {
			//顶级菜单
			if(!StringUtils.hasLength(sysMenu.getParentId())) {
				menus.add(sysMenu);
			}
			for(SysMenu menu: sysMenus) {
				if(sysMenu.getId().equals(menu.getParentId())) {
					if(sysMenu.getChildMenus() == null) {
						sysMenu.setChildMenus(new ArrayList<SysMenu>());
					}
					sysMenu.getChildMenus().add(menu);
				}
			}
		}
		return menus;
	}
	
	
}
