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
}
