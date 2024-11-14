package com.example.tdd.spell;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException {
        ExpressionParser parser
                = new SpelExpressionParser();
        StandardEvaluationContext context
                = new StandardEvaluationContext();


        context.registerFunction(
                "reverseString",
                StringUtils.class.getDeclaredMethod(
                        "reverseString", String.class));

        context.registerFunction(
                "capitalizeString",
                StringUtils.class.getDeclaredMethod(
                        "capitalizeString", String.class));

        context.registerFunction(
                "subString",
                StringUtils.class.getDeclaredMethod(
                        "subString", String.class, int.class,
                        int.class));


        context.registerFunction(
                "subStringWithOnlyUpper",
                StringUtils.class.getDeclaredMethod(
                        "subString", String.class, int.class));

        context.registerFunction(
                "joinString",
                StringUtils.class.getDeclaredMethod(
                        "joinString", String.class, String.class));

        String helloWorldReversed
                = parser
                .parseExpression(
                        "#capitalizeString(#joinString(#subString('hello',1,4),#subStringWithOnlyUpper('world',1)))")
                .getValue(context, String.class);

        System.out.println(helloWorldReversed);


    }
}
