package model;

import javax.persistence.*; 
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class Client  {
    
    @OneToOne
    private Billet billet ;



}