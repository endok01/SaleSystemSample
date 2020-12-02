package com.pci.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pci.entity.MtCustomer;
import com.pci.entity.MtItem;
import com.pci.entity.MtUser;
import com.pci.entity.TrSalesDetail;
import com.pci.entity.TrSalesOutline;
import com.pci.form.SalesForm;
import com.pci.form.SalesItemForm;
import com.pci.repository.CustomerRepository;
import com.pci.repository.ItemGenreRepository;
import com.pci.repository.ItemRepository;
import com.pci.repository.SalesDetailRepository;
import com.pci.repository.SalesOutlineRepository;
import com.pci.repository.UserRepository;
import com.pci.summary.ResultConverter;

/**
 * 
 * スタッフコントローラークラス
 * @author endo_k01
 *
 */
@Controller
@RequestMapping(value = "/Staff")	// このコントローラが処理するURL
@SessionAttributes("loginUser")		// セッション情報の利用
public class StaffController {

	@Autowired
	UserRepository userRepo;			// ユーザ情報
	@Autowired							
	SalesOutlineRepository saleRepository;	// 売上概要
	@Autowired
	SalesDetailRepository saleDetailRepository;	// 売上明細
	@Autowired
	CustomerRepository customerRepository;	// 顧客情報
	@Autowired
	ItemRepository itemRepository;	// 商品情報
	@Autowired
	ItemGenreRepository itemGenreRepository;	// 商品区分
	@Autowired
	ResultConverter resultConverter;	// 集計情報変換
	
	

	/**
	 * 
	 * 売上一覧画面表示
	 * @param loginUser
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "/SalesList",method=RequestMethod.GET)
	public ModelAndView salesList(
			@ModelAttribute("loginUser") MtUser loginUser,	// セッション情報から取得
			ModelAndView mav) {

		mav.addObject("salesList", saleRepository.findByMtUser(loginUser));
		mav.setViewName("/300staff/310salesList");

		return mav;
	}
	
	/**
	 * 
	 * 売上集計(日付)表示
	 * @param loginUser
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "/salesSummaryByDate",method=RequestMethod.POST)
	public ModelAndView salesSummaryByDate(
			@ModelAttribute("loginUser") MtUser loginUser,	// セッション情報から取得
			ModelAndView mav) {
		mav.addObject("salesList", resultConverter.salesSummaryResultConverterForDate(saleDetailRepository.findBySalesSummaryByDate(loginUser.getUserCode())));
		mav.setViewName("/300staff/316salesSummaryByDate");
		
		return mav;
	}

	/**
	 * 
	 * 売上集計(商品)表示
	 * @param loginUser
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "/salesSummaryByItem",method=RequestMethod.POST)
	public ModelAndView salesSummaryByItem(
			@ModelAttribute("loginUser") MtUser loginUser,	// セッション情報から取得
			ModelAndView mav) {
		mav.addObject("salesList", resultConverter.salesSummaryResultConverter(saleDetailRepository.findBySalesSummaryByItem(loginUser.getUserCode())));
		mav.setViewName("/300staff/312salesSummaryByItem");

		return mav;
	}
	
	/**
	 * 
	 * 売上集計(顧客)表示
	 * @param loginUser
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "/salesSummaryByCustomer",method=RequestMethod.POST)
	public ModelAndView salesSummaryByCustomer(
			@ModelAttribute("loginUser") MtUser loginUser,	// セッション情報から取得
			ModelAndView mav) {
		mav.addObject("salesList", resultConverter.salesSummaryResultConverter(saleDetailRepository.findBySalesSummaryByCustomer(loginUser.getUserCode())));
		mav.setViewName("/300staff/313salesSummaryByCustomer");

		return mav;
	}
	
	/**
	 * 
	 * 売上明細表示
	 * @param loginUser
	 * @param salesId
	 * @param mav
	 * @return
	 */
	@RequestMapping(value="/salesDetailDisp/{salesId}",method=RequestMethod.POST)
	public ModelAndView salesDetailDisp(
			@ModelAttribute("loginUser") MtUser loginUser,	// セッション情報から取得
			@PathVariable Long salesId,
			ModelAndView mav) {

		// saleIdから売上情報を取得する
		Optional<TrSalesOutline> s = saleRepository.findById(salesId);
		mav.addObject("saleOutline", s.get());

		mav.setViewName("/300staff/311salesDetailList");
		
		return mav;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 売上明細処理
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 売上登録処理
	 * 売上概要および売上明細登録画面の表示を行う
	 * @param loginUser
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "/saleCre",method = RequestMethod.POST)
	public ModelAndView SaleCre(
			@ModelAttribute("loginUser") MtUser loginUser,	// セッション情報から取得
			@ModelAttribute SalesForm salesForm,
			ModelAndView mav) {
		salesForm.setMtUser(loginUser);
		// 商品一覧の作成
		List<SalesItemForm> salesItemForm = new ArrayList<>();
		List<MtItem> itemList =  itemRepository.findAllByOrderByItemCode();
		for(MtItem i : itemList) {
			SalesItemForm itemForm = new SalesItemForm(
												null, 
												i.getItemCode(), 
												i.getItemName(), 
												i.getPrice(), 
												i.getSpec(), 
												0);
			salesItemForm.add(itemForm);
		}
		salesForm.setSalesItemForm(salesItemForm);
		mav.addObject("salesForm", salesForm);
		mav.addObject("customerList", customerRepository.findAllByOrderByCustomerCode());
		mav.setViewName("/300staff/321salesCre");
		return mav;
	}
	
