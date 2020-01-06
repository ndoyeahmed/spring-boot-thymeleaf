package com.penda.transfertargent.transfert.dao;

import com.penda.transfertargent.transfert.model.Versement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVersement extends JpaRepository<Versement, Integer> {
}
