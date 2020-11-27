package com.pci.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pci.entity.TrSalesOutline;

@Repository
public interface SalesOutlineRepository extends JpaRepository<TrSalesOutline, Long> {
	
	@Query("select t FROM TrSalesOutline t WHERE t.mtUser.userCode= :userCode")
	public List<TrSalesOutline> findByMtUser(@Param("userCode") String userCode);
	
	public Optional<TrSalesOutline> findBySalesId(Long salesId);

}
