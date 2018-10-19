package com.ff.demo.dal.repository;

import com.ff.demo.dal.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByIp(String ip);
}
