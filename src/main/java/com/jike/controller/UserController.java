package com.jike.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jike.entity.User;
import com.jike.service.IUserService;

@Controller
public class UserController {

	@Resource
	IUserService service;

	@RequestMapping("/index")
	public String getIndexPage() {
		return "index";
	}

	@RequestMapping("/jdbc/save")
	public void saveUserByMyBatis(@RequestParam String uName, @RequestParam int uAge) {
		service.saveWithJDBC(uName, uAge);
		System.out.println("JDBC save success");
	}

	@RequestMapping("/jdbc/all")
	public void selectAll() {

		List<User> list = service.allWithJDBC();
		for (User user : list) {
			System.out.println(user.toString());
		}
	}

	@RequestMapping("/jdbc/select/{uId}")
	public void selectByUId(@PathVariable Integer uId) {
		User user = service.selectByIdWithJDBC(uId);
		if (user != null) {
			System.out.println(user.toString());
		} else {
			System.out.println("not found");
		}
	}

	@RequestMapping("/jdbc/delete/{uId}")
	public void deleteByMyBatis(@PathVariable Integer uId) {
		service.deleteByIdWithJDBC(uId);
		System.out.println("JDBC delete success");

	}

	@RequestMapping("/mybatis/all")
	public void selectAllByMyBatis() {

		List<User> list = service.allWithMyBatis();
		for (User user : list) {
			System.out.println(user.toString());
		}
	}

	@RequestMapping("/mybatis/select/{uId}")
	public void selectByUIdByMyBatis(@PathVariable Integer uId) {
		User user = service.selectByIdWithMyBatis(uId);
		if (user != null) {
			System.out.println(user.toString());
		} else {
			System.out.println("not found");
		}
	}

	@RequestMapping("/mybatis/delete/{uId}")
	public void delete(@PathVariable Integer uId) {
		service.deleteByIdWithMyBatis(uId);
		System.out.println("JDBC delete success");

	}

	@RequestMapping("/mybatis/save")
	public void saveUser(@RequestParam String uName, @RequestParam int uAge) {
		service.saveWithMyBatis(uName, uAge);
		System.out.println("JDBC save success");
	}
}
