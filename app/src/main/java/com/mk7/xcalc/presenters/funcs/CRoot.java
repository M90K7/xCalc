package com.mk7.xcalc.presenters.funcs;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;

import java.util.Stack;

/**
 * Created by MK7 on 6/1/2015.
 */
public class CRoot extends PostfixMathCommand {

    public CRoot() {
        numberOfParameters = 1;
    }


    @Override
    public void run(Stack stack) throws ParseException {
        try {
            checkStack(stack);
            Object o_num = stack.pop();
            if (o_num instanceof Double) {
                double num = ((Double) o_num).doubleValue();

                stack.push(new Double(Math.cbrt(num)));
            } else {
                throw new ParseException("Invalid parameter type");
            }
        } catch (ParseException e) {
            throw e;
        } catch (Throwable e) {
            throw new ParseException(e.getMessage());
        }
    }
}
