package edu.mum.ea.dao;

import java.util.List;

import edu.mum.ea.domain.Project;
import edu.mum.ea.utils.SearchProjectParam;

public interface ProjectSearchDao {
	public List<Project> search(SearchProjectParam params);
}
