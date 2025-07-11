package service.impl;

import java.util.List;

import dao.IUserDAO;
import dao.impl.UserDAO;
import model.User;
import paging.Page;
import paging.Pageable;
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
	public Page<User> getAll(Pageable pageable) {
		List<User> users = userDAO.getAll(pageable);
		int total = userDAO.getTotal();
		
		return new Page<>(users, pageable.getPage(), pageable.getLimit(), total);
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
	public void update(User user) {
		userDAO.update(user);
	}

	@Override
	public void delete(int id) {
		userDAO.delete(id);
	}

	@Override
	public int getTotal() {
		return userDAO.getTotal();
	}
}
