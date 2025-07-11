package dao;

import java.util.List;

import model.User;
import paging.Pageable;

public interface IUserDAO extends IGenericDAO<User>{
	User getByUserNameAndPassword(String username, String password);
	List<User> getAll(Pageable pageable);
	User getOne(int id);
	int insert(User user);
	void update(User user);
	void delete(int id);
	int getTotal();
}
