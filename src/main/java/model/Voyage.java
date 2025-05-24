package model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Voyage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Trajet trajet;

    private LocalDate dateVoyage;
    private LocalTime heureDepart;
    private LocalTime heureArrivee;
    private double prixVoyage;
    private int nbPlacesDispo;
	public Trajet getTrajet() {
		return trajet;
	}
	public void setTrajet(Trajet trajet) {
		this.trajet = trajet;
	}
	public LocalDate getDateVoyage() {
		return dateVoyage;
	}
	public void setDateVoyage(LocalDate dateVoyage) {
		this.dateVoyage = dateVoyage;
	}
	public LocalTime getHeureDepart() {
		return heureDepart;
	}
	public void setHeureDepart(LocalTime heureDepart) {
		this.heureDepart = heureDepart;
	}
	public LocalTime getHeureArrivee() {
		return heureArrivee;
	}
	public void setHeureArrivee(LocalTime heureArrivee) {
		this.heureArrivee = heureArrivee;
	}
	public double getPrixVoyage() {
		return prixVoyage;
	}
	public void setPrixVoyage(double prixVoyage) {
		this.prixVoyage = prixVoyage;
	}
	public int getNbPlacesDispo() {
		return nbPlacesDispo;
	}
	public void setNbPlacesDispo(int nbPlacesDispo) {
		this.nbPlacesDispo = nbPlacesDispo;
	}
	public int getId() {
		return id;
	}
	public Voyage(int id, Trajet trajet, LocalDate dateVoyage, LocalTime heureDepart, LocalTime heureArrivee,
			double prixVoyage, int nbPlacesDispo) {
		super();
		this.id = id;
		this.trajet = trajet;
		this.dateVoyage = dateVoyage;
		this.heureDepart = heureDepart;
		this.heureArrivee = heureArrivee;
		this.prixVoyage = prixVoyage;
		this.nbPlacesDispo = nbPlacesDispo;
	}
	public Voyage() { 
	}
	
	
    
    
}