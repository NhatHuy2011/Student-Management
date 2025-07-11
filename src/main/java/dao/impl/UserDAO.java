package dao.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import dao.IUserDAO;
import mapper.UserMapper;
import model.User;
import paging.Pageable;

public class UserDAO extends GenericDAO<User> implements IUserDAO{
	
	//Login
	@Override
	public User getByUserNameAndPassword(String username, String password) {
		StringBuilder sql = new StringBuilder("SELECT u.*, r.* FROM users u");
		sql.append(" INNER JOIN roles r ON r.id = u.roleId ");
		sql.append(" WHERE username = ? AND password = ?");
		
		User user = getOne(sql.toString(), new UserMapper(), username, password);
		
		if(user != null) {
			return user;
		} else {
			System.err.println("Lỗi khi đăng nhập...");
			return null;
		}
	}

	@Override
	public List<User> getAll(Pageable pageble) {
		StringBuilder sql = new StringBuilder("SELECT u.*, r.* FROM users u");
		sql.append(" LEFT JOIN roles r ON u.roleId = r.id");
		if (pageble.getSort() != null 
			    && StringUtils.isNotBlank(pageble.getSort().getSortName()) 
			    && StringUtils.isNotBlank(pageble.getSort().getSortBy())) {

			List<String> userColumns = Arrays.asList("id", "username", "fullname", "email", "dob", "sex", "avatar");
			List<String> roleColumns = Arrays.asList("role");

			if (userColumns.contains(pageble.getSort().getSortName())) {
			    sql.append(" ORDER BY u." + pageble.getSort().getSortName() + " " + pageble.getSort().getSortBy());
			} else if (roleColumns.contains(pageble.getSort().getSortName())) {
			    sql.append(" ORDER BY r." + pageble.getSort().getSortName() + " " + pageble.getSort().getSortBy());
			}

		}

		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT "+pageble.getOffset()+", "+pageble.getLimit()+"");
		}
		return getAll(sql.toString(), new UserMapper());
	}
	
	@Override
	public User getOne(int id) {
		StringBuilder sql = new StringBuilder("SELECT u.*, r.* FROM users u");
		sql.append(" LEFT JOIN roles r ON u.roleId = r.id");
		sql.append(" WHERE u.id = ?");
		
		return getOne(sql.toString(), new UserMapper(), id);
	}
	
	@Override
	public int insert(User user) {
		StringBuilder sql = new StringBuilder("INSERT INTO users (username, password,");
		sql.append(" fullname, email, dob, sex, avatar, publicId, roleId)");
		sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, (SELECT id FROM roles r WHERE r.role = ?))");
		
		return insert(sql.toString(), user.getUsername(), user.getPassword(), user.getFullname(),
				user.getEmail(), user.getDob(), user.getSex().name(), user.getAvatar(), user.getPublicId(), user.getRole().getRole());
	}

	@Override
	public void update(User user) {
		StringBuilder sql = new StringBuilder("UPDATE users SET fullname = ?, email = ?, dob = ?,");
		sql.append(" sex = ?, avatar = ?, publicId = ?");
		sql.append(" WHERE id = ?");
		
		update(sql.toString(), user.getFullname(), user.getEmail(), user.getDob(), user.getSex() != null ? user.getSex().name() : null, user.getAvatar(), user.getPublicId(), user.getId());
	}

	@Override
	public void delete(int id) {
		StringBuilder sql = new StringBuilder("DELETE FROM users u WHERE u.id = ?");
		
		delete(sql.toString(), id);
	}

	@Override
	public int getTotal() {
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM users");
		
		return getTotal(sql.toString());	
	}
}
