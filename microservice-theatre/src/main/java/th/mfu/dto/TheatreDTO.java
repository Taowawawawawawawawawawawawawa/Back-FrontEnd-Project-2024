package th.mfu.dto;

import java.util.List;

import th.mfu.domain.Movie;
import th.mfu.domain.Seat;

public class TheatreDTO {

    private Long id;

    private Movie movie;

    private List<Seat> seats;

    private String round;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    
   
}
