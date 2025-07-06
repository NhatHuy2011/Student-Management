package service;

import java.util.List;

import model.User;

public interface IUserService {
	User getByUserNameAndPassword(String username, String password);
	List<User> getAll();
	User getOne(int id);
	int insert(User user);
	void update(User user);
	void delete(int id);
	int count();
}
