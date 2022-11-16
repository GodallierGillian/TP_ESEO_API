package com.blo;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleBLO {
	ArrayList<Ville> getInfoVilles(String codePostal);
	String addVille(Ville ville);
	String modifyVille(String codeCommuneINSEE, Ville ville);
	String deleteVille(String codeCommuneINSEE);
}
