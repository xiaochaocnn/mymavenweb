package com.xiaochao.mycfms.controller.sys;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xiaochao.mycfms.model.sys.Users;
import com.xiaochao.mycfms.service.iface.sys.UsersService;
import com.xiaochao.mycfms.util.Configuration;
import com.xiaochao.mycfms.util.FileUtil;
import com.xiaochao.mycfms.util.domain.Result;

@RestController
@RequestMapping("/service/users/")
public class UserController {

	@Autowired
	public UsersService usersService;
//	@Autowired
//	public Configuration configure4OSS;

	/**
	 * 查询用户信息
	 * 
	 * @author nana.chao
	 * @param userId
	 * @return
	 */
	@RequestMapping("queryUser")
	// @ResponseBody
	public Result queryUser(String userId) {
		Result result = new Result();
		System.out.println("userId:" + userId);
		Users user = usersService.selectUser(Integer.parseInt(userId));
		result.getBody().put("user", user);
		return result;
	}

	@RequestMapping("queryUserJSP")
	public String userMVC(HttpServletRequest request, Model model) {
		String userId = request.getParameter("userId");
		Users user = usersService.selectUser(Integer.parseInt(userId));
		model.addAttribute("user", user);
		return "user";
	}

	@RequestMapping("fileUpload")
	public void fileUploadUser() {
		//File file=new File("/Users/mfhj-dz-001-060/chaochao/mymavenweb/file/fileTest.txt");
		String filename="fileTest.txt";
		Configuration configure4OSS=new Configuration("/properties/remoteInterface.properties");
		FileUtil.uploadLargeFile(configure4OSS, filename);
	}
	
	@RequestMapping("fileUser")
	public void fileUser() throws IOException {
		List<Users> listUsers = new ArrayList<Users>();
		Iterator iter = Files.readAllLines(Paths.get("/Users/mfhj-dz-001-060/chaochao/mymavenweb/file/fileTest.txt"),
				StandardCharsets.UTF_8).iterator();
		while (iter.hasNext()) {
			String line = (String) iter.next();
			String param[] = line.split(",");
			Users user = new Users();
			user.setUserName(param[0]);
			user.setPassword(param[1]);
			if (listUsers.size() >= 2) {
				usersService.insertBatch(listUsers);
				listUsers.clear();
			}
			listUsers.add(user);
		}
		if (listUsers.size() > 0) {
			usersService.insertBatch(listUsers);
		}
	}
}
