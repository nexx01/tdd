package com.example.tdd.queryAndSpell;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.spel.spi.EvaluationContextExtension;
import org.springframework.stereotype.Component;

import java.util.Locale;

//@Component
public class LocaleContextHolderExtension implements EvaluationContextExtension {

    @Override
    public String getExtensionId() {
        return "customSpell";
    }

    @Override
    public Locale getRootObject() {
        return LocaleContextHolder.getLocale();
    }
}