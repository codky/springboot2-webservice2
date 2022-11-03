package com.codky.book.springboot2webservice2.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

public class TestAccountServiceEasyMock {

    private AccountManager mockAccountManager;

    @Before
    public void setUp() {
        // mock 객체를 생성
        mockAccountManager = createMock("mockAccountManager", AccountManager.class);
    }

    @Test
    public void testTransferOk() {
        Account senderAccount = new Account("1", 200);
        Account beneficiaryAccount = new Account("2", 100);

        mockAccountManager.updateAccount(senderAccount);
        mockAccountManager.updateAccount(beneficiaryAccount);

        // 기대되는 행위 및 리턴 값을 기록한다.
        // expect: 기대되는 행위 메서드
        // add Return: 리턴
        expect(mockAccountManager.findAccountForUser("1")).andReturn(senderAccount);
        expect(mockAccountManager.findAccountForUser("2")).andReturn(beneficiaryAccount);

        // 해당 mock 객체를 수행한다.
        replay(mockAccountManager);

        AccountService accountService = new AccountService();
        accountService.setAccountManager(mockAccountManager);
        accountService.transfer("1", "2", 50);

        assertEquals(150, senderAccount.getBalance());
        assertEquals(150, beneficiaryAccount.getBalance());
    }

    @After
    public void tearDown() {
        // 테스트 실행
        verify(mockAccountManager);
    }
}
