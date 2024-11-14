package com.example.tdd.logback;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class MyApp2 {
  final static Logger logger = LoggerFactory.getLogger(MyApp2.class);

  public static void main(String[] args) {
    // assume logback is in use
    LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

    Map<String, String> rules
            = (Map<String, String>) lc.getObject(CoreConstants.PATTERN_RULE_REGISTRY);
    if (rules == null) {
      rules = new HashMap<String, String>();
      lc.putObject(CoreConstants.PATTERN_RULE_REGISTRY, rules);
    }
    rules.put("custom", CustomConverter.class.getName());

    logger.info("%custom Entering application.");

    Foo foo = new Foo();
    foo.doIt();
    logger.info("Exiting application.");
  }
}

class CustomConverter extends ClassicConverter {
  public String convert(ILoggingEvent event) {
    // mdc = event.getMDCPropertyMap();
    var formattedMessage = event.getFormattedMessage();
    System.out.println("--------------------------------"+formattedMessage);
    return "variable-expanded";
  }
}