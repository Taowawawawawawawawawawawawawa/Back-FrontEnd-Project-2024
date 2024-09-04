package th.mfu.dto;

import java.util.List;

import th.mfu.domain.Seat;

public class TheatreDTO {

    private Long id;

    private List<Seat> seats;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

}
