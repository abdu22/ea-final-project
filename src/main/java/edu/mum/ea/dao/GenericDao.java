package edu.mum.ea.dao;

import java.util.List;
import java.util.Map;

public interface GenericDao<T> {
	void save(T t);

	void delete(Long id);

	T findOne(Long id);

	public T findOne(Long id, Map<String, Object> hints);

	T update(T t);

	List<T> findAll();

}
