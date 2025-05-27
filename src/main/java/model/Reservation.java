package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReservation;

    @OneToOne
    private Voyage voyage;

    @OneToOne
    private Utilisateur utilisateur;

    @Column(name = "nb_personnes")
    private int nbPersonnes;
    
    @Column (name="prixTotal")
    private int prixTotal ;

    // ======= Constructeurs =======

    public Reservation() {}

    public Reservation(Date dateReservation, Voyage voyage, Utilisateur utilisateur, int nbPersonnes , int pt) {
        this.dateReservation = dateReservation;
        this.voyage = voyage;
        this.utilisateur = utilisateur;
        this.nbPersonnes = nbPersonnes;
        this.prixTotal=pt;
    }

    // ======= Getters & Setters =======

    public int getId() {
        return id;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Voyage getVoyage() {
        return voyage;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
    }

  

    public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public int getNbPersonnes() {
        return nbPersonnes;
    }

    public void setNbPersonnes(int nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }

    public void setId(int id) {
        this.id = id;
    }

	public int getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(int prixTotal) {
		this.prixTotal = prixTotal;
	}
    
	
    
}
