package sep3.classes.Model;

public class Rating {

    private int rating;
    private int idNr;
    private int hospitalId;

    public Rating() {
    }

    public Rating(int rating, int idNr, int hospitalId) {
        this.rating = rating;
        this.idNr = idNr;
        this.hospitalId = hospitalId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getIdNr() {
        return idNr;
    }

    public void setIdNr(int idNr) {
        this.idNr = idNr;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "rating=" + rating +
                ", idNr=" + idNr +
                ", hospitalId=" + hospitalId +
                '}';
    }
}
