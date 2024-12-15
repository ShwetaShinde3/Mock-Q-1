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
@RequestMapping("/vehicle")
public class VehicleController {
	
	@Autowired
	VehicleRepo repo;
	
	@GetMapping
	public List<Vehicle> getAll() {
		return repo.findAll();
	}

	@GetMapping("/{id}")
	public Vehicle getbyid(@PathVariable int id) {
		return repo.findById(id).orElseThrow();
	}
	
	@PostMapping
	public Vehicle add(@RequestBody Vehicle user) {
		
		return repo.save(user);
	}
	@PutMapping("/{id}")
	public Vehicle update(@RequestBody Vehicle user,@PathVariable int id) {
		Vehicle v1 = repo.findById(id).orElseThrow();
			
			v1.setCarFirst(user.getCarFirst());
			v1.setCarLast(user.getCarLast());
			v1.setYear(user.getYear());
		
		return repo.save(v1);
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		 repo.deleteById(id);
	}
}
