package com.major.model;

public class Risk {
	private int id;
	private String name;
	private String content;
	private String probability;
	private String influence;
	private String triggerOrThreshold;
	private Integer submitter;
	private Integer tracer;
	
	public Integer getSubmitter() {
		return submitter;
	}
	public void setSubmitter(Integer submitter) {
		this.submitter = submitter;
	}
	public Integer getTracer() {
		return tracer;
	}
	public void setTracer(Integer tracer) {
		this.tracer = tracer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getProbability() {
		return probability;
	}
	public void setProbability(String probability) {
		this.probability = probability;
	}
	
	public String getInfluence() {
		return influence;
	}
	public void setInfluence(String influence) {
		this.influence = influence;
	}
	
	public String getTriggerOrThreshold() {
		return triggerOrThreshold;
	}
	public void setTriggerOrThreshold(String triggerOrThreshold) {
		this.triggerOrThreshold = triggerOrThreshold;
	}
}
