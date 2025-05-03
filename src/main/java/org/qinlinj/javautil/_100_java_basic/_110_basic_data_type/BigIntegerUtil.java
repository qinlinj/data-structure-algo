package org.qinlinj.javautil._100_java_basic._110_basic_data_type;

import java.math.BigInteger;

public class BigIntegerUtil {
    public static void main(String[] args) {
        // storage range > long
        long a = 9223372036854775807L;
        System.out.println(a);
        System.out.println(Long.MAX_VALUE);

        BigInteger b = new BigInteger("922337203685477580781234");
        System.out.println(b);
    }
}
