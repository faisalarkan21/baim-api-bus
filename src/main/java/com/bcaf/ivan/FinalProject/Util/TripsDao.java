package com.bcaf.ivan.FinalProject.Util;

import com.bcaf.ivan.FinalProject.Entity.Bus;
import com.bcaf.ivan.FinalProject.Entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripsDao extends JpaRepository<Trip,String> {
    @Query(nativeQuery = true,value="SELECT tb.* FROM tb_trip tb WHERE tb.agency_id =:id ")
    List<Trip> findAllTripByAgencyId(@Param("id") String id);
}