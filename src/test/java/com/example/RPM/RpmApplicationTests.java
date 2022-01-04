package com.example.RPM;

import com.example.RPM.service.RPMMethodsImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(Parameterized.class)
class RpmApplicationTests {

    private static final Logger log = LogManager.getLogger(RpmApplicationTests.class);

    private RPMMethodsImpl rpmMethods;

    @Autowired
    public void setRpmMethods(RPMMethodsImpl rpmMethods) {
        this.rpmMethods = rpmMethods;
    }

    @Test
    void contextLoads() {
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/IsOperatorMethod.csv", numLinesToSkip = 1, delimiter = ';')
    void isOperatorFirstTest(char operator, boolean expected) {
        log.info("======Testing is operator method=======");
        Assertions.assertEquals(expected,rpmMethods.isOperator(operator));

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/IsDelimiterMethod.csv", numLinesToSkip = 1, delimiter = ';')
    void isDelimiterTest(char delimiter, boolean expected) {
        log.info("======Testing is delimiter method=======");
       Assertions.assertEquals(expected,rpmMethods.isDelimiter(delimiter));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/GetPriority.csv", numLinesToSkip = 1, delimiter = ';')
    void getPriorityTest(char operator,int expected) {
        log.info("======Test for the priority method  =======");
        Assertions.assertEquals(expected, rpmMethods.getPriority(operator));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/GetExpression.csv", numLinesToSkip = 1, delimiter = ';')
    void getExpressionTest(String expression, String expected) {
        log.info("======A test for converting an expression======");
        Assertions.assertEquals(expected, rpmMethods.getExpression(expression));

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/Calculation.csv", numLinesToSkip = 1, delimiter = ';')
    void calculationTest(String expression, double result) {
        log.info("=======Test for a calculation======");
        Assertions.assertEquals(result, rpmMethods.calculate(expression));
    }


}
