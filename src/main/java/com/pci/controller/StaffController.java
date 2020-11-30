package com.pci.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pci.entity.MtUser;
import com.pci.entity.TrSalesOutline;
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
	@RequestMapping(value = "/saleCre",method = RequestMethod.POST)
	public ModelAndView SaleCre(ModelAndView mav) {
		TrSalesOutline sale = new TrSalesOutline();
		mav.addObject("formModel", sale);
		mav.addObject("itemList", itemRepository.findAll());
		mav.addObject("customerList", customerRepository.findAll());
		mav.setViewName("/300staff/321salesCre");
		return mav;
	}
	
//	@RequestMapping(value = "/saleCreConf",method=RequestMethod.GET)
//	public ModelAndView SaleCreConf(
//			@ModelAttribute("formModel")TrSalesOutline sale,
//			BindingResult result,
//			ModelAndView mav) {
//		user=userRepository.findByUserCode(userDetail.getUsername()).get();
//		mav.addObject("userName", user.getUserName());
//		if(!result.hasErrors()) {
//			String[] itemCodeArray = sale.getItemCodeArray();
//			String[] quantityArray = sale.getQuantityArray();
//			if(itemCodeArray!=null) {
//				for(int i=0;i<itemCodeArray.length;i++) {
//					int qty=Integer.parseInt(quantityArray[i]);
//					if(qty!=0) {
//						MtItem item = itemRepository.findByItemCode(itemCodeArray[i]).get();
//						salesDetails.add(new TrSalesDetail(qty,item.getPrice(),item));
//					}
//				}
//			}
//			mav.addObject("salesDetails", salesDetails);
//			mav.setViewName("/300staff/322salesCreConf");
//		}else {
//			mav.addObject("msg", "エラーが発生しました");
//			mav.addObject("itemList", itemRepository.findAll());
//			mav.addObject("customerList", customerRepository.findAll());
//			mav.setViewName("/300staff/321salesCre");
//		}
//		return mav;
//	}

}
