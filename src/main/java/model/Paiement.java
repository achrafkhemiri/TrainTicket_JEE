package model;



import javax.persistence.*; 
import java.util.Date;

@Entity
@Table(name = "paiement")
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float montant;
    private Date date;

    @OneToOne
    @JoinColumn(name = "billet_id")
    private Billet billet;
    private String statut;

    public Paiement(float montant, Date date, Billet billet, String statut) {
        this.montant = montant;
        this.date = date;
        this.billet = billet;
        this.statut = statut;
    }

    public Paiement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Billet getBillet() {
        return billet;
    }

    public void setBillet(Billet billet) {
        this.billet = billet;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}