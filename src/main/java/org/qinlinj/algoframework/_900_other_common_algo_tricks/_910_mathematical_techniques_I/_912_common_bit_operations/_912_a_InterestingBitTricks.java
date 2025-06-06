package org.qinlinj.algoframework._900_other_common_algo_tricks._910_mathematical_techniques_I._912_common_bit_operations;

public class _912_a_InterestingBitTricks {
    /**
     * Converts English character to lowercase using OR operation with space
     */
    public char toLowerCase(char c) {
        return (char) (c | ' ');
    }

    /**
     * Converts English character to uppercase using AND operation with underscore
     */
    public char toUpperCase(char c) {
        return (char) (c & '_');
    }

    /**
     * Toggles case of English character using XOR operation with space
     */
    public char toggleCase(char c) {
        return (char) (c ^ ' ');
    }

}
