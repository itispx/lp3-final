package br.ifsul.lp3.controller;

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
import br.ifsul.lp3.model.Manufacturer;
import br.ifsul.lp3.model.repository.CarRepository;
import br.ifsul.lp3.model.repository.ManufacturerRepository;

@Controller
@RequestMapping("/car/")
public class CarController {

	private ModelMapper mapper = new ModelMapper();

	@Autowired
	CarRepository repo;

	@Autowired
	ManufacturerRepository manufacturerRepo;

	// Register
	@PostMapping("register/{manufacturer_id}")
	public @ResponseBody ResponseEntity<Car> register(@RequestBody Car car, @PathVariable Integer manufacturer_id) {

		Manufacturer manufacturer = manufacturerRepo.findById(manufacturer_id).orElse(null);

		if (manufacturer == null) {
			return new ResponseEntity<Car>(HttpStatus.NOT_FOUND);
		}

		manufacturer.addCar(car);

		car.setManufacturer(manufacturer);

		Car saved = repo.save(car);

		return new ResponseEntity<Car>(saved, HttpStatus.OK);
	}

	// Update by id
	@PutMapping("update")
	public @ResponseBody ResponseEntity<Car> update(@RequestBody Car car) {
		if (car.getId() == null) {
			return new ResponseEntity<Car>(HttpStatus.NOT_FOUND);
		}

		Car oldCar = repo.findById(car.getId()).orElse(null);

		if (oldCar == null) {
			return new ResponseEntity<Car>(HttpStatus.NOT_FOUND);
		}

		Manufacturer manufacturer = oldCar.getManufacturer();

		car.setManufacturer(manufacturer);

		Car saved = repo.save(car);

		return new ResponseEntity<Car>(saved, HttpStatus.OK);
	}

	// Delete by id
	@DeleteMapping("delete/{id}")
	public @ResponseBody ResponseEntity<Car> delete(@PathVariable Integer id) {
		Car car = repo.findById(id).orElse(null);

		if (car == null) {
			return new ResponseEntity<Car>(HttpStatus.NOT_FOUND);
		}

		Manufacturer manufacturer = car.getManufacturer();

		manufacturer.removeCar(id);

		repo.deleteById(id);

		return new ResponseEntity<Car>(HttpStatus.OK);

	}

	// Get by id
	@GetMapping("{id}")
	public @ResponseBody ResponseEntity<Car> getById(@PathVariable Integer id) {
		Optional<Car> car = repo.findById(id);

		if (car.isPresent()) {
			return new ResponseEntity<Car>(car.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Car>(HttpStatus.NOT_FOUND);

	}

	@GetMapping("listAll/{pageNumber}/{numberOfItems}")
	public @ResponseBody List<Car> listAll(@PathVariable Integer pageNumber, @PathVariable Integer numberOfItems) {

		List<Car> cars = repo.findAll(PageRequest.of(pageNumber, numberOfItems)).getContent();

		return cars;
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

		List<CarDTO> carsDTO = cars.stream().map(this::convert).collect(Collectors.toList());

		return carsDTO;
	}

	// Search cars by color
	@GetMapping("searchColor/{color}")
	public @ResponseBody List<Car> searchColor(@PathVariable String color) {
		List<Car> cars = repo.findByColor(color);

		return cars;
	}
}
