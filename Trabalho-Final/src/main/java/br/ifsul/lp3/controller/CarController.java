package br.ifsul.lp3.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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

import br.ifsul.lp3.dto.CarDTO;
import br.ifsul.lp3.model.Car;
import br.ifsul.lp3.model.repository.CarRepository;

@Controller
@RequestMapping("/car/")
public class CarController {

	private ModelMapper mapper = new ModelMapper();

	@Autowired
	CarRepository repo;

	// Register
	@PostMapping("register")
	public @ResponseBody String register(@RequestBody Car car) {

		repo.save(car);

		return "Car registered";
	}

	// Update by id
	@PutMapping("update")
	public ResponseEntity<Car> update(@RequestBody Car car) {
		if (car.getId() == null) {
			return new ResponseEntity<Car>(HttpStatus.METHOD_NOT_ALLOWED);
		}

		Car saved = repo.save(car);

		return new ResponseEntity<Car>(saved, HttpStatus.OK);
	}

	// Delete by id
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Car> delete(@PathVariable Integer id) {
		Optional<Car> car = repo.findById(id);

		if (car.isPresent()) {
			repo.deleteById(id);

			return new ResponseEntity<Car>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Car>(HttpStatus.NOT_FOUND);
		}
	}

	// Get by id
	@GetMapping("{id}")
	public ResponseEntity<Car> getById(@PathVariable Integer id) {
		Optional<Car> car = repo.findById(id);

		if (car.isPresent()) {
			return new ResponseEntity<Car>(car.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Car>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("listAll/{pageNumber}/{numberOfItems}")
	public @ResponseBody List<CarDTO> listAll(@PathVariable Integer pageNumber, @PathVariable Integer numberOfItems) {

		List<Car> cars = repo.findAll(PageRequest.of(pageNumber, numberOfItems)).getContent();

		List<CarDTO> carsDTO = cars.stream().map(this::convert).collect(Collectors.toList());

		return carsDTO;
	}

	private CarDTO convert(Car car) {
		return mapper.map(car, CarDTO.class);
	}

	// Full search
	@GetMapping("search/{keyword}")
	public @ResponseBody List<Car> search(@PathVariable String keyword) {
		List<Car> cars = repo.findByModel(keyword);

		return cars;
	}

	// Short search
	@GetMapping("shortSearch/{keyword}")
	public @ResponseBody List<CarDTO> shortSearch(@PathVariable String keyword) {
		List<Car> cars = search(keyword);

		List<CarDTO> carsDTO = new ArrayList<CarDTO>();

		for (Car c : cars) {
			CarDTO cdto = new CarDTO();
			cdto.setModel(c.getModel());
			cdto.setColor(c.getColor());
			cdto.setReleaseDate(c.getReleaseDate());
			carsDTO.add(cdto);
		}

		return carsDTO;
	}

	// Search cars by date

	@GetMapping("searchColor/{color}")
	public @ResponseBody List<Car> searchColor(@PathVariable String color) {
		List<Car> cars = repo.findByColor(color);

		return cars;
	}
}
