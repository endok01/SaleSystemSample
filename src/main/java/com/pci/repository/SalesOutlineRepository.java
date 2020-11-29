package com.pci.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pci.entity.TrSalesOutline;

@Repository
public interface SalesOutlineRepository extends JpaRepository<TrSalesOutline, Long> {
	
}
