package com.bcaf.ivan.FinalProject.Controller;

import com.bcaf.ivan.FinalProject.Entity.Bus;
import com.bcaf.ivan.FinalProject.Entity.Stop;
import com.bcaf.ivan.FinalProject.Entity.Trip;
import com.bcaf.ivan.FinalProject.Entity.TripExt;
import com.bcaf.ivan.FinalProject.Util.BusDao;
import com.bcaf.ivan.FinalProject.Util.StopDao;
import com.bcaf.ivan.FinalProject.Util.TripsDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TripsApiController {

    @Autowired
    private TripsDao tripsDao;

    @Autowired
    private BusDao busDao;

    @Autowired
    private StopDao stopDao;

    @PostMapping("/getAllTrips")
    public String getAllBus(HttpServletRequest request) throws JsonProcessingException {
        HttpSession session = request.getSession(true);
        String agencyId = (String) session.getAttribute("agencyId");

        List<Trip> listTrip = tripsDao.findAllTripByAgencyId(agencyId);
        List<TripExt> listTripExts = new LinkedList<>();

        for (Trip t : listTrip) {
            TripExt tripExt = new TripExt(t);
            tripExt.setBus(busDao.findById(t.getBusId()).get());
            tripExt.setStop(stopDao.findById(t.getSourceStopId()).get());
            tripExt.setStopDestination(stopDao.findById(t.getDestStopId()).get());
            listTripExts.add(tripExt);
        }

        ObjectMapper Obj = new ObjectMapper();
        String rs = Obj.writeValueAsString(listTripExts);
        return rs;

    }

    @GetMapping("/getAllTrips-angular")
    public String getAllTrips(@RequestParam(name = "id") String agencyId) throws JsonProcessingException {

        List<Trip> listTrip = tripsDao.findAllTripByAgencyId(agencyId);
        List<TripExt> listTripExts = new LinkedList<>();

        for (Trip t : listTrip) {
            TripExt tripExt = new TripExt(t);
            tripExt.setBus(busDao.findById(t.getBusId()).get());
            tripExt.setStop(stopDao.findById(t.getSourceStopId()).get());
            tripExt.setStopDestination(stopDao.findById(t.getDestStopId()).get());
            listTripExts.add(tripExt);
        }

        ObjectMapper Obj = new ObjectMapper();
        String rs = Obj.writeValueAsString(listTripExts);
        return rs;
    }


    @PostMapping("/addTrips")
    public String addTrip(@RequestBody List<TripExt> listTrip, HttpServletRequest request) throws JsonProcessingException {
        HttpSession session = request.getSession(true);
        String agencyId = (String) session.getAttribute("agencyId");

        for (TripExt addTrip : listTrip) {
            Trip newTrip = new Trip();
            newTrip.setAgencyId(agencyId);
            newTrip.setBusId(addTrip.getBus().getId());
            newTrip.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            newTrip.setDestStopId(addTrip.getStop().getId());
            newTrip.setSourceStopId(addTrip.getStop().getId());
            newTrip.setFare(addTrip.getFare());
            newTrip.setJourneyTime(addTrip.getJourneyTime());
            tripsDao.save(newTrip);
        }

        ObjectMapper Obj = new ObjectMapper();
        String rs = Obj.writeValueAsString(listTrip);
        return rs;
    }

    @PostMapping("/addTrips-angular")
    public String addTrip(@RequestBody TripExt addTrip) throws JsonProcessingException {
//        HttpSession session = request.getSession(true);
//        String agencyId = (String) session.getAttribute("agencyId");

        Trip newTrip = new Trip();
        newTrip.setAgencyId(addTrip.getAgencyId());
        newTrip.setBusId(addTrip.getBus().getId());
        newTrip.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        newTrip.setDestStopId(addTrip.getStop().getId());
        newTrip.setSourceStopId(addTrip.getStop().getId());
        newTrip.setFare(addTrip.getFare());
        newTrip.setJourneyTime(addTrip.getJourneyTime());
        tripsDao.save(newTrip);

        ObjectMapper Obj = new ObjectMapper();
        String rs = Obj.writeValueAsString(tripsDao.findAll());
        return rs;
    }


    @PostMapping("/getAllStop")
    public String getAllStop(HttpServletRequest request) throws JsonProcessingException {
        HttpSession session = request.getSession(true);
        String agencyId = (String) session.getAttribute("agencyId");
        List<Stop> listStop = stopDao.findAllStopByAgencyId(agencyId);
        if (listStop == null)
            listStop = new LinkedList<>();
        ObjectMapper Obj = new ObjectMapper();
        String rs = Obj.writeValueAsString(listStop);
        return rs;
    }

    @GetMapping("/getAllStopAngular")
    public String getAllStopAngular(@RequestParam(name = "id") String agencyId) throws JsonProcessingException {

        List<Stop> listStop = stopDao.findAllStopByAgencyId(agencyId);
        if (listStop == null)
            listStop = new LinkedList<>();
        ObjectMapper Obj = new ObjectMapper();
        String rs = Obj.writeValueAsString(listStop);
        return rs;
    }

    @PostMapping("/updateTrips")
    public String updateTrips(@RequestBody List<TripExt> listTrip, HttpServletRequest request) throws JsonProcessingException {
        HttpSession session = request.getSession(true);
        String agencyId = (String) session.getAttribute("agencyId");

        for (TripExt addTrip : listTrip) {
            Trip newTrip = tripsDao.findById(addTrip.getId()).get();
            newTrip.setBusId(addTrip.getBus().getId());
            newTrip.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            newTrip.setDestStopId(addTrip.getStop().getId());
            newTrip.setSourceStopId(addTrip.getStop().getId());
            newTrip.setFare(addTrip.getFare());
            newTrip.setJourneyTime(addTrip.getJourneyTime());
            tripsDao.save(newTrip);
        }

        ObjectMapper Obj = new ObjectMapper();
        String rs = Obj.writeValueAsString(listTrip);
        return rs;
    }

    @PostMapping("/updateTripsAngular")
    public String updateTripsAngular(@RequestBody TripExt addTrip) throws JsonProcessingException {

//        for (TripExt addTrip : listTrip) {
//            Trip newTrip= tripsDao.findById(addTrip.getId()).get();
//            newTrip.setBusId(addTrip.getBus().getId());
//            newTrip.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
//            newTrip.setDestStopId(addTrip.getStop().getId());
//            newTrip.setSourceStopId(addTrip.getStop().getId());
//            newTrip.setFare(addTrip.getFare());
//            newTrip.setJourneyTime(addTrip.getJourneyTime());
//            tripsDao.save(newTrip);
//        }

        Trip newTrip = tripsDao.findById(addTrip.getId()).get();
        newTrip.setBusId(addTrip.getBus().getId());
        newTrip.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        newTrip.setDestStopId(addTrip.getDestStopId());
        newTrip.setSourceStopId(addTrip.getSourceStopId());
        newTrip.setFare(addTrip.getFare());
        newTrip.setJourneyTime(addTrip.getJourneyTime());

        tripsDao.save(newTrip);

        ObjectMapper Obj = new ObjectMapper();
        String rs = Obj.writeValueAsString(tripsDao.findAll());
        return rs;
    }

    @PostMapping("/deleteTrips")
    public String deleteTrips(@RequestBody List<TripExt> ListTrip) throws JsonProcessingException {
        for (TripExt b : ListTrip) {
            tripsDao.deleteById(b.getId());
        }
        ObjectMapper Obj = new ObjectMapper();
        String rs = Obj.writeValueAsString(ListTrip);
        return rs;
    }

    @PostMapping("/deleteTrips-angular")
    public String deleteTrips(@RequestBody String tripId) throws JsonProcessingException {
        tripsDao.deleteById(tripId);
        ObjectMapper Obj = new ObjectMapper();
        String rs = Obj.writeValueAsString(tripsDao.findAll());
        return rs;
    }

}
