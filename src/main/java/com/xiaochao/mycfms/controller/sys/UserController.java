package com.xiaochao.mycfms.controller.sys;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaochao.mycfms.model.sys.Users;
import com.xiaochao.mycfms.service.iface.sys.UsersService;
import com.xiaochao.mycfms.util.domain.Result;

@Controller
@RequestMapping("/service/users/")
public class UserController {
    
	@Autowired
	public UsersService usersService;
	
	@RequestMapping("queryUser")
	@ResponseBody
	public Result queryUser(String userId) {
		Result result=new Result();
		Users user=usersService.selectUser(Integer.parseInt(userId));
		result.getBody().put("user", user);
		return result;
	}
	
	@RequestMapping("queryUserJSP")
	public String userMVC(HttpServletRequest request,Model model) {
		String userId=request.getParameter("userId");
		Users user=usersService.selectUser(Integer.parseInt(userId));
		model.addAttribute("user", user);
		return "user";
	}
}
