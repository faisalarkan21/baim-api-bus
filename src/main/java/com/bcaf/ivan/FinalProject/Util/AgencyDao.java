package com.bcaf.ivan.FinalProject.Util;


import com.bcaf.ivan.FinalProject.Entity.Agency;
import com.bcaf.ivan.FinalProject.Entity.Bus;
import com.bcaf.ivan.FinalProject.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgencyDao extends JpaRepository<Agency, String> {
    @Query(nativeQuery = true, value = "SELECT ta.* FROM tb_agency ta WHERE ta.user_id =:id ")
    Agency findAgencyByUserId(@Param("id") String id);
}