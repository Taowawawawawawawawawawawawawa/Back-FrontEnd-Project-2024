package th.mfu.domain;

import java.util.List;

public class MovieDTO {
    private Long movieID;
    private String name;
    private String description;
    private String length;
    private String genre;
    private List<Long> theatreIDs;

    // Getters and Setters
    public Long getMovieID() {
        return movieID;
    }

    public void setMovieID(Long movieID) {
        this.movieID = movieID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Long> getTheatreIDs() {
        return theatreIDs;
    }

    public void setTheatreIDs(List<Long> theatreIDs) {
        this.theatreIDs = theatreIDs;
    }
}
