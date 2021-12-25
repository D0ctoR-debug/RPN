package com.example.RPM;

import com.example.RPM.service.RPMMethodsImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RpmApplicationTests {

    private RPMMethodsImpl rpmMethods;

    @Autowired
    public void setRpmMethods(RPMMethodsImpl rpmMethods) {
        this.rpmMethods = rpmMethods;
    }

    @Test
    void contextLoads() {
    }

    @Tag("Is operator method for +,- and ^")
    @Test
    void isOperatorFirstTest() {
        char firstOperator = '+';
        char secondOperator = '-';
        char thirdOperator = '-';
        System.out.println("======Testing is operator method for +,- and ^=======");
        Assertions.assertTrue(rpmMethods.isOperator(firstOperator));
        Assertions.assertTrue(rpmMethods.isOperator(secondOperator));
        Assertions.assertTrue(rpmMethods.isOperator(thirdOperator));
    }

    @Tag("Is operator method without operator")
    @Test
    void isOperatorSecondTest() {
        char operator = 'e';
        System.out.println("======Testing is operator method without operators=======");
        Assertions.assertFalse(rpmMethods.isOperator(operator));
    }

    @Tag("Is operator method for / and *")
    @Test
    void isOperatorThirdTest() {
        char firstOperator = '/';
        char secondOperator = '*';
        System.out.println("======Testing is operator method for * and /=======");
        Assertions.assertTrue(rpmMethods.isOperator(firstOperator));
        Assertions.assertTrue(rpmMethods.isOperator(secondOperator));
    }


    @Test
    void isDelimeterTest() {
        char firstDelimeter = ' ';
        char secondDelimeter = ' ';
        System.out.println("======Testing is delimeter method=======");
        Assertions.assertTrue(rpmMethods.isDelimeter(firstDelimeter));
        Assertions.assertTrue(rpmMethods.isDelimeter(secondDelimeter));
    }

    @Test
    void getPriorityTest() {
        char firstOperator = '*';
        char secondOperator = '+';
        char thirdOperator = '(';
        System.out.println("======Test for the priority method  =======");
        Assertions.assertEquals(3, rpmMethods.getPriority(firstOperator));
        Assertions.assertEquals(2, rpmMethods.getPriority(secondOperator));
        Assertions.assertTrue(rpmMethods.getPriority(secondOperator) > rpmMethods.getPriority(thirdOperator));
    }

    @Test
    void getExpressionTest() {
        System.out.println("======A test for converting an expression======");
        String initialExpression = "3+2*4";
        String rpnExpression = rpmMethods.getExpression(initialExpression);
        Assertions.assertEquals("3 2 4*+",rpnExpression);

    }

    @Test
    void calculationTest(){
        System.out.println("=======Test for a calculation======");
        String expression = "(3+4)*5-(5-3)/2";
        Assertions.assertEquals(34,rpmMethods.calculate(expression));
    }


}
