package com.pci.controller;

import java.util.List;

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
import com.pci.entity.MtItemGenre;
import com.pci.form.CustomerForm;
import com.pci.repository.CustomerRepository;
import com.pci.repository.ItemGenreRepository;
import com.pci.repository.ItemRepository;
import com.pci.repository.SalesOutlineRepository;
import com.pci.repository.UserRepository;


/**
 * 
 * マネージャーロールのコントローラークラス
 * 
 * @author endo_k01
 *
 */
@Controller
@RequestMapping(value = "/Mgr")		// このコントローラが処理するURL
@SessionAttributes("loginUser")		// セッション情報
public class mgrController {
	
	@Autowired
	UserRepository userRepo;			// ユーザ情報
	@Autowired							
	SalesOutlineRepository saleRepository;	// 売上概要
	@Autowired
	CustomerRepository customerRepository;	// 顧客情報
	@Autowired
	ItemRepository itemRepository;	// 商品情報
	@Autowired
	ItemGenreRepository itemGenreRepository;	// 商品区分
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	// 売上一覧
	////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 * トップ画面用アクション(売上全件表示)
	 * @param mv
	 * @return ModelAndView
	 * 
	 */
	@RequestMapping(value = "/SalesList", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public ModelAndView top(ModelAndView mav) {

    	// 売上一覧の取得
		mav.addObject("salesList", saleRepository.findAll());
		mav.setViewName("/200manager/210salesList");
		
    	mav.setViewName("200manager/210salesList");

    	return mav;

	}
	
//	/**
//	 * 
//	 * 課員別売上表示(売上一覧表示)
//	 * @param mv
//	 * @return ModelAndView
//	 * 
//	 */
//	@RequestMapping(value = "/SaleListMember", method = RequestMethod.POST)
//	@Transactional(readOnly = true)
//	public ModelAndView saleListMember(ModelAndView mv) {
//
//    	// 売上一覧の取得
//    	List<Object[]> totalList = saleRepo.findBySaleListMember();
//
//    	// 取得したオブジェクトからFormを作成する
//    	List<SaleListForm> saleList = new ArrayList<>();
//    	for(Object[] obj : totalList) {
//    		SaleListForm s = new SaleListForm();
//
//    		Optional<MtUser> userData = userRepo.findById((String)obj[0]);
//    		MtUser u = userData.get();		// ユーザ情報
//    		s.setMtUser(u);
//
//    		s.setTotal(((Long)obj[1]));		// 売上合計
//
//    		saleList.add(s);
//    	}
//    	mv.addObject("saleList",saleList);
//    	
//    	// 課員別売上一覧の表示を行う
//    	mv.setViewName("100mgr/101SaleListMember");
//
//    	return mv;
//
//	}
//	
//	
//	/**
//	 * 
//	 * 顧客別売上表示(売上一覧表示)
//	 * @param mv
//	 * @return ModelAndView
//	 * 
//	 */
//	@RequestMapping(value = "/SaleListCustomer", method = RequestMethod.POST)
//	@Transactional(readOnly = true)
//	public ModelAndView saleListCustomer(ModelAndView mv) {
//
//    	// 売上一覧の取得
//    	List<Object[]> totalList = saleRepo.findBySaleListCustomer();
//    	// 取得したオブジェクトからFormを作成する
//    	List<SaleListForm> saleList = new ArrayList<>();
//    	for(Object[] obj : totalList) {
//    		SaleListForm s = new SaleListForm();
//
//    		Optional<MtCustomer> customerData = customerRepo.findById((String)obj[0]);
//    		MtCustomer c = customerData.get();		// ユーザ情報
//    		s.setMtCustomer(c);
//
//    		s.setTotal(((Long)obj[1]));		// 売上合計
//
//    		saleList.add(s);
//    	}
//    	mv.addObject("saleList",saleList);
//    	
//    	// 顧客別売上一覧の表示を行う
//    	mv.setViewName("100mgr/102SaleListCustomer");
//
//    	return mv;
//
//	}
//	
//	/**
//	 * 
//	 * 商品別売上表示(売上一覧表示)
//	 * @param mv
//	 * @return ModelAndView
//	 * 
//	 */
//	@RequestMapping(value = "/SaleListItem", method = RequestMethod.POST)
//	@Transactional(readOnly = true)
//	public ModelAndView saleListItem(ModelAndView mv) {
//
//    	// 売上一覧の取得
//    	List<Object[]> totalList = saleRepo.findBySaleListItem();
//    	// 取得したオブジェクトからFormを作成する
//    	List<SaleListForm> saleList = new ArrayList<>();
//    	for(Object[] obj : totalList) {
//    		SaleListForm s = new SaleListForm();
//
//    		Optional<MtItem> itemData = itemRepo.findById((String)obj[0]);
//    		MtItem i = itemData.get();		// ユーザ情報
//    		s.setMtItem(i);
//
//    		s.setTotal(((Long)obj[1]));		// 売上合計
//
//    		saleList.add(s);
//    	}
//    	mv.addObject("saleList",saleList);
//    	
//    	// 商品別売上一覧の表示を行う
//    	mv.setViewName("100mgr/103SaleListItem");
//
//    	return mv;
//
//	}
//
//	////////////////////////////////////////////////////////////////////////////////////////////////////
//	// 売上明細処理
//	////////////////////////////////////////////////////////////////////////////////////////////////////
//	/**
//	 * 
//	 * 売上明細ボタン押下時のアクション(売上明細表示)
//	 * @param mv
//	 * @return ModelAndView
//	 * 
//	 */
//	@RequestMapping(value = "/SaleDetail")
//	@Transactional(readOnly = true)
//	public ModelAndView detailList(ModelAndView mv) {
//
//		// 売上一覧を取得
//		List<TrSale> saleList = saleRepo.findAllByOrderBySaleId();
//    	mv.addObject("salelist",saleList);
//
//    	// 売上明細一覧の表示を行う
//    	mv.setViewName("100mgr/110SaleDetailList");
//
//    	return mv;
//
//	}
//
	////////////////////////////////////////////////////////////////////////////////////////////////////
	// 顧客マスタ系処理
	////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 顧客一覧表示
	 * @param mv
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/CustomerList", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public ModelAndView custumerList(ModelAndView mv) {

		// 全顧客情報を取得(CustomerCode昇順で取得)
    	List<MtCustomer> customerList = customerRepository.findAllByOrderByCustomerCode();
    	mv.addObject("customerList",customerList);

    	// 顧客リストの表示を行う
    	mv.setViewName("200manager/260customerList");		

    	return mv;
	}

