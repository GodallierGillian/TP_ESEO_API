package com.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blo.VilleBLO;
import com.dto.Ville;
@RestController
public class VilleController {
	@Autowired
	VilleBLO villeBloService;
	
	@RequestMapping(value="/ville", method=RequestMethod.GET)
	@ResponseBody
	public ArrayList<Ville> get(@RequestParam(required = false, value= "codePostal")String codePostal){
		System.out.println("get");
		ArrayList<Ville> listeVille = villeBloService.getInfoVilles(codePostal);
		return listeVille;
	}
	
	@PostMapping(value="/ajouterVille")
	public String post(@RequestBody Ville ville) {
		System.out.println("post");
		String response = villeBloService.addVille(ville);
		return response;
	}
	@PutMapping(value="/modifierVille")
	public String put(@RequestBody Ville ville, @RequestParam(required = true, value = "codeCommuneINSEE") String codeCommuneINSEE) {
		System.out.println("put");
		String response = villeBloService.modifyVille(codeCommuneINSEE,ville);
		return response;
	}
	@DeleteMapping(value="/supprimerVille")
	public String delete(@RequestParam(required = false, value= "codeCommuneINSEE")String codeCommuneINSEE) {
		System.out.println("delete");
		String response = villeBloService.deleteVille(codeCommuneINSEE);
		return response;
	}
	
}
