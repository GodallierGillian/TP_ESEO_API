package com.dao;

import java.util.List;

import com.dto.Ville;

public interface VilleDao {
	List<Ville> findAllVilles(String codePostal);
	String addVille(Ville ville);
	String modifyVille(String codeCommuneINSEE, Ville ville);
	String deleteVille(String codeCommuneINSEE);
	List<Ville> findAllVillesbyName(String nomCommune);
	List<Ville> findAllVillesbyCodeCommuneINSEE(String codeCommuneINSEE);
}
