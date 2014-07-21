package com.maxime.leondebruxelles.beans;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint("ParcelCreator")
public class LeonDeBruxelles implements Parcelable{

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
	private String accesHandicape;
	private String espaceEnfant;
	private String photo;
	private String infosSupplementaires;
	private String Telephone;
	private float distanceMeterFromUser;
	
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

	public String getAccesHandicape() {
		return accesHandicape;
	}

	public void setAccesHandicape(String accesHandicape) {
		this.accesHandicape = accesHandicape;
	}

	public String getEspaceEnfant() {
		return espaceEnfant;
	}

	public void setEspaceEnfant(String espaceEnfant) {
		this.espaceEnfant = espaceEnfant;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getInfosSupplementaires() {
		return infosSupplementaires;
	}

	public void setInfosSupplementaires(String infosSupplementaires) {
		this.infosSupplementaires = infosSupplementaires;
	}

	public String getTelephone() {
		return Telephone;
	}

	public void setTelephone(String telephone) {
		Telephone = telephone;
	}



	public float getDistanceMeterFromUser() {
		return distanceMeterFromUser;
	}

	public void setDistanceMeterFromUser(float distanceMeterFromUser) {
		this.distanceMeterFromUser = distanceMeterFromUser;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "LeonDeBruxelles [id=" + id + ", nom=" + nom + ", adresse="
				+ adresse + ", complementAdresse=" + complementAdresse
				+ ", codePostal=" + codePostal + ", ville=" + ville
				+ ", latitude=" + latitude + ", longitude=" + longitude
				+ ", terasse=" + terasse + ", parking=" + parking
				+ ", accesHandicape=" + accesHandicape + ", espaceEnfant="
				+ espaceEnfant + ", photo=" + photo + ", infosSupplementaires="
				+ infosSupplementaires + ", Telephone=" + Telephone
				+ ", distanceMeterFromUser=" + distanceMeterFromUser + "]";
	}
	
	
}
