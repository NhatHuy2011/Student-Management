package service.impl;

import java.util.List;

import dao.IUserDAO;
import dao.impl.UserDAO;
import model.User;
import service.IUserService;

public class UserService implements IUserService{
	private IUserDAO userDAO;
	
	public UserService() {
		userDAO = new UserDAO();
	}
	
	@Override
	public User getByUserNameAndPassword(String username, String password) {
		User user = userDAO.getByUserNameAndPassword(username, password);
		return user;
	}

	@Override
	public List<User> getAll() {
		List<User> users = userDAO.getAll();
		return users;
	}

	@Override
	public User getOne(int id) {
		User user = userDAO.getOne(id);
		return user;
	}

	@Override
	public int insert(User user) {
		int id = userDAO.insert(user);
		return id;
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
