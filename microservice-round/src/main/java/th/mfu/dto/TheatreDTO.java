package th.mfu.dto;

import java.util.List;

public class TheatreDTO {

    private Long theatreId;
    private List<SeatDTO> seats;  // Contains the seat details for the theatre

    // Getters and setters
    public Long getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Long theatreId) {
        this.theatreId = theatreId;
    }

    public List<SeatDTO> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatDTO> seats) {
        this.seats = seats;
    }
}
