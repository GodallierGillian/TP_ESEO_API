package com.blo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.VilleDao;
import com.dto.Ville;
@Service
public class VilleBLOImpl implements VilleBLO{
	//partie métier
	@Autowired
	private VilleDao villeDAO;
	
	public List<Ville> getInfoVilles(String codePostal){
		return villeDAO.findAllVilles(codePostal); 
	}

	@Override
	public String addVille(Ville ville) {
		return villeDAO.addVille(ville);
	}

	@Override
	public String modifyVille(String codeCommuneINSEE, Ville ville) {
		return villeDAO.modifyVille(codeCommuneINSEE, ville);
	}

	@Override
	public String deleteVille(String codeCommuneINSEE) {
		return villeDAO.deleteVille(codeCommuneINSEE);
	}

	@Override
	public List<Ville> getInfoVillesbyName(String nomCommune) {
		return villeDAO.findAllVillesbyName(nomCommune);
	}

	@Override
	public List<Ville> getInfoVillesbyCodeCommuneINSEE(String codeCommuneINSEE) {
		return villeDAO.findAllVillesbyCodeCommuneINSEE(codeCommuneINSEE);
	}
	
}
