package com.registry.featureDocRegistry.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.registry.featureDocRegistry.modal.FeatureDocRegistryModal;

public interface FeatureDocRegistryRepository extends MongoRepository<FeatureDocRegistryModal, Integer> {
	@Query("{'applicationName':?0}")
	List<FeatureDocRegistryModal> findByApplicationName(String applicationName);

	@Query("{'id':?0}")
	FeatureDocRegistryModal findByFid(Integer id);

}
