package com.pci.form;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.pci.entity.MtCustomer;
import com.pci.entity.MtUser;

/**
 * 
 * 売上明細入力用フォームクラス
 * @author endo_k01
 *
 */
public class SalesForm {

	private long salesId;	// 売上ID
	private MtUser mtUser;	// 担当者情報
	// Input
	private MtCustomer mtCustomer;	// 顧客情報
	@NotBlank
	private String salesDateString;		// 日付
	@NotNull
	@Valid
	private List<SalesItemForm> saleItemForm;	// 売上明細入力フォーム
	
//	private Date saleDate;

	/**
	 * コンストラクタ
	 */
	public SalesForm() {
		super();
	}

	/**
	 * アクセッサメソッド
	 */
	public long getSalesId() {
		return salesId;
	}

	public void setSalesId(long salesId) {
		this.salesId = salesId;
	}

	public MtUser getMtUser() {
		return mtUser;
	}

	public void setMtUser(MtUser mtUser) {
		this.mtUser = mtUser;
	}

	public MtCustomer getMtCustomer() {
		return mtCustomer;
	}

	public void setMtCustomer(MtCustomer mtCustomer) {
		this.mtCustomer = mtCustomer;
	}

	public String getSalesDateString() {
		return salesDateString;
	}

	public void setSalesDateString(String salesDateString) {
		this.salesDateString = salesDateString;
	}

	public List<SalesItemForm> getSaleItemForm() {
		return saleItemForm;
	}

	public void setSaleItemForm(List<SalesItemForm> saleItemForm) {
		this.saleItemForm = saleItemForm;
	}
	
}
