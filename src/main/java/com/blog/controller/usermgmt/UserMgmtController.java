package com.blog.controller.usermgmt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.controller.AbstractController;
import com.blog.security.modal.TUser;
import com.blog.service.usermgmt.UserMgmtService;

@Controller
@RequestMapping("/usermgmt")
public class UserMgmtController extends AbstractController {
	
	@Autowired
	private UserMgmtService userMgmtService;
	
	@RequestMapping("/findUsers.json")
	@ResponseBody
	public Map<String, Object>  findUsers(HttpSession httpSession,String username) {
		if(!StringUtils.hasLength(username)) {
			username = httpSession.getAttribute("j_username") + "";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		List<TUser> users = userMgmtService.findUser(map);
		if(users == null || users.size() <= 0) {
			map.put(RESULT, FAILURE);
			map.put(DATA, "");
			map.put(MESSAGE, "未发现用户");
		} else {
			map.put(RESULT, SUCCESS);
			map.put(DATA, users.get(0));
			map.put(MESSAGE, "");
		}
		return map;
	}
	
}