	/**
	 * 
	 * 顧客情報登録メソッド
	 * 顧客登録ボタンクリック時に呼び出される
	 * @param customerForm
	 * @param mv
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/customerCre", method = RequestMethod.POST)
	public ModelAndView custumerCre(
			@ModelAttribute CustomerForm customerForm,
			ModelAndView mv) {
		
		// Form初期化
		customerForm.setCustomerCode(null);
		customerForm.setCustomerName(null);
		mv.setViewName("200manager/261customerCre");
		return mv;

	}

	/**
	 * 
	 * 顧客情報登録確認メソッド
	 * @param customerForm
	 * @param result
	 * @param mv
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/customerCreConf", method = RequestMethod.POST)
	@Transactional(readOnly = true)
	public ModelAndView customerCreConf(
			@ModelAttribute @Validated CustomerForm customerForm,
			BindingResult result,
			ModelAndView mv) {

		if(result.hasErrors()) {	// Validationエラー
			mv.setViewName("200manager/261customerCre");
		} else {
			// 顧客コードが重複していないかチェックする
			if(!customerRepository.existsById(customerForm.getCustomerCode())) {	
				mv.setViewName("200manager/262customerCreConf");		
			}else {
				mv.addObject("errormessage","IDが重複しています");
				mv.setViewName("200manager/261customerCre");
			} 
		}
		
		return mv;

	}

	/**
	 * 
	 * 顧客情報登録・更新実行メソッド
	 * @param customerForm
	 * @param mv
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/customerRegExe", method = RequestMethod.POST)
	@Transactional
	public ModelAndView custmerRegExe(
			@ModelAttribute CustomerForm customerForm,
			ModelAndView mv) {

		// Debug
		System.out.println(customerForm);
		// 顧客情報登録
		MtCustomer m = new MtCustomer(customerForm.getCustomerCode(), customerForm.getCustomerName());
		customerRepository.saveAndFlush(m);
		
		// 顧客リストへリダイレクト
		mv.setViewName("redirect:/Mgr/CustomerList");		
		
		return mv;

	}

	
	/**
	 * 
	 * 顧客情報更新メソッド
	 * 変更リンククリック時に呼び出される
	 * @param customerId
	 * @param customerForm
	 * @param mv
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/customerUpd/{customerCode}", method = RequestMethod.POST)
	@Transactional(readOnly = true)
	public ModelAndView custumerUpd(
			@PathVariable String customerCode,	// 顧客ID
			@ModelAttribute CustomerForm customerForm,
			ModelAndView mv) {
		
		// 変更対象の顧客エンティティ取得
		MtCustomer m = customerRepository.getOne(customerCode);

		// フォームビーンに顧客情報を設定
		customerForm.setCustomerCode(m.getCustomerCode());
		customerForm.setCustomerName(m.getCustomerName());
		
		// 顧客情報変更画面の表示を行う
		mv.setViewName("200manager/265customerUpd");

		return mv;

	}

	/**
	 * 
	 * 顧客情報登録確認メソッド
	 * @param customerForm
	 * @param result
	 * @param mv
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/customerUpdConf", method = RequestMethod.POST)
	@Transactional(readOnly = true)
	public ModelAndView customerUpdConf(
			@ModelAttribute @Validated CustomerForm customerForm,
			BindingResult result,
			ModelAndView mv) {

		if(result.hasErrors()) {
			// Validationエラー
			mv.setViewName("200manager/265customerUpd");
		} else {
			mv.setViewName("200manager/266customerUpdConf");		
		}
		
		return mv;

	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	// 商品マスタ系処理
	////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 商品一覧表示
	 * @param mv
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/ItemList")
	@Transactional(readOnly = true)
	public ModelAndView itemList(ModelAndView mv) {

		// 全商品情報を取得
		// (顧客コードで昇順ソートされた全エンティティを取得する)
    	List<MtItem> itemList = itemRepository.findAllByOrderByItemCode();
    	mv.addObject("itemList",itemList);

    	mv.setViewName("200manager/220itemList");		
		return mv;
	}

//	/**
//	 * 
//	 * 商品登録メソッド
//	 * 商品登録ボタンクリック時に呼び出される
//	 * @param itemForm
//	 * @param mv
//	 * @return ModelAndView
//	 */
//	@RequestMapping(value = "/ItemAdd", method = RequestMethod.POST)
//	@Transactional(readOnly = true)
//	public ModelAndView itemAdd(
//			@ModelAttribute ItemForm itemForm,
//			ModelAndView mv) {
//		
//
//		List<MtCategory>categoryList = categoryRepo.findAllByOrderByCategoryCode();
//		mv.addObject("categoryList", categoryList);
//		
//		mv.setViewName("100mgr/133ItemAdd");
//		return mv;
//
//	}


//	/**
//	 * 
//	 * 商品情報削除確認メソッド
//	 * 削除リンククリック時に呼び出される
//	 * @param itemCode
//	 * @param itemForm
//	 * @param mv
//	 * @return ModelAndView
//	 * 
//	 */
//	@RequestMapping(value = "/itemDel/{itemCode}", method = RequestMethod.GET)
//	@Transactional(readOnly = true)
//	public ModelAndView itemDel(
//			@PathVariable String itemCode,	// 商品ID
//			@ModelAttribute ItemForm itemForm,
//			ModelAndView mv) {
//
//		// 削除対象の商品エンティティを取得する
//		MtItem item = itemRepo.getOne(itemCode);
//		
//		// 削除対象のエンティティをFormに設定する
//		itemForm.setItemCode(item.getItemCode());
//		itemForm.setItemName(item.getItemName());
//		itemForm.setMtCategory(item.getMtCategory());
//		itemForm.setPrice(item.getPrice());
//		itemForm.setSpec(item.getSpec());
//		
//		// 削除確認画面の表示を行う
//		mv.setViewName("100mgr/131ItemDelconf");
//		
//		return mv;
//		
//	}
//
//	/**
//	 * 
//	 * 商品情報削除実行メソッド
//	 * @param itemCode
//	 * @param mv
//	 * @return ModelAndView
//	 * 
//	 */
//	@RequestMapping(value = "/itemDelExe", method = RequestMethod.POST)
//	@Transactional
//	public ModelAndView itemDelExe(
//			@RequestParam String itemCode,
//			ModelAndView mv) {
//
//		// ☆削除失敗時の例外処理が必要
//		itemRepo.deleteById(itemCode);
//		
//		// 商品リスト画面へリダイレクト
//		mv.setViewName("redirect:/Mgr/Item");
//		
//		return mv;
//
//	}
//	
//	/**
//	 * 
//	 * 商品情報更新メソッド
//	 * 変更リンククリック時に呼び出される
//	 * @param itemCode
//	 * @param itemForm
//	 * @param mv
//	 * @return ModelAndView
//	 */
//	@RequestMapping(value = "/itemUpd/{itemCode}", method = RequestMethod.GET)
//	@Transactional(readOnly = true)
//	public ModelAndView itemUpdate(
//			@PathVariable String itemCode,	// 商品ID
//			@ModelAttribute ItemForm itemForm,
//			ModelAndView mv) {
//		
//		// 変更対象の商品エンティティ取得
//		MtItem item = itemRepo.getOne(itemCode);
//
//		// フォームビーンに商品情報を設定
//		itemForm.setItemCode(item.getItemCode());
//		itemForm.setItemName(item.getItemName());
//		itemForm.setMtCategory(item.getMtCategory());
//		itemForm.setPrice(item.getPrice());
//		itemForm.setSpec(item.getSpec());
//		
//		// 商品区分表示用
//		List<MtCategory>categoryList = categoryRepo.findAllByOrderByCategoryCode();
//		mv.addObject("categoryList", categoryList);
//		
//		mv.setViewName("100mgr/132ItemUpdate");
//		return mv;
//
//	}
//
//	/**
//	 * 
//	 * 商品情報更新実行メソッド
//	 * @param itemForm
//	 * @param result
//	 * @param mv
//	 * @return ModelAndView
//	 */
//	@RequestMapping(value = "/itemUpdExe", method = RequestMethod.POST)
//	@Transactional
//	public ModelAndView itemUpdExe(
//			@ModelAttribute @Validated ItemForm itemForm,
//			BindingResult result,
//			ModelAndView mv) {
//
//		// 商品情報更新
//		if(result.hasErrors()) {
//			List<MtCategory>categoryList = categoryRepo.findAllByOrderByCategoryCode();
//			mv.addObject("categoryList", categoryList);
//			mv.setViewName("100mgr/132ItemUpdate");
//		} else {
//			MtItem i = new MtItem(	// 入力情報をもとにエンティティを生成する
//							itemForm.getItemCode(), 
//							itemForm.getItemName(),
//							itemForm.getPrice(),
//							itemForm.getSpec(),
//							itemForm.getMtCategory());
//			itemRepo.saveAndFlush(i);
//			mv.setViewName("redirect:/Mgr/Item");		
//		}
//		
//		return mv;
//
//	}
//
//	/**
//	 * 
//	 * 商品情報登録実行メソッド
//	 * @param itemForm
//	 * @param result
//	 * @param mv
//	 * @return ModelAndView
//	 */
//	@RequestMapping(value = "/itemAdd", method = RequestMethod.POST)
//	@Transactional
//	public ModelAndView itemAddConf(
//			@ModelAttribute @Validated ItemForm itemForm,
//			BindingResult result,
//			ModelAndView mv) {
//		
//		// 商品登録
//		if(result.hasErrors()) {
//			List<MtCategory>categoryList = categoryRepo.findAllByOrderByCategoryCode();
//			mv.addObject("categoryList", categoryList);
//			mv.setViewName("100mgr/133ItemAdd");
//		} else {
//			if(!itemRepo.existsById(itemForm.getItemCode())) {	// 商品コードが重複していないかチェックする
//				MtItem i = new MtItem(itemForm.getItemCode(), itemForm.getItemName(), itemForm.getPrice(), itemForm.getSpec(), itemForm.getMtCategory());
//				itemRepo.saveAndFlush(i);
//				mv.setViewName("redirect:/Mgr/Item");		
//			}else {
//				mv.addObject("errormessage","IDが重複しています");
//				List<MtCategory>categoryList = categoryRepo.findAllByOrderByCategoryCode();
//				mv.addObject("categoryList", categoryList);
//				mv.setViewName("100mgr/133ItemAdd");
//			}
//		}
//
//		return mv;
//
//	}
//
	////////////////////////////////////////////////////////////////////////////////////////////////////
	// 商品区分マスタ系処理
	////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 商品区分一覧表示
	 * @param mv
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/ItemGenreList")
	@Transactional(readOnly = true)
	public ModelAndView categoryList(ModelAndView mv) {

		// 全商品区分情報を取得
    	List<MtItemGenre> itemGenreList = itemGenreRepository.findAllByOrderByItemGenreCode();
    	mv.addObject("itemGenreList", itemGenreList);

    	// 区分リスト画面の表示を行う
    	mv.setViewName("200manager/240itemGenreList");
    	
		return mv;
	}

//	/**
//	 * 
//	 * 商品区分削除確認メソッド
//	 * 削除リンククリック時に呼び出される
//	 * @param categoryCode
//	 * @param categoryForm
//	 * @param mv
//	 * @return ModelAndView
//	 */
//	@RequestMapping(value = "/categoryDel/{categoryCode}", method = RequestMethod.GET)
//	@Transactional(readOnly = true)
//	public ModelAndView categoryDel(
//			@PathVariable String categoryCode,	// 区分コード
//			@ModelAttribute CategoryForm categoryForm,
//			ModelAndView mv) {
//
//		// 削除対象の商品区分エンティティ取得
//		MtCategory c = categoryRepo.getOne(categoryCode);
//
//		// フォームビーンへ設定
//		categoryForm.setCategoryCode(c.getCategoryCode());
//		categoryForm.setName(c.getName());
//		
//		// 削除確認画面の表示を行う
//		mv.setViewName("100mgr/141CategoryDelconf");
//		
//		return mv;
//	}
//
//	/**
//	 * 
//	 * 商品区分削除実行メソッド
//	 * @param categoryCode
//	 * @param mv
//	 * @return ModelAndView
//	 */
//	@RequestMapping(value = "/categoryDelExe", method = RequestMethod.POST)
//	@Transactional
//	public ModelAndView categoryDelExe(
//			@RequestParam String categoryCode,
//			ModelAndView mv) {
//
//		// 顧客情報削除
//		// ☆子レコードありで削除できない場合の例外キャッチが必要
//		categoryRepo.deleteById(categoryCode);
//		
//		// 商品区分一覧へリダイレクト
//		mv.setViewName("redirect:/Mgr/Category");
//		
//		return mv;
//
//	}
//	
//	/**
//	 * 
//	 * 商品区分更新メソッド
//	 * 変更リンククリック時に呼び出される
//	 * @param categoryCode	商品区分コード
//	 * @param categoryForm	フォームビーン
//	 * @param mv ModelAndView
//	 * @return ModelAndView
//	 */
//	@RequestMapping(value = "/categoryUpd/{categoryCode}", method = RequestMethod.GET)
//	@Transactional(readOnly = true)
//	public ModelAndView categoryUpdate(
//			@PathVariable String categoryCode,	// カテゴリーID
//			@ModelAttribute CategoryForm categoryForm,
//			ModelAndView mv) {
//		
//		// 変更対象の商品区分エンティティを取得する
//		MtCategory c = categoryRepo.getOne(categoryCode);
//
//		// エンティティの情報をフォームビーンに設定する
//		categoryForm.setCategoryCode(c.getCategoryCode());
//		categoryForm.setName(c.getName());
//		
//		// 商品区分変更画面の表示を行う
//		mv.setViewName("100mgr/142CategoryUpdate");
//		
//		return mv;
//
//	}
//
//	/**
//	 * 
//	 * 商品区分更新実行メソッド
//	 * @param categoryForm フォームビーン
//	 * @param result
//	 * @param mv
//	 * @return ModelAndView
//	 */
//	@RequestMapping(value = "/categoryUpdExe", method = RequestMethod.POST)
//	@Transactional(readOnly=false)
//	public ModelAndView categoryUpdExe(
//			@ModelAttribute @Validated CategoryForm categoryForm,
//			BindingResult result,
//			ModelAndView mv) {
//
//		// 商品区分情報更新
//		if(result.hasErrors()) {
//			mv.setViewName("100mgr/142CategoryUpdate");
//		} else {
//			MtCategory c = new MtCategory(categoryForm.getCategoryCode(), categoryForm.getName());
//			categoryRepo.saveAndFlush(c);
//			mv.setViewName("redirect:/Mgr/Category");		
//		}
//		
//		return mv;
//
//	}
//
//	/**
//	 * 商品区分情報登録メソッド
//	 * 商品区分登録ボタンクリック時に呼び出される
//	 * @param categoryForm フォームビーン
//	 * @param mv
//	 * @return
//	 */
//	@RequestMapping(value = "/CategoryAdd", method = RequestMethod.POST)
//	@Transactional(readOnly = true)
//	public ModelAndView categoryAdd(
//			@ModelAttribute CategoryForm categoryForm,
//			ModelAndView mv) {
//		
//		// Form初期化
//		categoryForm.setCategoryCode(null);;
//		categoryForm.setName(null);
//		
//		// 区分追加画面の表示を行う
//		mv.setViewName("100mgr/143CategoryAdd");
//
//		return mv;
//
//	}
//
//	/**
//	 * 商品区分登録実行メソッド
//	 * @param categoryForm
//	 * @param result
//	 * @param mv
//	 * @return ModelAndView
//	 */
//	@RequestMapping(value = "/categoryAddExe", method = RequestMethod.POST)
//	@Transactional
//	public ModelAndView categoryAddConf(
//			@ModelAttribute @Validated CategoryForm categoryForm,
//			BindingResult result,
//			ModelAndView mv) {
//
//		if(result.hasErrors()) {
//			mv.setViewName("100mgr/143CategoryAdd");
//		} else {
//			if(!categoryRepo.existsById(categoryForm.getCategoryCode())) {	// 区分コードが重複していないかチェックする
//				MtCategory c = new MtCategory(categoryForm.getCategoryCode(), categoryForm.getName());
//				categoryRepo.saveAndFlush(c);
//				mv.setViewName("redirect:/Mgr/Category");		
//			}else {
//				mv.addObject("errormessage","IDが重複しています");
//				mv.setViewName("100mgr/143CategoryAdd");
//			}
//		}
//		
//		return mv;
//
//	}
//
}
