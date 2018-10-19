package com.ff.demo.dal.repository;

import com.ff.demo.dal.model.LoanVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanVersionRepository extends JpaRepository<LoanVersion, Long> {
}
