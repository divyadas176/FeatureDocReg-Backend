package com.registry.featureDocRegistry.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.registry.featureDocRegistry.SequenceGeneratorService;
import com.registry.featureDocRegistry.modal.FeatureDocAdditionResponse;
import com.registry.featureDocRegistry.modal.FeatureDocRegistryModal;
import com.registry.featureDocRegistry.repository.FeatureDocRegistryRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/featureDocReg")

public class FeatureDocRegistryController {

	@Autowired
	FeatureDocRegistryRepository repository;

	FeatureDocAdditionResponse featureDocAdditionResponse = new FeatureDocAdditionResponse();

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@PostMapping(value = "/addData")
	public <T> ResponseEntity<?> addFeatureDoc(@RequestBody FeatureDocRegistryModal featureDocRegistryModalDTO) {
		try {
			int generateID = sequenceGeneratorService.generateSequence(FeatureDocRegistryModal.SEQUENCE_NAME);
			featureDocRegistryModalDTO.setFid(generateID);
			repository.save(featureDocRegistryModalDTO);
			featureDocAdditionResponse.setMessage("Successfully Added new Doc to DB");
			return new ResponseEntity<>(featureDocAdditionResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(value = "/viewAllDocs/{applicationName}")
	List<FeatureDocRegistryModal> getAllDocsUnderApplnName(@PathVariable String applicationName) {
		return repository.findByApplicationName(applicationName);
	}

	@PutMapping(value = "/updateData/{id}")
	public ResponseEntity<?> updateFeatureDoc(@RequestBody FeatureDocRegistryModal featureDocRegistryModalDTO,
			@PathVariable String id) {
		try {
			Optional<FeatureDocRegistryModal> optionalFeatureDoc = repository.findById(Integer.parseInt(id));

			if (optionalFeatureDoc.isPresent()) {
				FeatureDocRegistryModal featureDocRegistryNew = optionalFeatureDoc.get();

				featureDocRegistryNew.setReleaseNo(featureDocRegistryModalDTO.getReleaseNo());
				featureDocRegistryNew.setPpmNo(featureDocRegistryModalDTO.getPpmNo());
				featureDocRegistryNew.setPdsmNo(featureDocRegistryModalDTO.getPdsmNo());
				featureDocRegistryNew.setBusinessCapabilityName(featureDocRegistryModalDTO.getBusinessCapabilityName());
				featureDocRegistryNew.setCategory(featureDocRegistryModalDTO.getCategory());
				featureDocRegistryNew.setHighLevelDesc(featureDocRegistryModalDTO.getHighLevelDesc());
				featureDocRegistryNew.setBusVsTech(featureDocRegistryModalDTO.getBusVsTech());
				featureDocRegistryNew.setImpactedLocs(featureDocRegistryModalDTO.getImpactedLocs());

				repository.save(featureDocRegistryNew);
				FeatureDocAdditionResponse featureDocAdditionResponse = new FeatureDocAdditionResponse();
				featureDocAdditionResponse.setMessage("Successfully updated Doc in DB");
				return new ResponseEntity<>(featureDocAdditionResponse, HttpStatus.OK);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@DeleteMapping(value = "/delete/{id}")
	public <T> ResponseEntity<?> deleteFeatureDoc(@PathVariable String id) {

		try {
			// FeatureDocRegistryModal featureDocRegistryNew =
			// repository.findByFid(Integer.parseInt((id)));
			repository.deleteById(Integer.parseInt(id));
			FeatureDocAdditionResponse featureDocAdditionResponse = new FeatureDocAdditionResponse();
			featureDocAdditionResponse.setMessage("Successfully Deleted Doc from DB");
			return new ResponseEntity<>(featureDocAdditionResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

}
