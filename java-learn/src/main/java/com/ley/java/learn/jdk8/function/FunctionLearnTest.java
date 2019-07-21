package com.ley.java.learn.jdk8.function;

import org.junit.Test;

import java.util.function.Function;

/**
 * 函数式接口学习
 *
 * @author liuenyuan
 * @see FunctionalInterface
 * @see java.util.function.Function
 * @see java.util.function.Consumer
 * @see java.util.function.Supplier
 * @see java.util.function.Predicate
 * @see java.util.function.BinaryOperator
 * @see java.util.function.UnaryOperator
 * @see java.util.function.BiFunction
 * @see java.util.function.BiConsumer
 **/
public class FunctionLearnTest {

    /**
     * @see java.util.function.Function
     **/
    @Test
    public void testFunction() {
        //返回一个执行了apply()方法之后,只会返回输入参数的函数对象
        Function<Integer, Integer> function = e -> e * 2;
        System.out.println(function.apply(3));

        //andThen使用,先计算前一个,在计算后一个
        Function<Integer, Integer> function1 = e -> e * e;
        System.out.println(function.andThen(function1).apply(3));

        //先计算后一个,在就散前一个
        Function<Integer, Integer> function2 = e -> e * 3;
        System.out.println(function.compose(function2).apply(3));
    }
}
