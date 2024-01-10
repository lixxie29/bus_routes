package domain;

public class Route {
    private String sourceCity;
    private String destCity;
    private Integer departureTime;
    private Integer arrivalTime;
    private Integer nrSeats;
    private Integer ticketPrice;

    public Route(String sourceCity, String destCity, Integer departureTime, Integer arrivalTime, Integer nrSeats, Integer ticketPrice) {
        this.sourceCity = sourceCity;
        this.destCity = destCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.nrSeats = nrSeats;
        this.ticketPrice = ticketPrice;
    }

    public String getSourceCity() {
        return sourceCity;
    }

    public void setSourceCity(String sourceCity) {
        this.sourceCity = sourceCity;
    }

    public String getDestCity() {
        return destCity;
    }

    public void setDestCity(String destCity) {
        this.destCity = destCity;
    }

    public Integer getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Integer departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Integer arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getNrSeats() {
        return nrSeats;
    }

    public void setNrSeats(Integer nrSeats) {
        this.nrSeats = nrSeats;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "Route{" +
                "sourceCity='" + sourceCity  +
                "', destCity='" + destCity  +
                "', departureTime='" + departureTime +
                "', arrivalTime='" + arrivalTime +
                "', nrSeats='" + nrSeats +
                "', ticketPrice='" + ticketPrice +
                "'}";
    }
}
