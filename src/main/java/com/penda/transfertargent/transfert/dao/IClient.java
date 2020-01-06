package com.penda.transfertargent.transfert.dao;

import com.penda.transfertargent.transfert.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClient extends JpaRepository<Client, Integer> {
}
