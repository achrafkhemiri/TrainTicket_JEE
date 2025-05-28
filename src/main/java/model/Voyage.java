package model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
    
    @OneToOne
    private Train train ;
    
    
    
    
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
	public Voyage(LocalDate dateVoyage, LocalTime heureDepart, LocalTime heureArrivee, int nbPlacesDispo, double prixVoyage
			, Trajet trajet , Train train) {
	    this.dateVoyage = dateVoyage;
	    this.heureDepart = heureDepart;
	    this.heureArrivee = heureArrivee;
	    this.nbPlacesDispo = nbPlacesDispo;
	    this.prixVoyage = prixVoyage;
	    this.trajet = trajet;
	    this.train=train;
	}
	
	
	
	
	public Train getTrain() {
		return train;
	}
	public void setTrain(Train train) {
		this.train = train;
	}
	@Override
	public String toString() {
		return "Voyage [id=" + id + ", trajet=" + trajet + ", dateVoyage=" + dateVoyage + ", heureDepart=" + heureDepart
				+ ", heureArrivee=" + heureArrivee + ", prixVoyage=" + prixVoyage + ", nbPlacesDispo=" + nbPlacesDispo
				+ "]";
	}

	

    
    
}