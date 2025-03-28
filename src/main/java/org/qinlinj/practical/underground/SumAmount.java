package org.qinlinj.practical.underground;

/**
 * Represents a data structure that tracks both a sum and a count (amount).
 * This class can be used for calculating averages or tracking cumulative values.
 */
public class SumAmount {
    /**
     * The total sum value
     */
    private int sum;

    /**
     * The count or number of items
     */
    private int amount;

    /**
     * Constructs a new SumAmount object with the specified sum and amount values.
     *
     * @param sum    The initial sum value
     * @param amount The initial count or amount value
     */
    public SumAmount(int sum, int amount) {
        this.sum = sum;
        this.amount = amount;
    }

    /**
     * Returns the current sum value.
     *
     * @return The sum value
     */
    public int getSum() {
        return sum;
    }

    /**
     * Sets the sum value.
     *
     * @param sum The new sum value to set
     */
    public void setSum(int sum) {
        this.sum = sum;
    }

    /**
     * Returns the current amount (count) value.
     *
     * @return The amount value
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets the amount (count) value.
     *
     * @param amount The new amount value to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
}