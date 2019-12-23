package com.todos.restful.webServices.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todos.restful.webServices.todo.Todo;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class TodoRessource {
	
	@Autowired
	private TodoHardcodedService todoService;
	
	@GetMapping("/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username) {
	
		return todoService.findAll();
	}
	
	@GetMapping("/users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable String username, @PathVariable long id) {
	
		return todoService.findById(id);
	}
	
	//DELETE /users/{username}/todos/{id}
	
	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> delete(@PathVariable String username, @PathVariable long id) {
		
		Todo todo = todoService.deleteById(id);
		if(todo!=null) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	// Edit/Update a Todo
	// PUT /users/{username}/todos/{id}
	@PutMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo) {
		Todo todoUpdate = todoService.save(todo);
		
		return new ResponseEntity(todo, HttpStatus.OK);
	}
	
	// Create a new Todo
	// POST /users/{username}/todos/
	@PostMapping("/users/{username}/todos")
	public ResponseEntity<Void> updateTodo(@PathVariable String username, @RequestBody Todo todo) {
		Todo todoUpdate = todoService.save(todo);
		
		return new ResponseEntity(todo, HttpStatus.OK);
	}
	
}















