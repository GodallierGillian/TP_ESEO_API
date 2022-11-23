package com.blo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.VilleDao;
import com.dto.Ville;
@Service
public class VilleBLOImpl implements VilleBLO{
	//partie métier
	@Autowired
	private VilleDao villeDAO;
	
	public ArrayList<Ville> getInfoVilles(String codePostal){
		ArrayList<Ville> listeVille = villeDAO.findAllVilles(codePostal);
		return listeVille;
	}

	@Override
	public String addVille(Ville ville) {
		String response = villeDAO.addVille(ville);
		return response;
	}

	@Override
	public String modifyVille(String codeCommuneINSEE, Ville ville) {
		String response = villeDAO.modifyVille(codeCommuneINSEE, ville);
		return response;
	}

	@Override
	public String deleteVille(String codeCommuneINSEE) {
		String response = villeDAO.deleteVille(codeCommuneINSEE);
		return response;
	}

	@Override
	public ArrayList<Ville> getInfoVillesbyName(String nomCommune) {
		ArrayList<Ville> listeVille = villeDAO.findAllVillesbyName(nomCommune);
		return listeVille;
	}

	@Override
	public ArrayList<Ville> getInfoVillesbyCodeCommuneINSEE(String codeCommuneINSEE) {
		ArrayList<Ville> listeVille = villeDAO.findAllVillesbyCodeCommuneINSEE(codeCommuneINSEE);
		return listeVille;
	}
	
}
