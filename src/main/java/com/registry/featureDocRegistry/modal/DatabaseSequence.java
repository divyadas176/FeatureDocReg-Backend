package com.registry.featureDocRegistry.modal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "database_sequences")
public class DatabaseSequence {
	
    @Id
    private String id;
    private long seq;
    private DatabaseSequence() {}
    
    private String getId() {
    	return id;
    }
    private void setId(String id) {
    	this.id =id;
    }
    public long getSeq() {
    	return seq;
    }
    public void setSeq(long seq) {
    	this.seq= seq;
    }
}
