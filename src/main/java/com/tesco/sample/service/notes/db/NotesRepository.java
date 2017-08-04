package com.tesco.sample.service.notes.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.RxJavaCouchbaseTemplate;
import org.springframework.stereotype.Repository;

import com.couchbase.client.java.query.N1qlQuery;
import com.tesco.sample.service.notes.models.Note;

import rx.Observable;

@Repository
public class NotesRepository {

	@Autowired
	RxJavaCouchbaseTemplate rxJavaCouchbaseTemplate;

	public Observable<Note> save(Note note) {
		return rxJavaCouchbaseTemplate.save(note);
	}

	public Observable<Note> findById(String id) {
		return rxJavaCouchbaseTemplate.findById(id, Note.class);
	}

	public Observable<Note> removeNote(Note note) {
		return rxJavaCouchbaseTemplate.remove(note);
	}

	public Observable<Note> findAll() {

		final String query = "SELECT META(default).id as _ID, META(default).cas as _CAS ,content , lable , title , userId FROM  `default`  LIMIT 100";
		N1qlQuery n1qulQuery = N1qlQuery.simple(query);
		return rxJavaCouchbaseTemplate.findByN1QL(n1qulQuery, Note.class);
	}

}
