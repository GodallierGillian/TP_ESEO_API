package com.blo;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleBLO {
	ArrayList<Ville> getInfoVilles(String codePostal);
}
