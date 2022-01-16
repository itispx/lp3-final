package br.ifsul.lp3.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifsul.lp3.model.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {

	List<Manufacturer> findByName(String name);

	List<Manufacturer> findByOriginCountry(String country);

}
