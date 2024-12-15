package com.mock.OneToMany4;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserRepo repo;
	
	@GetMapping
	public List<User> getAll() {
		return repo.findAll();
	}

	@GetMapping("/{id}")
	public User getbyid(@PathVariable int id) {
		return repo.findById(id).orElseThrow();
	}
	
	@PostMapping
	public User add(@RequestBody User user) {
		
		return repo.save(user);
	}
	@PutMapping("/{id}")
	public User update(@RequestBody User user,@PathVariable int id) {
		User u=repo.findById(id).orElseThrow();
		u.setFirst(user.getFirst());
		u.setLast(user.getLast());
		
		return repo.save(u);
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		 repo.deleteById(id);
	}
}
