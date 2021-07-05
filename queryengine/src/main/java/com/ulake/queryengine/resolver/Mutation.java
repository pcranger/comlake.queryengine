package com.ulake.queryengine.resolver;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.ulake.queryengine.model.Content;
import com.ulake.queryengine.model.Dataset;
import com.ulake.queryengine.repository.ContentRepository;
import com.ulake.queryengine.repository.DatasetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import javassist.NotFoundException;

@Component
public class Mutation implements GraphQLMutationResolver {
	private ContentRepository contentRepository;
	private DatasetRepository datasetRepository;

	@Autowired
	public Mutation(ContentRepository contentRepository, DatasetRepository datasetRepository) {
		this.contentRepository = contentRepository;
		this.datasetRepository = datasetRepository;
	}
		public Content createContent(String type, List<JsonNode> extra) {
		Content content = new Content();
		content.setType(type);
		content.setExtra(extra);

		contentRepository.save(content);

		return content;
	}
	public Dataset createDataset(String file, String description, String source, List<String> topics, List<JsonNode> extra, Long parent) {
		Dataset dataset = new Dataset();
		dataset.setFile(file);
		dataset.setDescription(description);
		dataset.setSource(source);
		dataset.setTopics(topics);
		dataset.setExtra(extra);
		dataset.setParent(parent);

		datasetRepository.save(dataset);

		return dataset;}


	public boolean deleteDataset(Long id) {
		datasetRepository.deleteById(id);
		return true;
	}

	public Dataset updateDataset(Long id, String file, String description) throws NotFoundException {
		Optional<Dataset> optDataset = datasetRepository.findById(id);

		if (optDataset.isPresent()) {
			Dataset Dataset = optDataset.get();

			if (file != null)
				Dataset.setFile(file);
			if (description != null)
				Dataset.setDescription(description);

			datasetRepository.save(Dataset);
			return Dataset;
		}

		throw new NotFoundException("Not found Dataset to update!");
	}
}
