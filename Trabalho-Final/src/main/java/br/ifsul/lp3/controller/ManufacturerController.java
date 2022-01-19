package br.ifsul.lp3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.ifsul.lp3.model.Manufacturer;
import br.ifsul.lp3.model.repository.ManufacturerRepository;

@Controller
@RequestMapping("/manufacturer/")
public class ManufacturerController {

	@Autowired
	ManufacturerRepository repo;

	// Register
	@PostMapping("register")
	public @ResponseBody ResponseEntity<Manufacturer> register(@RequestBody Manufacturer manufacturer) {

		Manufacturer saved = repo.save(manufacturer);

		return new ResponseEntity<Manufacturer>(saved, HttpStatus.OK);
	}

	// Update by id
	@PutMapping("update")
	public @ResponseBody ResponseEntity<Manufacturer> update(@RequestBody Manufacturer manufacturer) {
		System.out.println(manufacturer);
		if (manufacturer.getId() == null) {
			System.out.println("no id");
			return new ResponseEntity<Manufacturer>(HttpStatus.METHOD_NOT_ALLOWED);
		}

		Manufacturer saved = repo.save(manufacturer);

		return new ResponseEntity<Manufacturer>(saved, HttpStatus.OK);
	}

	// Delete by id
	@DeleteMapping("delete/{id}")
	public @ResponseBody ResponseEntity<Manufacturer> delete(@PathVariable Integer id) {
		Optional<Manufacturer> manufacturer = repo.findById(id);

		if (manufacturer.isPresent()) {
			repo.deleteById(id);

			return new ResponseEntity<Manufacturer>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Manufacturer>(HttpStatus.NOT_FOUND);
		}
	}

	// Get by id
	@GetMapping("{id}")
	public @ResponseBody ResponseEntity<Manufacturer> getById(@PathVariable Integer id) {
		Optional<Manufacturer> manufacturer = repo.findById(id);

		if (manufacturer.isPresent()) {
			return new ResponseEntity<Manufacturer>(manufacturer.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Manufacturer>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("listAll/{pageNumber}/{numberOfItems}")
	public @ResponseBody List<Manufacturer> listAll(@PathVariable Integer pageNumber,
			@PathVariable Integer numberOfItems) {

		List<Manufacturer> manufacturers = repo.findAll(PageRequest.of(pageNumber, numberOfItems)).getContent();

		return manufacturers;
	}
}
