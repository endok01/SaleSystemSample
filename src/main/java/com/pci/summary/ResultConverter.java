package com.pci.summary;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 集計結果を各オブジェクトに変換するクラス
 * マネージャーロール、ユーザロールから使用されるのでstaticメソッドとして実装
 * @author endo_k01
 *
 */
public class ResultConverter {

	/**
	 * 
	 * 集計結果リストオブジェクトとして格納する
	 * @param result
	 * @return
	 */
	public static List<SalesSummary> salesSummaryResultConverter(List<Object[]> result) {
		List<SalesSummary> salesList = new ArrayList<>();
		for(Object[] objects : result) {
			salesList.add(new SalesSummary(objects));
		}
		return salesList;
	}
	
	public static List<SalesSummaryDate> salesSummaryResultConverterForDate(List<Object[]> result) {
		List<SalesSummaryDate> salesList = new ArrayList<>();
		for(Object[] objects : result) {
			salesList.add(new SalesSummaryDate(objects));
		}
		return salesList;
	}

}
