package com.penda.transfertargent.transfert.dao;

import com.penda.transfertargent.transfert.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRole extends JpaRepository<Role, Integer> {
    public Role findRoleByLibRole(String libelle);
}
