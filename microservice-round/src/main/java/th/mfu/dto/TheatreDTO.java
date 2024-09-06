package th.mfu.dto;

import java.util.List;

public class TheatreDTO {

    private Long theatreId;
    private List<SeatDTO> seats; // Use SeatDTO instead of Seat

    // Getters and setters
    public Long getId() {
        return theatreId;
    }

    public void setId(Long id) {
        this.theatreId = id;
    }

    public List<SeatDTO> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatDTO> seats) {
        this.seats = seats;
    }
}
