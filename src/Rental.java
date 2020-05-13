class Rental {
    private final Movie movie;
    private final int daysRented;

    public Rental(Movie movie, int rentedDays) {
        this.movie = movie;
        daysRented = rentedDays;
    }

    public int getRentedDays() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }
}
