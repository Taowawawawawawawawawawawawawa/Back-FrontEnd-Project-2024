package th.mfu.dto;

public class TicketDTO {

    private Long id;

    private CustomerDTO customer;

    private RoundDTO round;

    private SeatDTO seat;
   
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
    
}
