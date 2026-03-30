package ms.cinemas.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Cinema implements Serializable {

    @ElementCollection
    @CollectionTable(name = "MOVIE_CINEMA", joinColumns = @JoinColumn(name = "cinema_id"))
    @Column(name = "movie_id")
    public Set<Integer> movies;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String logo;

    @Transient
    private List<String> movieNames = new ArrayList<>();

    @Override
    public String toString() {
        return "Cinema{" +
                "name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}