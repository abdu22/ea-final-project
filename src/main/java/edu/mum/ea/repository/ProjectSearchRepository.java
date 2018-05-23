package edu.mum.ea.repository;

import java.util.List;

import edu.mum.ea.domain.Project;
import edu.mum.ea.utils.SearchProjectParam;

public interface ProjectSearchRepository {
	public List<Project> search(SearchProjectParam params);
}
