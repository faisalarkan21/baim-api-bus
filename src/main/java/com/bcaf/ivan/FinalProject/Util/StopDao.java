package com.bcaf.ivan.FinalProject.Util;

import com.bcaf.ivan.FinalProject.Entity.Bus;
import com.bcaf.ivan.FinalProject.Entity.Stop;
import com.bcaf.ivan.FinalProject.Entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StopDao extends JpaRepository<Stop,String> {
    @Query(nativeQuery = true,value="SELECT tb.* FROM tb_stop tb ")
    List<Stop> findAllStopByAgencyId(@Param("id") String id);
}
