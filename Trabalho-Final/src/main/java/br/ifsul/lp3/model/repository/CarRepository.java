package br.ifsul.lp3.model.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifsul.lp3.model.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {

	List<Car> findByModel(String model);

	List<Car> findByColor(String color);

	List<Car> findByReleaseDate(Date date);
}
