package com.pci;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pci.security.UserAccountService;
/**
 * テストクラス
 * DBにユーザを登録する
 * パスワードハッシュ化対応のため、テストクラスを利用する
 * @author kzhk9
 *
 */
@SpringBootTest
class SalesAdvApplicationTests {

	@Autowired
	private UserAccountService sv;
	
	@Test
	public void contextLoads() {
//		sv.registerAdmin("u001","システム管理者","admin",true);
		sv.registerManager("u002","江戸　一郎","mgr",true);
		sv.registerUser("u003","平川　太郎","user",true);
		sv.registerUser("u004","大手　次郎","user",true);
		sv.registerUser("u005","桔梗　花子","user",true);
		sv.registerUser("u006","坂下　幸子","user",true);
		sv.registerUser("u007","桜田　三郎","user",true);
		sv.registerUser("u008","田安　健一","user",true);
		sv.registerUser("u009","清水　緑","user",true);
	}

}
