package model;

import javax.persistence.*; 
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class Client extends Utilisateur {
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Billet> historique = new ArrayList<>();

    // Getters and Setters
}