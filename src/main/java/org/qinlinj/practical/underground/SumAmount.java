package org.qinlinj.practical.underground;

/**
 * Represents a data structure that tracks both a sum and a count (amount).
 * This class can be used for calculating averages or tracking cumulative values.
 */
public class SumAmount {
    private int sum;
    private int amount;

    public SumAmount(int sum, int amount) {
        this.sum = sum;
        this.amount = amount;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
