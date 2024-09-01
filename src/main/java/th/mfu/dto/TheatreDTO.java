package th.mfu.dto;

public class TheatreDTO {
    
    private Long id;

    private String movie;

    private String round;

    private SeatDTO seat;

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

    public SeatDTO getSeat() {
        return seat;
    }

    public void setSeat(SeatDTO seat) {
        this.seat = seat;
    }
    
    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }
}
