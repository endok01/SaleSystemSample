package com.pci.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pci.entity.TrSalesDetail;

public interface SalesDetailRepository extends JpaRepository<TrSalesDetail, Long> {
	@Query("select t FROM TrSalesDetail t WHERE t.trSalesOutline.salesId= :salesId")
	public List<TrSalesDetail> findBySalesId(@Param("salesId") long salesId);
	
	@Query("select t from TrSalesDetail t where t.trSalesOutline.salesId= :salesId and t.mtItem.itemCode = :itemCode")
	public Optional<TrSalesDetail> findByItemCode(@Param("salesId") long salesId,@Param("itemCode")String itemCode);
}
