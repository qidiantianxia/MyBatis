package com.jike.service;

import java.util.List;

import com.jike.entity.User;

public interface IUserService {

	public void saveWithJDBC(String uName, int uAge);

	public User selectByIdWithJDBC(int uId);

	public List<User> allWithJDBC();

	public void deleteByIdWithJDBC(int uId);

	public void saveWithMyBatis(String uName, int uAge);

	public User selectByIdWithMyBatis(int uId);

	public List<User> allWithMyBatis();

	public void deleteByIdWithMyBatis(int uId);

}
