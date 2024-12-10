package org.com.models;





public class Flight {
    public Integer id;
    public String destination;
    public String departureLocation;
    public String capacity;
    public String takeoffTime;
    public String landingTime;
    public String flightDate;
    public String status;


    public Flight(
        String destination, 
        String departureLocation, 
        String capacity, 
        String takeoffTime,
        String landingTime,
        String flightDate,
        String status
        ){
            this.id = null;
        this.destination = destination;
        this.departureLocation = departureLocation;
        this.capacity = capacity;
        this.takeoffTime = takeoffTime;
        this.landingTime = landingTime;
        this.flightDate = flightDate;
        this.status = status;
    }

    public Flight(
        Integer id,
        String destination, 
        String departureLocation, 
        String capacity, 
        String takeoffTime,
        String landingTime,
        String flightDate,
        String status
        ){
            this.id = id;
            this.destination = destination;
            this.departureLocation = departureLocation;
            this.capacity = capacity;
            this.takeoffTime = takeoffTime;
            this.landingTime = landingTime;
            this.flightDate = flightDate;
            this.status = status;
    }

    public void cleanFields() {
        if (destination != null && "".equals(destination)) {
            destination = null;
        }
        if (capacity != null && "".equals(capacity)) {
            capacity = null;
        }
        if (departureLocation != null && "".equals(departureLocation)) {
            departureLocation = null;
        }
        if (status != null && "".equals(status)) {
            status = null;
        }
    }
    

    // Getters
    public Integer getId() {
        return id;
    }
    public String getDestination() {
        return destination;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public String getCapacity() {
        return capacity;
    }

    public String getTakeoffTime() {
        return takeoffTime;
    }

    public String getLandingTime() {
        return landingTime;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public String getStatus() {
        return status;
    }   




    // Setters
    public Integer setId() {
        return id;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation =  departureLocation;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public void setTakeoffTime(String takeoffTime) {
        this.takeoffTime = takeoffTime;
    }

    public void setLandingTime(String landingTime) {
        this.landingTime = landingTime;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate =  flightDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }   

}