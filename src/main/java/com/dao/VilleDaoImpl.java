package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.dto.Coordonnee;
import com.dto.Ville;
@Repository
public class VilleDaoImpl implements VilleDao{

	
	public ArrayList<Ville> findAllVilles(String codePostal){
		System.out.println("findAllVilles");
		System.out.println(codePostal);
		DaoFactory daoFactory = DaoFactory.getInstance();
		
		ArrayList<Ville> listeVille = new ArrayList<Ville>();
		if(codePostal!=null) {
			try {
				listeVille=findVilleByCodePostal(codePostal, daoFactory);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				System.out.println("error");
			}
		}
		else {
			try {
				listeVille=findAllVille(daoFactory);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				System.out.println("error");
			}
		}
		
		
		return listeVille;
	}

	private ArrayList<Ville> findVilleByCodePostal(String codePostal, DaoFactory daoFactory) throws DaoException {
		Connection connexion = null;
		PreparedStatement pstmt = null;
        ResultSet resultat = null;
        ArrayList<Ville> villes = new ArrayList<Ville>();
        String query = "SELECT * FROM ville_france WHERE Code_Postal=? ;";
        try {
            connexion = daoFactory.getConnection();
            pstmt = connexion.prepareStatement(query);
            pstmt.setString(1, codePostal);
            resultat = pstmt.executeQuery();
            while (resultat.next()) {
            	String codeCommune = resultat.getString("Code_commune_INSEE");
            	String nomCommune = resultat.getString("Nom_commune");
            	float latitude = resultat.getFloat("Latitude");
            	float longitude = resultat.getFloat("Longitude");
            	Ville ville = new Ville(codeCommune, nomCommune, codePostal, new Coordonnee(latitude,longitude));
            	villes.add(ville);
            }
            return villes;
        } catch (SQLException e) {
            throw new DaoException("Impossible de communiquer avec la base de donnees");
        }
        finally {
            try {
                if (connexion != null) {
                	pstmt.close();
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de donnees");
            }
        }
	}
	private ArrayList<Ville> findAllVille(DaoFactory daoFactory) throws DaoException {
		Connection connexion = null;
		Statement statement = null;
        ResultSet resultat = null;
        ArrayList<Ville> villes = new ArrayList<Ville>();
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM ville_france;");
            while (resultat.next()) {
            	String codeCommune = resultat.getString("Code_commune_INSEE");
            	String nomCommune = resultat.getString("Nom_commune");
            	String codePostal = resultat.getString("Code_Postal");
            	float latitude = resultat.getFloat("Latitude");
            	float longitude = resultat.getFloat("Longitude");
            	Ville ville = new Ville(codeCommune, nomCommune, codePostal, new Coordonnee(latitude,longitude));
            	villes.add(ville);
            }
            return villes;
        } catch (SQLException e) {
            throw new DaoException("Impossible de communiquer avec la base de donnees");
        }
        finally {
            try {
                if (connexion != null) {
                	statement.close();
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de donnees");
            }
        }
	}

    
}
