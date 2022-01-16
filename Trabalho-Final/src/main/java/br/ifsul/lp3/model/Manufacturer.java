package br.ifsul.lp3.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "manufacturer")
@Getter
@Setter
public class Manufacturer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "manufacturer_id")
	private Integer id;

	private String name;

	private Long revenue;

	private String originCountry;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "manufacturer")
	private List<Car> cars;

//	public void addCar(Car c) {
//		if (this.cars == null)
//			this.cars = new ArrayList<>();
//
//		c.setManufacturer(this);
//
//		this.cars.add(c);
//	}
}