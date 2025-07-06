package dao;

import java.util.List;

import mapper.RowMapper;
import model.User;

public interface IGenericDAO<T> {
	List<T> getAll(String sql, RowMapper<T> mapper, Object...parameters);
	T getOne(String sql, RowMapper<T> mapper, Object...parameters);
	int insert(String sql, Object...parameters);
	void update(String sql, Object...parameters);
	void delete(String sql, Object...parameters);
	int count(String sql, Object...parameters);
}
