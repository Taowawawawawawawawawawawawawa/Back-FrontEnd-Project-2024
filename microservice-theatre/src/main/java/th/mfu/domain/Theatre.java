package th.mfu.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "theatre_seq")
    @SequenceGenerator(name = "theatre_seq", sequenceName = "hibernate_sequence", allocationSize = 1)
    private Long theatreId;

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();

    // Getters and setters
    public Long getId() {
        return theatreId;
    }

    public void setId(Long id) {
        this.theatreId = id;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
