package com.tj.exercise.flash.sale.demo.util;

/**
 * @Author: tj
 * @Date: 2024/1/15 20:42
 */
import com.sun.el.util.ReflectionUtil;
import io.swagger.util.ReflectionUtils;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;


import java.lang.reflect.Method;

public class SpelUtil {

    private static final ExpressionParser parser = new SpelExpressionParser();

    public static String parseSpel(Method method, Object[] args, String spelExpression) {
        StandardEvaluationContext context = new StandardEvaluationContext();

        // 设置方法参数为 SpEL 上下文变量
        String[] parameterNames =getParameterNames(method);
        if (parameterNames != null && args != null && parameterNames.length == args.length) {
            for (int i = 0; i < parameterNames.length; i++) {
                context.setVariable(parameterNames[i], args[i]);
            }
        }

        // 解析 SpEL 表达式
        return parser.parseExpression(spelExpression).getValue(context, String.class);
    }

    private static String[] getParameterNames(Method method) {
        ParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
        return discoverer.getParameterNames(method);
    }
}

