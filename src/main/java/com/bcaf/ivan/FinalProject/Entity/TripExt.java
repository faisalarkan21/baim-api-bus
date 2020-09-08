package com.bcaf.ivan.FinalProject.Entity;

public class TripExt extends Trip{

    public TripExt(Trip trip) {
        this.setId(trip.getId());
        this.setFare(trip.getFare());
        this.setJourneyTime(trip.getJourneyTime());
        this.setSourceStopId(trip.getSourceStopId());
        this.setDestStopId(trip.getDestStopId());
        this.setBusId(trip.getBusId());
        this.setAgencyId(trip.getAgencyId());
        this.setCreatedDate(trip.getCreatedDate());
        this.setUpdatedDate(trip.getUpdatedDate());
    }

    public TripExt(){

    }

    private Bus bus;
    private Stop stopDestination;
    private Stop stop;

    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
    }

    public Stop getStopDestination() {
        return stopDestination;
    }

    public void setStopDestination(Stop stopDestination) {
        this.stopDestination = stopDestination;
    }


    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }
}
