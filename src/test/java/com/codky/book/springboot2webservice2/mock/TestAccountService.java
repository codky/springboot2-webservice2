package com.codky.book.springboot2webservice2.mock;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestAccountService {

    @Test
    public void testTransferOk() {
        // 테스트를 하기위한 객체 생성 및 준비
        MockAccountManager mockAccountManager = new MockAccountManager();
        Account senderAccount = new Account("1", 200);
        Account beneficiaryAccount = new Account("2", 100);
        mockAccountManager.addAccount("1", senderAccount);
        mockAccountManager.addAccount("2", beneficiaryAccount);

        AccountService accountService = new AccountService();
        accountService.setAccountManager(mockAccountManager);

        // 테스트 수행
        accountService.transfer("1","2", 50);

        // 결과 검증
        assertEquals(150, senderAccount.getBalance());
        assertEquals(150, beneficiaryAccount.getBalance());


    }
}
