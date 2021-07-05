package com.ulake.queryengine.resolver;

import com.ulake.queryengine.model.Content;
import com.ulake.queryengine.model.Dataset;
import com.ulake.queryengine.repository.ContentRepository;
import com.ulake.queryengine.repository.DatasetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class Query implements GraphQLQueryResolver {
	private ContentRepository contentRepository;
	private DatasetRepository datasetRepository;

	@Autowired
	public Query(ContentRepository contentRepository, DatasetRepository datasetRepository) {
		this.contentRepository = contentRepository;
		this.datasetRepository = datasetRepository;
	}

	public Iterable<Content> findAllContents() {
		return contentRepository.findAll();
	}

	public Iterable<Dataset> findAllDatasets() {
		return datasetRepository.findAll();
	}

	public long countContents() {
		return contentRepository.count();
	}

	public long countDatasets() {
		return datasetRepository.count();
	}

}
