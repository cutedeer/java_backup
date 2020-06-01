package aop;

import java.lang.annotation.*;

/**
 *
 * Controller的包裹
 * 1,提供输入输出的打印
 * 2,提供bizException和sysException的监控及日志及返回
 * 3,提供总的耗时监控
 */
@Target({ElementType.PARAMETER,ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerWrapper {

    //打印输入输出参数
    boolean printInput() default true;

    boolean printOutput() default true;

    //关键字
    String keyWords();

}
