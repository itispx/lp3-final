package br.ifsul.lp3.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManufacturerDTO {
	private String name;
	private Long revenue;
	private String originCountry;
}
