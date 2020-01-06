package com.penda.transfertargent.transfert.dao;

import com.penda.transfertargent.transfert.model.Commission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommission extends JpaRepository<Commission, Integer> {
    @Query("SELECT MAX (c.id) FROM Commission c")
    public Integer getMaxId();
}
