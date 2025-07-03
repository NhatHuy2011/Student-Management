package dao;

import java.util.List;

import model.User;

public interface IUserDAO extends IGenericDAO<User>{
	User getByUserNameAndPassword(String username, String password);
	List<User> getAll();
	User getOne(int id);
	int insert(User user);
	void update(int id);
	void delete(int id);
	int count();
}
