package model;



import javax.persistence.*; 
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trajet")
public class Trajet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "gare_depart_id")
    private Gare depart;

    @ManyToOne
    @JoinColumn(name = "gare_arrivee_id")
    private Gare arrivee;

    @ManyToMany
    @JoinTable(
            name = "trajet_gare_passage",
            joinColumns = @JoinColumn(name = "trajet_id"),
            inverseJoinColumns = @JoinColumn(name = "gare_id")
    )
    private List<Gare> garesDePassage = new ArrayList<>();

    public Trajet() {
    }

    public Trajet(Gare depart, Gare arrivee, List<Gare> garesDePassage) {
        this.depart = depart;
        this.arrivee = arrivee;
        this.garesDePassage = garesDePassage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Gare getDepart() {
        return depart;
    }

    public void setDepart(Gare depart) {
        this.depart = depart;
    }

    public Gare getArrivee() {
        return arrivee;
    }

    public void setArrivee(Gare arrivee) {
        this.arrivee = arrivee;
    }

    public List<Gare> getGaresDePassage() {
        return garesDePassage;
    }

    public void setGaresDePassage(List<Gare> garesDePassage) {
        this.garesDePassage = garesDePassage;
    }

	@Override
	public String toString() {
		return "Trajet [id=" + id + ", depart=" + depart + ", arrivee=" + arrivee + ", garesDePassage=" + garesDePassage
				+ "]";
	}

    // Getters and Setters
    
    
    
}