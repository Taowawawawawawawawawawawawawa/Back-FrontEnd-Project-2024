package th.mfu.dto;
import th.mfu.domain.CustomerTemp;

public class TicketDTO {

    private Long id;

    private String movie;

    private CustomerTemp customer;

    private String theatre;

    private String round;

    private String seat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public CustomerTemp getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerTemp customer) {
        this.customer = customer;
    }

    public String getTheatre() {
        return theatre;
    }

    public void setTheatre(String theatre) {
        this.theatre = theatre;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
