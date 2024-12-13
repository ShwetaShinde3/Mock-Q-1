package com.mock.OneToMany4;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
		User u=new User();
		u.setFirst(user.getFirst());
		u.setLast(user.getLast());
		List<Vehicle> l=new ArrayList<Vehicle>();
		
		for(Vehicle v:user.getVehicle()) {
			Vehicle v1=new Vehicle();
			v1.setCarFirst(v.getCarFirst());
			v1.setCarLast(v.getCarLast());
			v1.setYear(v.getYear());
			v1.setUser(user);
			l.add(v1);
		}
		u.setVehicle(l);
		return repo.save(u);
	}
	@PutMapping("/{id}")
	public User update(@RequestBody User user,@PathVariable int id) {
		User u=repo.findById(id).orElseThrow();
		u.setFirst(user.getFirst());
		u.setLast(user.getLast());
		List<Vehicle> l=u.getVehicle();
		
		for(Vehicle v:user.getVehicle()) {
			Vehicle v1=new Vehicle();
			v1.setCarFirst(v.getCarFirst());
			v1.setCarLast(v.getCarLast());
			v1.setYear(v.getYear());
			v1.setUser(user);
			l.add(v1);
		}
		u.setVehicle(l);
		return repo.save(u);
	}
}
