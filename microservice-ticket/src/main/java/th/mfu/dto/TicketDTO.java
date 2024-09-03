package th.mfu.dto;

import th.mfu.domain.Customer;
import th.mfu.domain.Movie;
import th.mfu.domain.Seat;
import th.mfu.domain.Theatre;

public class TicketDTO {
    private Long id;
    private Movie movie;
    private Customer customer;
    private Theatre theatre;
    private Seat seat;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

}
