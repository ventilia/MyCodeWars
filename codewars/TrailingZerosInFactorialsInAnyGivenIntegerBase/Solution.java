package TrailingZerosInFactorialsInAnyGivenIntegerBase;

import java.math.BigInteger;
import java.util.*;

class Solution {


    public static BigInteger trailingZeros(BigInteger num, BigInteger base) {
        if (num.compareTo(BigInteger.ZERO) == 0) {
            return BigInteger.ZERO;
        }

        //  ФАКТ. base
        Map<BigInteger, Integer> primeExponents = factorize(base);

        // для каждого простого считаем сколько раз он входит
        BigInteger minZeros = null;
        for (Map.Entry<BigInteger, Integer> entry : primeExponents.entrySet()) {
            BigInteger p = entry.getKey();
            int expInBase = entry.getValue();

            // exponent = sum_{k>=1} floor(num / p^k)
            BigInteger exponent = BigInteger.ZERO;
            BigInteger pPower = p;
            while (pPower.compareTo(num) <= 0) {
                exponent = exponent.add(num.divide(pPower));
                pPower = pPower.multiply(p);
            }

            // делим на expInBase и берем флур
            BigInteger zerosForP = exponent.divide(BigInteger.valueOf(expInBase));
            if (minZeros == null || zerosForP.compareTo(minZeros) < 0) {
                minZeros = zerosForP;
            }
        }

        return minZeros == null ? BigInteger.ZERO : minZeros;
    }

    private static Map<BigInteger, Integer> factorize(BigInteger n) {
        Map<BigInteger, Integer> map = new HashMap<>();
        if (n.compareTo(BigInteger.ONE) <= 0) {
            return map;
        }
        // рекурссия
        if (n.isProbablePrime(30)) {
            map.put(n, 1);
        } else {
            BigInteger divisor = pollardsRho(n);
            for (Map<BigInteger, Integer> part : Arrays.asList(factorize(divisor), factorize(n.divide(divisor)))) {
                for (Map.Entry<BigInteger, Integer> e : part.entrySet()) {
                    map.merge(e.getKey(), e.getValue(), Integer::sum);
                }
            }
        }
        return map;
    }

    //алгоритм поиска  делителя
    private static BigInteger pollardsRho(BigInteger n) {
        Random rnd = new Random();
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return BigInteger.TWO;
        }
        BigInteger x, y, c, d;
        do {
            //f = x^2 + c
            x = new BigInteger(n.bitLength(), rnd).mod(n);
            y = x;
            c = new BigInteger(n.bitLength(), rnd).mod(n);
            if (c.equals(BigInteger.ZERO)) c = BigInteger.ONE;
            d = BigInteger.ONE;
 
            while (d.equals(BigInteger.ONE)) {
                x = x.multiply(x).mod(n).add(c).mod(n);
                y = y.multiply(y).mod(n).add(c).mod(n);
                y = y.multiply(y).mod(n).add(c).mod(n);
                // d = gcd(|x-y|, n)
                d = x.subtract(y).abs().gcd(n);
                if (d.equals(n)) break;
            }
        } while (d.equals(BigInteger.ONE) || d.equals(n));

        return d;
    }
}
