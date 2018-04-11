package com.s3corp.util.converter;

import com.s3corp.dto.AccountDTO;
import com.s3corp.dto.LayoutDTO;
import com.s3corp.entity.Account;
import com.s3corp.entity.Layout;

public class DTOConverter {

	public static AccountDTO convertAccount(Account account) {
		return new AccountDTO(account.getUserName(), account.getPassword(), account.getRole(), account.getEmail(),
				account.getName(), account.isActive());
	}

	public static LayoutDTO convertLayout(Layout layout) {
		return new LayoutDTO(layout.getId(), layout.getLayoutName());
	}

}
