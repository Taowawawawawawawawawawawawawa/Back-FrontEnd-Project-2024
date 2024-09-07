package th.mfu.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.*;

@Entity
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long theatreID;  // Keep this field to reference theatre by ID
    private Long movieID;
    private LocalDate date;
    private LocalTime time;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getTheatreID() {
        return theatreID;
    }
    public void setTheatreID(Long theatreID) {
        this.theatreID = theatreID;
    }
    public Long getMovieID() {
        return movieID;
    }
    public void setMovieID(Long movieID) {
        this.movieID = movieID;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public LocalTime getTime() {
        return time;
    }
    public void setTime(LocalTime time) {
        this.time = time;
    }
    
}
