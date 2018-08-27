package com.jike.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jike.dao.IUserDao;
import com.jike.entity.User;

@Service
public class UserService implements IUserService {

	@Resource
	IUserDao dao;

	@Resource
	JdbcTemplate jdbcTemplate;

	@Transactional
	public void saveWithJDBC(final String uName, final int uAge) {
		final String sql = "insert into User(uName,uAge) values(?,?)";
		/* jdbcTemplate.update(sql, new Object[] { uName, uAge }); */

		KeyHolder key = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				statement.setString(1, uName);
				statement.setInt(2, uAge);
				return statement;
			}
		}, key);

		System.out.println(key.getKey().intValue());
	}

	@Override
	public User selectByIdWithJDBC(int uId) {
		String sql = "select * from User where uId=?";
		final User user = new User();
		jdbcTemplate.query(sql, new Object[] { uId }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				user.setuId(uId);
				user.setuName(rs.getString("uName"));
				user.setuAge(rs.getInt("uAge"));
			}

		});
		return user;
	}

	@Override
	public List<User> allWithJDBC() {
		String sql = "select * from User";
		final List<User> list = new ArrayList<User>();
		jdbcTemplate.query(sql, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {

				User user = new User();
				user.setuId(rs.getInt("uId"));
				user.setuName(rs.getString("uName"));
				user.setuAge(rs.getInt("uAge"));
				list.add(user);
			}

		});
		return list;
	}

	@Transactional
	public void deleteByIdWithJDBC(int uId) {
		String sql = "delete from User where uId=?";
		jdbcTemplate.update(sql, uId);
	}

	@Transactional
	public void saveWithMyBatis(String uName, int uAge) {
		User user = new User();
		user.setuName(uName);
		user.setuAge(uAge);
		dao.save(user);
	}

	@Override
	public User selectByIdWithMyBatis(int uId) {
		return dao.selectById(uId);
	}

	@Override
	public List<User> allWithMyBatis() {
		return dao.selectAll();
	}

	@Transactional
	public void deleteByIdWithMyBatis(int uId) {
		dao.deleteById(uId);
	}

}
