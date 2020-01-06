package com.penda.transfertargent.transfert.dao;

import com.penda.transfertargent.transfert.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransaction extends JpaRepository<Transaction, Integer> {
}
