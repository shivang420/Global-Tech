package com.globaltech.qna.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "topic", schema = "global_tech")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private String id;
	
	@Column(name = "topic")
	private String topic;
	
	@OneToMany
	@JoinColumn(name = "topic_id")
    private List<SubTopic> subTopics;

	//getters and setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public List<SubTopic> getSubTopics() {
		return subTopics;
	}

	public void setSubTopics(List<SubTopic> subTopics) {
		this.subTopics = subTopics;
	}
}
