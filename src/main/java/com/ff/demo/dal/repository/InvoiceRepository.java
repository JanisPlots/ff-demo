package com.ff.demo.dal.repository;

import com.ff.demo.dal.model.Invoice;
import com.ff.demo.dal.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
