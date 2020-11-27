package com.pci.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * 商品区分入力フォームビーン
 * @author endo_k01
 *
 */
public class CategoryForm {

	@NotNull
	@Size(min=4, max=4)
	private String categoryCode;

	@Size(min=1, max=10)
	private String name;

	public CategoryForm() {
	}

	public CategoryForm(String categoryCode, String name) {
		super();
		this.categoryCode = categoryCode;
		this.name = name;
	}

	public String getCategoryCode() {
		return this.categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}