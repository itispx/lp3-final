package br.ifsul.lp3.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDTO {
	private String model;
	private String color;
	private Date releaseDate;
}
