package th.mfu.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long seatId;

    @Column(nullable = false, name = "seat_row")
    private String seatRow; // Renamed field

    @Column(nullable = false, name = "seat_column") // Renamed the column field
    private int seatColumn;  // Updated field name
    private boolean vip;

    private boolean available; // Fixed spelling

    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;

    private Long theatreId;
    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long id) {
        this.seatId = id;
    }

    public String getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(String seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatColumn() {
        return seatColumn;
    }

    public void setSeatColumn(int seatColumn) {
        this.seatColumn = seatColumn;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public Long getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Long theatreId) {
        this.theatreId = theatreId;
    }    

    // Getters and setters
    
}
