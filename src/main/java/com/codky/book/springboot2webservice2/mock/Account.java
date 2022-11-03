package com.codky.book.springboot2webservice2.mock;

public class Account {
    /**
     * 계좌 아이디
     */
    private String accountId;

    /**
     * 계좌 잔고
     */
    private long balance;

    /**
     * 초기화
     * @param accountId
     * @param initialBalance
     */
    public Account(String accountId, long initialBalance) {
        this.accountId=accountId;
        this.balance=initialBalance;
    }

    /**
     * 출금
     */
    public void debit(long amount) {
        this.balance -= amount;
    }

    /**
     * 입금
     */
    public void credit(long amount) {
        this.balance += amount;
    }

    /**
     * 현재 잔고
     */
    public long getBalance() {
        return this.balance;
    }
}
