package com.codky.book.springboot2webservice2.mock;

public interface AccountManager {
    /**
     * 아이디로 계좌 계정찾기
     */
    Account findAccountForUser(String userId);

    /**
     * 계좌 계정 업데이트
     */
    void updateAccount(Account account);
}
