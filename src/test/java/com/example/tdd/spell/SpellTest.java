package com.example.tdd.spell;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SpellTest {

    @Test
    void name() throws NoSuchMethodException {
        var spelExpressionParser = new SpelExpressionParser();

        var spelExpression = spelExpressionParser.parseRaw("'Hello, world!'").getValue();
        assertThat(spelExpression).isEqualTo("Hello, world!");

        //----------------------------------------------------------------
        var standardEvaluationContext = new StandardEvaluationContext();
        standardEvaluationContext.setVariable("some","aaa");
        var spelExpression2 = spelExpressionParser.parseRaw("#some").getValue(standardEvaluationContext);
        assertThat(spelExpression2).isEqualTo("aaa");


        //----------------------------------------------------------------
        var standardEvaluationContext5 = new StandardEvaluationContext();
        standardEvaluationContext5.setVariable("some","aaa");
        var spelExpression5 = spelExpressionParser.parseExpression("#some").getValue(standardEvaluationContext5);
        assertThat(spelExpression5).isEqualTo("aaa");


//        ----------------------------------------------------------------
        var standardEvaluationContext3 = new StandardEvaluationContext();
        standardEvaluationContext3.setVariable("some","aaa");
        var spelExpression3 = spelExpressionParser.parseExpression("#{#some}",new TemplateParserContext()).getValue(standardEvaluationContext3);
        assertThat(spelExpression3).isEqualTo("aaa");


//        ----------------------------------------------------------------
        var standardEvaluationContext6 = new StandardEvaluationContext();
        standardEvaluationContext6.setVariable("car",new Car("aaa"));
        var spelExpression6 = spelExpressionParser.parseExpression("#{#car.getName()}",new TemplateParserContext()).getValue(standardEvaluationContext6);
        assertThat(spelExpression6).isEqualTo("aaa");


//        ----------------------------------------------------------------
        var standardEvaluationContext7 = new StandardEvaluationContext();
        standardEvaluationContext7.setVariable("car",new Car("aaa"));
        var spelExpression7 = spelExpressionParser.parseExpression("#{#car.name}",new TemplateParserContext()).getValue(standardEvaluationContext7);
        assertThat(spelExpression7).isEqualTo("aaa");


//        ----------------------------------------------------------------
        var standardEvaluationContext8 = new StandardEvaluationContext();
        standardEvaluationContext8.setVariable("car",new Car("aaa"));
        standardEvaluationContext8.registerFunction("capitalize", SpellUtil.class.getMethod("capitalize",String.class));
        var spelExpression8 = spelExpressionParser.parseExpression("#capitalize('car.name')").getValue(standardEvaluationContext8);
        assertThat(spelExpression8).isEqualTo("CAR.NAME");

        //        ----------------------------------------------------------------
        var standardEvaluationContext9 = new StandardEvaluationContext();
        standardEvaluationContext9.setVariable("car",new Car("aaa"));
        standardEvaluationContext9.registerFunction("capitalize", SpellUtil.class.getMethod("capitalize",String.class));
        var spelExpression9 = spelExpressionParser.parseExpression("#capitalize(#car.name)").getValue(standardEvaluationContext9);
        assertThat(spelExpression9).isEqualTo("AAA");


        //        ----------------------------------------------------------------
        var standardEvaluationContext0 = new StandardEvaluationContext();
        standardEvaluationContext0.setVariable("car",new Car("aaa"));
        standardEvaluationContext0.registerFunction("capitalize", SpellUtil.class.getMethod("capitalize",String.class));
        var spelExpression0 = spelExpressionParser.parseExpression("#{#capitalize(#car.name)}",new TemplateParserContext()).getValue(standardEvaluationContext0);
        assertThat(spelExpression0).isEqualTo("AAA");
    }
}


class Car {

    public Car(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class SpellUtil {

    public static String capitalize(String s) {
        return s.toUpperCase();
    }
}