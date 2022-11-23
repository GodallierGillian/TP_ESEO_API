package com.blo;

import java.util.List;

import com.dto.Ville;

public interface VilleBLO {
	List<Ville> getInfoVilles(String codePostal);
	String addVille(Ville ville);
	String modifyVille(String codeCommuneINSEE, Ville ville);
	String deleteVille(String codeCommuneINSEE);
	List<Ville> getInfoVillesbyName(String nomCommune);
	List<Ville> getInfoVillesbyCodeCommuneINSEE(String codeCommuneINSEE);
}
