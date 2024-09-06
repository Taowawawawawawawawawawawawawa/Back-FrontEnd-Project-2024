package th.mfu.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "seat_row")
    private String seatRow; // Renamed field

    @Column(nullable = false, name = "seat_column") // Renamed the column field
    private int seatColumn;  // Updated field name
    private boolean vip;

    private boolean available; // Fixed spelling

    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;    

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRow() {
        return seatRow;
    }

    public void setSeatRow(String row) {
        this.seatRow = row;
    }

    public int getColumn() {
        return seatColumn;
    }

    public void setSeatColumn(int column) {
        this.seatColumn = column;
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
}
