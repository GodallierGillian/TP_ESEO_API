package com.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		System.out.println("getVilleByCodePostal");
		ArrayList<Ville> listeVille = villeBloService.getInfoVilles(codePostal);
		return listeVille;
	}
	@RequestMapping(value="/villeByName", method=RequestMethod.GET)
	@ResponseBody
	public ArrayList<Ville> getVilleByName(@RequestParam(required = false, value= "nomCommune")String nomCommune){
		System.out.println("getVilleByName");
		ArrayList<Ville> listeVille = villeBloService.getInfoVillesbyName(nomCommune);
		return listeVille;
	}
	@RequestMapping(value="/villeByCodeCommuneINSEE", method=RequestMethod.GET)
	@ResponseBody
	public ArrayList<Ville> getVilleByCodeCommuneINSEE(@RequestParam(required = false, value= "codeCommuneINSEE")String codeCommuneINSEE){
		System.out.println("getVilleByCodeCommuneINSEE");
		ArrayList<Ville> listeVille = villeBloService.getInfoVillesbyCodeCommuneINSEE(codeCommuneINSEE);
		return listeVille;
	}
	
	@PostMapping(value="/ajouterVille")
	public ResponseEntity<String> post(@RequestBody Ville ville) {
		System.out.println("post");
		String response = villeBloService.addVille(ville);
		HttpStatus httpStatus;
		if(response.equals("Ville ajoutée à la base de données")) {
			httpStatus = HttpStatus.CREATED;
		}
		else {
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(httpStatus).body(response);
	}
	@PutMapping(value="/modifierVille")
	public ResponseEntity<String> put(@RequestBody Ville ville, @RequestParam(required = true, value = "codeCommuneINSEE") String codeCommuneINSEE) {
		System.out.println("put");
		String response = villeBloService.modifyVille(codeCommuneINSEE,ville);
		HttpStatus httpStatus;
		if(response.equals("Ville modifiée avec succès")) {
			httpStatus = HttpStatus.ACCEPTED;
		}
		else {
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(httpStatus).body(response);
	}
	@DeleteMapping(value="/supprimerVille")
	public ResponseEntity<String> delete(@RequestParam(required = true, value= "codeCommuneINSEE")String codeCommuneINSEE) {
		System.out.println("delete");
		String response = villeBloService.deleteVille(codeCommuneINSEE);
		HttpStatus httpStatus;
		if(response.equals("Ville supprimée avec succès")) {
			httpStatus = HttpStatus.ACCEPTED;
		}
		else {
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(httpStatus).body(response);
	}
	
}
