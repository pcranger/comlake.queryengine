package com.ulake.queryengine.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ulake.queryengine.model.Content;
import com.ulake.queryengine.model.Dataset;
import com.ulake.queryengine.repository.ContentRepository;
import com.coxautodev.graphql.tools.GraphQLResolver;

@Component
public class ContentResolver implements GraphQLResolver<Dataset> {
	@Autowired
	private ContentRepository contentRepository;

	public ContentResolver(ContentRepository contentRepository) {
		this.contentRepository = contentRepository;
	}

	public Content getContent(Dataset dataset) {
		return contentRepository.findById(dataset.getContent().getCid()).orElseThrow(null);
	}
}
