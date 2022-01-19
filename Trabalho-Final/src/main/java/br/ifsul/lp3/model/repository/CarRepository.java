package br.ifsul.lp3.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ifsul.lp3.model.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {

	@Query("FROM Car WHERE model LIKE %?1%")
	List<Car> findByModel(String model);

	List<Car> findByColor(String color);
}
