package th.mfu.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class RoundDTO {

    private Long id;
    private TheatreDTO theatre;
    private MovieDTO movie;
    private LocalDate date;
    private LocalTime time;
    private Long theatreID;
    private Long movieID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public TheatreDTO getTheatre() {
        return theatre;
    }

    public void setTheatre(TheatreDTO theatre) {
        this.theatre = theatre;
    }

    public MovieDTO getMovie() {
        return movie;
    }

    public void setMovie(MovieDTO movie) {
        this.movie = movie;
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
}
