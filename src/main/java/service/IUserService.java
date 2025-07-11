package service;

import model.User;
import paging.Page;
import paging.Pageable;

public interface IUserService {
	User getByUserNameAndPassword(String username, String password);
	Page<User> getAll(Pageable pageable);
	User getOne(int id);
	int insert(User user);
	void update(User user);
	void delete(int id);
	int getTotal();
}
