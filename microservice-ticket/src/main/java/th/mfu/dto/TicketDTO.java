package th.mfu.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

public class TicketDTO {

    private Long id;

    private CustomerDTO customer;

    private RoundDTO round;

    private SeatDTO seat;

    private MovieDTO movie;

    private TheatreDTO theatre;
    
    private Long movieID;

    private Long customerID;

    private Long theatreID;

    private Long seatID;

    private Long roundID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public RoundDTO getRound() {
        return round;
    }

    public void setRound(RoundDTO round) {
        this.round = round;
    }

    public SeatDTO getSeat() {
        return seat;
    }

    public void setSeat(SeatDTO seat) {
        this.seat = seat;
    }

    public MovieDTO getMovie() {
        return movie;
    }

    public void setMovie(MovieDTO movie) {
        this.movie = movie;
    }

    public TheatreDTO getTheatre() {
        return theatre;
    }

    public void setTheatre(TheatreDTO theatre) {
        this.theatre = theatre;
    }

    public Long getMovieID() {
        return movieID;
    }

    public void setMovieID(Long movieID) {
        this.movieID = movieID;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public Long getTheatreID() {
        return theatreID;
    }

    public void setTheatreID(Long theatreID) {
        this.theatreID = theatreID;
    }

    public Long getSeatID() {
        return seatID;
    }

    public void setSeatID(Long seatID) {
        this.seatID = seatID;
    }

    public Long getRoundID() {
        return roundID;
    }

    public void setRoundID(Long roundID) {
        this.roundID = roundID;
    }

   

    

    

    

    
}
