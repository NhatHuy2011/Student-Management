package dao;

import java.util.List;

import model.Role;

public interface IRoleDAO extends IGenericDAO<Role>{
	List<Role> findAll();
}
