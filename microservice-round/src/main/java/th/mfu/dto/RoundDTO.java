package th.mfu.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class RoundDTO {

    private Long id;
    private TheatreDTO theatre;  // Fetch theatre details via TheatreClient
    private Long theatreID;       // Store the ID for persistence
    private Long movieID;
    private LocalDate date;
    private LocalTime time;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public TheatreDTO getTheatre() {
        return theatre;
    }
    public void setTheatre(TheatreDTO theatre) {
        this.theatre = theatre;
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
