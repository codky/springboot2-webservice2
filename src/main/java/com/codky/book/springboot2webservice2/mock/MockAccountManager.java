package com.codky.book.springboot2webservice2.mock;

import java.util.HashMap;
import java.util.Map;

public class MockAccountManager implements AccountManager{

    private Map<String,Account> accounts = new HashMap<String, Account>();

    /**
     * 아이디와 account 객체를 hashMap객체에 put
     * @param userId
     * @return
     */
    public void addAccount(String userId, Account account) {
        this.accounts.put(userId, account);
    }

    /**
     * 아이디로 HashMap객체에서 account  객체를 찾아 리턴
     * @param userId
     * @return
     */
    @Override
    public Account findAccountForUser(String userId) {
        return this.accounts.get(userId);
    }

    /**
     * 계정 정보를 갱신하며 반환값은 없다.
     * @param account
     */
    @Override
    public void updateAccount(Account account) {
        // do nothing
    }
}
