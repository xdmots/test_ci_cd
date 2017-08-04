package com.tesco.sample.service.notes.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Field;

@Document
public class Note {

	@Id
	private String id;
	@Field
	private String userId;
	@Field
	private String title;
	@Field
	private String content;
	@Field
	private String creationDateTime;
	@Field
	private String modificationDateTime;
	@Field
	private boolean archived;
	@Field
	private String label;

	public Note() {

	}

	public Note(String id, String userId, String title, String content, String creationDateTime, String modificationDateTime,
			boolean archived, String label) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.creationDateTime = creationDateTime;
		this.modificationDateTime = modificationDateTime;
		this.archived = archived;
		this.label = label;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(String creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public String getModificationDateTime() {
		return modificationDateTime;
	}

	public void setModificationDateTime(String modificationDateTime) {
		this.modificationDateTime = modificationDateTime;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "Note [id=" + id + "\n userId=" + userId + "\n title=" + title + "\n content=" + content + "\n creationDateTime="
				+ creationDateTime + "\n modificationDateTime=" + modificationDateTime + "\n archived=" + archived + "\n label=" + label
				+ "]";
	}

}
