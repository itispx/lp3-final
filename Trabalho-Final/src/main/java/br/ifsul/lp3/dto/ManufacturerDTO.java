package br.ifsul.lp3.dto;

import java.util.List;

import br.ifsul.lp3.model.Car;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManufacturerDTO {
	private Integer id;
	private String name;
	private Long revenue;
	private String originCountry;
	private List<Car> cars;
}