	/**
	 * 
	 * 登録確認
	 * 入力内容確認用画面
	 * @param salesForm
	 * @param result
	 * @param mav
	 * @return
	 */
	@RequestMapping(value="/saleCreConf", method=RequestMethod.POST)
	public ModelAndView SaleCreConf(
			@ModelAttribute @Validated SalesForm salesForm,
			BindingResult result,
			ModelAndView mav) {
		// 売上が１件も登録されていない場合はエラーにする
		int total = 0;
		for(SalesItemForm i : salesForm.getSalesItemForm()) {
			total += i.getQuantity();
		}
		if(total == 0) {
			mav.addObject("errormessage", "売上が登録されていません");
			mav.addObject("customerList", customerRepository.findAllByOrderByCustomerCode());
			mav.setViewName("/300staff/321salesCre");
		} else {
			if(result.hasErrors()) {
				mav.addObject("customerList", customerRepository.findAllByOrderByCustomerCode());
				mav.setViewName("/300staff/321salesCre");
			}else {
				MtCustomer c = customerRepository.getOne(salesForm.getMtCustomer().getCustomerCode());
				salesForm.setMtCustomer(c);
				mav.setViewName("/300staff/322salesCreConf");
			}
		}
		return mav;
	}

	/**
	 * 売上登録実行
	 * @param salesForm
	 * @param mav
	 * @return
	 */
	@RequestMapping(value="/saleRegExe", method=RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView SaleRegExe(
			@ModelAttribute("loginUser") MtUser loginUser,	// セッション情報から取得
			@ModelAttribute SalesForm salesForm,
			ModelAndView mav){

		TrSalesOutline sale = new TrSalesOutline();	// エンティティ生成

		sale.setSaleDate(java.sql.Date.valueOf(salesForm.getSalesDateString()));	// 日付設定

		MtCustomer customer = new MtCustomer();
		customer.setCustomerCode(salesForm.getMtCustomer().getCustomerCode());
		sale.setMtCustomer(customer);

		sale.setMtUser(loginUser);	// ユーザ情報設定

		saleRepository.saveAndFlush(sale);	// 売上概要登録
		
		// 売上明細作成
		List<TrSalesDetail> salesDetail = new ArrayList<>();
		sale.setTrSalesDetails(salesDetail);
		// 個数0排除する☆
		for(SalesItemForm i : salesForm.getSalesItemForm()) {
			MtItem m = new MtItem();
			m.setItemCode(i.getItemCode());
			TrSalesDetail d = new TrSalesDetail(null, i.getQuantity(), i.getPrice(), m);
			d.setTrSalesOutline(sale);
			salesDetail.add(d);
		}
		
		saleDetailRepository.saveAll(salesDetail);	// 売上明細登録

		mav.setViewName("redirect:/Staff/SalesList?");	
		return mav;
	}

}
