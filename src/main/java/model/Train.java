package model;
  
import javax.persistence.*;

@Entity
public class Train {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
    private String name;
    private String type;
    
    @Column(nullable = true)
    private String departureTime;


    public Train(int id, String name, String type, String departureTime) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.departureTime = departureTime;
    }

    public Train(String name, String type, String departureTime) {
        this.name = name;
        this.type = type;
        this.departureTime = departureTime;
    }
 

    public Train() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", departureTime='" + departureTime + '\'' +
                '}';
    }
	
}
