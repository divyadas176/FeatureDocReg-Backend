package com.registry.featureDocRegistry.modal;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor

@Document(collection="featureDocCollection")

public class FeatureDocRegistryModal {
	
	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";

	@Id
	private int fid;
	private String applicationName;
	private String releaseNo;
	private String ppmNo;
	private String pdsmNo;
	private String businessCapabilityName;
	private String category;
	private String highLevelDesc;
	private String busVsTech;
	private String impactedLocs;
}
