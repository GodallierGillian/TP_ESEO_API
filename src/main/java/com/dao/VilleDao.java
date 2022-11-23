package com.dao;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleDao {
	ArrayList<Ville> findAllVilles(String codePostal);
	String addVille(Ville ville);
	String modifyVille(String codeCommuneINSEE, Ville ville);
	String deleteVille(String codeCommuneINSEE);
	ArrayList<Ville> findAllVillesbyName(String nomCommune);
	ArrayList<Ville> findAllVillesbyCodeCommuneINSEE(String codeCommuneINSEE);
}
