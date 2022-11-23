package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.dto.Coordonnee;
import com.dto.Ville;

@Repository
public class VilleDaoImpl implements VilleDao{
	private static final Logger logger 
	  = LoggerFactory.getLogger(VilleDaoImpl.class);
	private static final String ERROR = "an error occured";
	private static final String CODE_COMMUNE_INSEE = "Code_commune_INSEE";
	private static final String NOM_COMMUNE = "Nom_commune";
	private static final String CODE_POSTAL = "Code_postal";
	private static final String LATITUDE = "Latitude";
	private static final String LONGITUDE = "Longitude";
	
	public List<Ville> findAllVilles(String codePostal){
		DaoFactory daoFactory = DaoFactory.getInstance();
		
		List<Ville> listeVille = new ArrayList<>();
		if(codePostal!=null) {
			try {
				listeVille=findVilleByCodePostal(codePostal, daoFactory);
			} catch (DaoException e) {
				logger.info(ERROR);
			}
		}
		else {
			try {
				listeVille=findAllVille(daoFactory);
			} catch (DaoException e) {
				logger.info(ERROR);
			}
		}
		return listeVille;
	}
	@Override
	public List<Ville> findAllVillesbyName(String nomCommune) {
		DaoFactory daoFactory = DaoFactory.getInstance();
		
		List<Ville> listeVille = new ArrayList<>();
		if(nomCommune!=null) {
			try {
				listeVille=findVilleByName(nomCommune, daoFactory);
			} catch (DaoException e) {
				logger.info(ERROR);
			}
		}
		else {
			try {
				listeVille=findAllVille(daoFactory);
			} catch (DaoException e) {
				logger.info(ERROR);
			}
		}
		return listeVille;
	}
	
	@Override
	public List<Ville> findAllVillesbyCodeCommuneINSEE(String codeCommuneINSEE) {
		DaoFactory daoFactory = DaoFactory.getInstance();
		
		List<Ville> listeVille = new ArrayList<>();
		if(codeCommuneINSEE!=null) {
			try {
				listeVille=findVilleByCodeCommuneINSEE(codeCommuneINSEE, daoFactory);
			} catch (DaoException e) {
				logger.info(ERROR);
			}
		}
		else {
			try {
				listeVille=findAllVille(daoFactory);
			} catch (DaoException e) {
				logger.info(ERROR);
			}
		}
		return listeVille;
	}

	private List<Ville> findVilleByName(String name, DaoFactory daoFactory) throws DaoException {
		Connection connexion = null;
		PreparedStatement pstmt = null;
        ResultSet resultat = null;
        List<Ville> villes = new ArrayList<>();
        String query = "SELECT * FROM ville_france WHERE Nom_Commune=? ;";
        try {
            connexion = daoFactory.getConnection();
            pstmt = connexion.prepareStatement(query);
            pstmt.setString(1, name);
            resultat = pstmt.executeQuery();
            while (resultat.next()) {
            	String codeCommune = resultat.getString(CODE_COMMUNE_INSEE);
            	String nomCommune = resultat.getString(NOM_COMMUNE);
            	String codePostal = resultat.getString(CODE_POSTAL);
            	float latitude = resultat.getFloat(LATITUDE);
            	float longitude = resultat.getFloat(LONGITUDE);
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
            	logger.info("Impossible de communiquer avec la base de donnees");
            }
        }
	}
	
	private List<Ville> findVilleByCodePostal(String codePostal, DaoFactory daoFactory) throws DaoException {
		Connection connexion = null;
		PreparedStatement pstmt = null;
        ResultSet resultat = null;
        List<Ville> villes = new ArrayList<>();
        String query = "SELECT * FROM ville_france WHERE Code_Postal=? ;";
        try {
            connexion = daoFactory.getConnection();
            pstmt = connexion.prepareStatement(query);
            pstmt.setString(1, codePostal);
            resultat = pstmt.executeQuery();
            while (resultat.next()) {
            	String codeCommune = resultat.getString(CODE_COMMUNE_INSEE);
            	String nomCommune = resultat.getString(NOM_COMMUNE);
            	float latitude = resultat.getFloat(LATITUDE);
            	float longitude = resultat.getFloat(LONGITUDE);
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
            	logger.info("Impossible de communiquer avec la base de donnees");
            }
        }
	}
	
	
	
	private List<Ville> findVilleByCodeCommuneINSEE(String codeCommuneINSEE, DaoFactory daoFactory) throws DaoException {
		Connection connexion = null;
		PreparedStatement pstmt = null;
        ResultSet resultat = null;
        List<Ville> villes = new ArrayList<>();
        String query = "SELECT * FROM ville_france WHERE Code_commune_INSEE=? ;";
        try {
            connexion = daoFactory.getConnection();
            pstmt = connexion.prepareStatement(query);
            pstmt.setString(1, codeCommuneINSEE);
            resultat = pstmt.executeQuery();
            while (resultat.next()) {
            	String codeCommune = resultat.getString(CODE_COMMUNE_INSEE);
            	String nomCommune = resultat.getString(NOM_COMMUNE);
            	String codePostal = resultat.getString(CODE_POSTAL);
            	float latitude = resultat.getFloat(LATITUDE);
            	float longitude = resultat.getFloat(LONGITUDE);
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
            	logger.info("Impossible de communiquer avec la base de donnees");
            }
        }
	}
	private List<Ville> findAllVille(DaoFactory daoFactory) throws DaoException {
		Connection connexion = null;
		Statement statement = null;
        ResultSet resultat = null;
        List<Ville> villes = new ArrayList<Ville>();
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM ville_france;");
            while (resultat.next()) {
            	String codeCommune = resultat.getString(CODE_COMMUNE_INSEE);
            	String nomCommune = resultat.getString(NOM_COMMUNE);
            	String codePostal = resultat.getString(CODE_POSTAL);
            	float latitude = resultat.getFloat(LATITUDE);
            	float longitude = resultat.getFloat(LONGITUDE);
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
            	logger.info("Impossible de communiquer avec la base de donnees");
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
            pstmt.executeUpdate();
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
            	logger.info("Impossible de communiquer avec la base de donnees");
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
            pstmt.executeUpdate();
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
            	logger.info("Impossible de communiquer avec la base de donnees");
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
            	logger.info("Impossible de communiquer avec la base de donnees");
            }
        }
	}
	
	

	
}
