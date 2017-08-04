package com.tesco.sample.service.notes.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tesco.sample.service.notes.db.NotesRepository;
import com.tesco.sample.service.notes.models.Note;

import rx.Observable;

@RestController
public class NotesController {

	private static final Logger logger = Logger.getLogger(NotesController.class.getName());

	@Autowired
	private NotesRepository notesRepository;

	@GetMapping(value = "/getNote/{id}")
	public Note findById(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {

		final CountDownLatch countDownLatch = new CountDownLatch(1);

		Observable<Note> observable = notesRepository.findById(id);

		final List<Note> notes = new ArrayList<Note>();

		Note note = null;

		observable.subscribe(

				s -> {

					notes.add(s);

					logger.severe("" + s.getId());

				}, e -> {

					countDownLatch.countDown();

					logger.severe("" + e);

					if (!response.isCommitted()) {

						sendError(400, response);
					}
					return;

				},

				() -> {

					logger.severe("finished ..");
					countDownLatch.countDown();
					sendSuccess(response);
					return;
				}

		);

		try {
			countDownLatch.await();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		if (notes != null && notes.size() == 1) {
			logger.severe("returning response. notes.size -  " + notes.size());
			note = notes.get(0);
		}

		return note;
	}

	@GetMapping("/getNotes")
	public List<Note> getAll(HttpServletRequest request, HttpServletResponse response) {

		final CountDownLatch countDownLatch = new CountDownLatch(1);

		Observable<Note> observable = notesRepository.findAll();

		// return observable;

		final List<Note> notes = new ArrayList<Note>();

		observable.subscribe(

				s -> {

					notes.add(s);

					logger.severe("" + s.getId());

				}, e -> {

					countDownLatch.countDown();

					logger.severe("" + e);

					if (!response.isCommitted()) {

						sendError(400, response);
					}
					return;

				},

				() -> {

					logger.severe("finished ..");
					countDownLatch.countDown();
					sendSuccess(response);
					return;
				}

		);

		try {
			countDownLatch.await();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		logger.severe("returning response " + notes.size());
		return notes;
	}

	@PostMapping(value = "/deleteNote")
	public void removeNote(@RequestBody Note note, HttpServletRequest request, HttpServletResponse response) {

		final CountDownLatch countDownLatch = new CountDownLatch(1);

		Observable<Note> observable = notesRepository.removeNote(note);

		observable.subscribe(

				s -> {

					countDownLatch.countDown();

				}, e -> {

					countDownLatch.countDown();

					logger.severe("" + e);

					if (!response.isCommitted()) {
						sendError(400, response);
					}
					return;

				},

				() -> {

					logger.severe("finished ..");
					countDownLatch.countDown();
					sendSuccess(response);
					return;
				}

		);

		try {
			countDownLatch.await();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

	}

	@PostMapping(value = "/createNote")
	public Note create(@RequestBody Note note, HttpServletRequest request, HttpServletResponse response) {

		Observable<Note> observable = notesRepository.save(note);

		final CountDownLatch countDownLatch = new CountDownLatch(1);

		final List<Note> notes = new ArrayList<Note>();

		Note createdNote = null;

		observable.subscribe(

				s -> {

					notes.add(s);

					logger.severe("" + s.getId());

				}, e -> {

					countDownLatch.countDown();

					logger.severe("" + e);

					if (!response.isCommitted()) {
						sendError(400, response);
					}

					return;

				},

				() -> {

					logger.severe("finished ..");
					countDownLatch.countDown();
					sendSuccess(response);
					return;
				}

		);

		try {
			countDownLatch.await();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		if (notes != null && notes.size() == 1) {

			logger.severe("returning response. notes.size -  " + notes.size());
			createdNote = notes.get(0);
		}

		return createdNote;

	}

	private void sendError(int statusCode, HttpServletResponse response) {

		if (response.isCommitted()) {
			return;
		}
		try {
			response.sendError(400);
		} catch (IOException e) {
			logger.severe("Error:" + e);
		}
	}

	private void sendSuccess(HttpServletResponse response) {

		if (response.isCommitted()) {
			return;
		}
		response.setStatus(200);

	}

}
