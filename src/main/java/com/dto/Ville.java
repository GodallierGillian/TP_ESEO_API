package com.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
// auto generate data based on parameters using lombock
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ville implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6395198119695585656L;
	private String codeCommune;
	private String nomCommune;
	private String codePostal;
	private Coordonnee coordonnee;
}
