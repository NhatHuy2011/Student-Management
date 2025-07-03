package dao.impl;

import java.util.List;

import constant.Constant;
import dao.IUserDAO;
import mapper.UserMapper;
import model.User;

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
	public List<User> getAll() {
		StringBuilder sql = new StringBuilder("SELECT u.*, r.* FROM users u");
		sql.append(" LEFT JOIN roles r ON u.roleId = r.id");
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
		sql.append(" fullname, email, dob, sex, avatar, roleId)");
		sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, (SELECT id FROM roles WHERE name = ?))");
		
		return insert(sql.toString(), user.getUsername(), user.getPassword(), user.getFullname(),
				user.getEmail(), user.getDob(), user.getAvatar(), Constant.ROLE_USER);
	}

	@Override
	public void update(int id) {
		
		
	}

	@Override
	public void delete(int id) {
		
		
	}

	@Override
	public int count() {
		
		return 0;
	}
}
