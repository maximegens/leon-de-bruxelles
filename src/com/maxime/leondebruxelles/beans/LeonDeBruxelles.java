package com.maxime.leondebruxelles.beans;

public class LeonDeBruxelles {

	private int id;
	private String nom;
	private String adresse;
	private String complementAdresse;
	private String codePostal;
	private String ville;
	private String latitude;
	private String longitude;
	private String terasse;
	private String parking;
	private String espaceEnfant;
	private String urlPhoto;
	private String infosSUpplementaires;
	private String telephone;
	
	public LeonDeBruxelles(int id, String nom, String adresse,
			String complementAdresse, String codePostal, String ville,
			String latitude, String longitude, String terasse, String parking,
			String espaceEnfant, String urlPhoto, String infosSUpplementaires,
			String telephone) {
		super();
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.complementAdresse = complementAdresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.latitude = latitude;
		this.longitude = longitude;
		this.terasse = terasse;
		this.parking = parking;
		this.espaceEnfant = espaceEnfant;
		this.urlPhoto = urlPhoto;
		this.infosSUpplementaires = infosSUpplementaires;
		this.telephone = telephone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getComplementAdresse() {
		return complementAdresse;
	}

	public void setComplementAdresse(String complementAdresse) {
		this.complementAdresse = complementAdresse;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getTerasse() {
		return terasse;
	}

	public void setTerasse(String terasse) {
		this.terasse = terasse;
	}

	public String getParking() {
		return parking;
	}

	public void setParking(String parking) {
		this.parking = parking;
	}

	public String getEspaceEnfant() {
		return espaceEnfant;
	}

	public void setEspaceEnfant(String espaceEnfant) {
		this.espaceEnfant = espaceEnfant;
	}

	public String getUrlPhoto() {
		return urlPhoto;
	}

	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}

	public String getInfosSUpplementaires() {
		return infosSUpplementaires;
	}

	public void setInfosSUpplementaires(String infosSUpplementaires) {
		this.infosSUpplementaires = infosSUpplementaires;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
}
