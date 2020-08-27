package com.bcaf.ivan.FinalProject.Util;


import com.bcaf.ivan.FinalProject.Entity.Bus;
import com.bcaf.ivan.FinalProject.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusDao extends JpaRepository<Bus,String> {
    @Query(nativeQuery = true,value="SELECT tb.* FROM tb_bus tb WHERE tb.agency_id =:id ")
    List<Bus> findAllBusByAgencyId(@Param("id") String id);
}