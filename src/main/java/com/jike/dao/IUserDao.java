package com.jike.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jike.entity.User;

@Repository
public interface IUserDao {

	public void save(User user);

	public User selectById(int id);

	public void deleteById(int id);

	public List<User> selectAll();
}
