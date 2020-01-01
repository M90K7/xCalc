package com.mk7.xcalc.presenters.funcs;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;

import java.util.Stack;

/**
 * Created by MK7 on 6/1/2015.
 */
public class Sqr extends PostfixMathCommand {

    public Sqr(){
        numberOfParameters = 2;
    }

    @Override
    public void run(Stack stack) throws ParseException {
        checkStack(stack);

        Object o_numA = stack.pop();
        Object o_numN = stack.pop();

        if(o_numA instanceof Double && o_numN instanceof Double){
            double numA = ((Double)o_numA).doubleValue();
            int numN = ((Double)o_numN).intValue();

            stack.push(new Double(nthroot(numN,numA)));

        }else{
            throw new ParseException("Invalid parameter type");
        }
    }

    public static double nthroot(int n, double A) {
        return nthroot(n, A, .001);
    }
    public static double nthroot(int n, double A, double p) {
        if(A < 0) {
            System.err.println("A < 0");// we handle only real positive numbers
            return -1;
        } else if(A == 0) {
            return 0;
        }
        double x_prev = A;
        double x = A / n;  // starting "guessed" value...
        while(Math.abs(x - x_prev) > p) {
            x_prev = x;
            x = ((n - 1.0) * x + A / Math.pow(x, n - 1.0)) / n;
        }
        return x;
    }
}
