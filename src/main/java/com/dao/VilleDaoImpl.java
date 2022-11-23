package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dto.Coordonnee;
import com.dto.Ville;

@Repository
public class VilleDaoImpl implements VilleDao{
	private static final Logger logger 
	  = LoggerFactory.getLogger(VilleDaoImpl.class);
	
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
	@Override
	public ArrayList<Ville> findAllVillesbyName(String nomCommune) {
		System.out.println("findAllVilles");
		System.out.println(nomCommune);
		DaoFactory daoFactory = DaoFactory.getInstance();
		
		ArrayList<Ville> listeVille = new ArrayList<Ville>();
		if(nomCommune!=null) {
			try {
				listeVille=findVilleByName(nomCommune, daoFactory);
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

	private ArrayList<Ville> findVilleByName(String name, DaoFactory daoFactory) throws DaoException {
		Connection connexion = null;
		PreparedStatement pstmt = null;
        ResultSet resultat = null;
        ArrayList<Ville> villes = new ArrayList<Ville>();
        String query = "SELECT * FROM ville_france WHERE Nom_Commune=? ;";
        try {
            connexion = daoFactory.getConnection();
            pstmt = connexion.prepareStatement(query);
            pstmt.setString(1, name);
            resultat = pstmt.executeQuery();
            while (resultat.next()) {
            	String codeCommune = resultat.getString("Code_commune_INSEE");
            	String nomCommune = resultat.getString("Nom_commune");
            	String codePostal = resultat.getString("Code_postal");
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
                	connexion.close();  
                }
                if (pstmt != null) {
                	pstmt.close(); 
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de donnees");
            }
        }
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
                	connexion.close();  
                }
                if (pstmt != null) {
                	pstmt.close(); 
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de donnees");
            }
        }
	}
	
	@Override
	public ArrayList<Ville> findAllVillesbyCodeCommuneINSEE(String codeCommuneINSEE) {
		System.out.println("try to find villes by Code Commune INSEE");
		System.out.println(codeCommuneINSEE);
		DaoFactory daoFactory = DaoFactory.getInstance();
		
		ArrayList<Ville> listeVille = new ArrayList<Ville>();
		if(codeCommuneINSEE!=null) {
			try {
				listeVille=findVilleByCodeCommuneINSEE(codeCommuneINSEE, daoFactory);
			} catch (DaoException e) {
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
	
	private ArrayList<Ville> findVilleByCodeCommuneINSEE(String codeCommuneINSEE, DaoFactory daoFactory) throws DaoException {
		Connection connexion = null;
		PreparedStatement pstmt = null;
        ResultSet resultat = null;
        ArrayList<Ville> villes = new ArrayList<Ville>();
        String query = "SELECT * FROM ville_france WHERE Code_commune_INSEE=? ;";
        try {
            connexion = daoFactory.getConnection();
            pstmt = connexion.prepareStatement(query);
            pstmt.setString(1, codeCommuneINSEE);
            resultat = pstmt.executeQuery();
            while (resultat.next()) {
            	String codeCommune = resultat.getString("Code_commune_INSEE");
            	String nomCommune = resultat.getString("Nom_commune");
            	String codePostal = resultat.getString("Code_postal");
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
                	connexion.close();  
                }
                if (pstmt != null) {
                	pstmt.close(); 
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
                	connexion.close();  
                }
                if (statement != null) {
                	statement.close(); 
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de donnees");
            }
        }
	}

	@Override
	public String addVille(Ville ville) {
		DaoFactory daoFactory = DaoFactory.getInstance();
		try {
			addVilleDao(ville, daoFactory);
			return "Ville ajoutée à la base de données";
			
		} catch (DaoException e) {
			return "an error occured";
		}
		
	}
	public void addVilleDao(Ville ville, DaoFactory daoFactory) throws DaoException {
		Connection connexion = null;
		PreparedStatement pstmt = null;
		Statement statement = null;
		String query2 = "COMMIT;";
        int resultat;
    	String query = "INSERT INTO ville_france VALUES ( ?, ?, ?, '', '', ?, ?);";
    	
    	try {
        	connexion = daoFactory.getConnection();
        	statement = connexion.createStatement();
            pstmt = connexion.prepareStatement(query);
            pstmt.setString(1, ville.getCodeCommune());
            pstmt.setString(2, ville.getNomCommune());
            pstmt.setString(3, ville.getCodePostal());
            pstmt.setFloat(4, ville.getCoordonnee().getLatitude());
            pstmt.setFloat(5, ville.getCoordonnee().getLongitude());
            resultat = pstmt.executeUpdate();
            statement.execute(query2);
            
        } catch (SQLException e) {
            throw new DaoException("Impossible de communiquer avec la base de donnees");
        }
        finally {
            try {
                if (connexion != null) {
                	connexion.close();  
                }
                if (pstmt != null) {
                	pstmt.close(); 
                }
                if (statement != null) {
                	statement.close(); 
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de donnees");
            }
        }
	}

	@Override
	public String modifyVille(String codeCommuneINSEE, Ville ville) {
		DaoFactory daoFactory = DaoFactory.getInstance();
		try {
			modifyVilleDao(codeCommuneINSEE, ville, daoFactory);
			return "Ville modifiée avec succès";
			
		} catch (DaoException e) {
			return "an error occured";
		}
	}

	private void modifyVilleDao(String codeCommuneINSEE, Ville ville, DaoFactory daoFactory) throws DaoException {
		Connection connexion = null;
		PreparedStatement pstmt = null;
        int resultat;
        Statement statement = null;
		String query2 = "COMMIT;";
    	String query = "UPDATE ville_france SET Code_commune_INSEE=?, Nom_commune=?,  Code_postal=?, Latitude=?, Longitude=? WHERE Code_commune_INSEE=?";
    	try {
        	connexion = daoFactory.getConnection();
        	statement=connexion.createStatement();
            pstmt = connexion.prepareStatement(query);
            pstmt.setString(1, ville.getCodeCommune());
            pstmt.setString(2, ville.getNomCommune());
            pstmt.setString(3, ville.getCodePostal());
            pstmt.setFloat(4, ville.getCoordonnee().getLatitude());
            pstmt.setFloat(5, ville.getCoordonnee().getLongitude());
            pstmt.setString(6, codeCommuneINSEE);
            resultat = pstmt.executeUpdate();
            statement.execute(query2);
        } catch (SQLException e) {
            throw new DaoException("Impossible de communiquer avec la base de donnees");
        }
        finally {
            try {
                if (connexion != null) {
                	connexion.close();  
                }
                if (pstmt != null) {
                	pstmt.close(); 
                }
                if (statement != null) {
                	statement.close(); 
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de donnees");
            }
        }
	}
	
	@Override
	public String deleteVille(String codeCommuneINSEE) {
		DaoFactory daoFactory = DaoFactory.getInstance();
		try {
			deleteVilleDao(codeCommuneINSEE, daoFactory);
			return "Ville supprimée avec succès";
			
		} catch (DaoException e) {
			return "an error occured";
		}
	}
	
	private void deleteVilleDao(String codeCommuneINSEE, DaoFactory daoFactory) throws DaoException {
		Connection connexion = null;
		PreparedStatement pstmt = null;
        ResultSet resultat = null;
        Statement statement = null;
		String query2 = "COMMIT;";
        String query = "DELETE FROM ville_france WHERE Code_commune_INSEE=? ;";
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            pstmt = connexion.prepareStatement(query);
            pstmt.setString(1, codeCommuneINSEE);
            pstmt.execute();
            statement.execute(query2);
        } catch (SQLException e) {
            throw new DaoException("Impossible de communiquer avec la base de donnees");
        }
        finally {
            try {
                if (connexion != null) {
                	connexion.close();  
                }
                if (pstmt != null) {
                	pstmt.close(); 
                }
                if (statement != null) {
                	statement.close(); 
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de donnees");
            }
        }
	}
	
	

	
}
