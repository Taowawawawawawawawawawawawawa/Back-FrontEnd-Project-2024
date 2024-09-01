package th.mfu.dto;
import th.mfu.domain.CustomerTemp;
import th.mfu.domain.Movie;
import th.mfu.domain.Theatre;

public class TicketDTO {

    private Long id;

    private CustomerTempDTO customer;

    //private Theatre theatre;
    
    private MovieDTO movie;
    
    private String round;

    private SeatDTO seat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerTempDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerTempDTO customer) {
        this.customer = customer;
    }

    // public Theatre getTheatre() {
    //     return theatre;
    // }

    // public void setTheatre(Theatre theatre) {
    //     this.theatre = theatre;
    // }

    public MovieDTO getMovie() {
        return movie;
    }

    public void setMovie(MovieDTO movie) {
        this.movie = movie;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public SeatDTO getSeat() {
        return seat;
    }

    public void setSeat(SeatDTO seat) {
        this.seat = seat;
    }

   

}
