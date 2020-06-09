package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import util.JacksonHandler;

import java.lang.reflect.Method;


@Aspect
@Component
public class ControllerWrapperAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerWrapperAspect.class);

    private static final String CLASS_METHOD = "_";

    //切入点
    @Pointcut("@annotation(ControllerWrapper)")
    public void controllerAspect() {

    }

    /**
     */
    @Around("controllerAspect()")
    public Object recordControllerTrace(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        long start = System.currentTimeMillis();
        String keyWords = "";
        String methodDesc = null;
        boolean printInput = false;
        boolean printOutput = false;
        try {
            Class declaringType = joinPoint.getSignature().getDeclaringType();
            String method = joinPoint.getSignature().getName();
            Object[] args = joinPoint.getArgs();
            methodDesc = declaringType.getSimpleName() + CLASS_METHOD + method;
            ControllerWrapper aopMark = getAopMark(joinPoint, method);

            if (aopMark == null) {
                result = joinPoint.proceed();
            } else {
                keyWords = aopMark.keyWords();
                printInput = aopMark.printInput();
                printOutput = aopMark.printOutput();
                if (printInput) {
                    LOGGER.info("{} recordControllerTrace {} request:{}", methodDesc, keyWords, JacksonHandler.toSerialize(args));
                }
                result = joinPoint.proceed();
            }
        } catch (Throwable e) {
            LOGGER.error("{} recordControllerTrace error {}", methodDesc, keyWords, e);
            if (e instanceof RuntimeException) {
                LOGGER.error("{} {} biz error", methodDesc, keyWords);
//                result = ResultData.buildBusinessFailure(e.getMessage());
            } else if (e instanceof Exception) {
                LOGGER.error("{} {} sys error", methodDesc, keyWords);
//                result = ResultData.builderFailure();
            } else {
                throw e;
            }
        } finally {
            long duration = System.currentTimeMillis() - start;
            if (printOutput) {
                LOGGER.info("{} {} recordControllerTrace totalTime:{} response:{}", methodDesc, keyWords, duration, JacksonHandler.toSerialize(result));
            }
        }
        return result;
    }

    private ControllerWrapper getAopMark(ProceedingJoinPoint point, String methodName) {
        try {
            Class<?> targetClass = point.getTarget().getClass();
            Class[] parameterTypes = ((MethodSignature) point.getSignature()).getParameterTypes();
            Method method = targetClass.getMethod(methodName, parameterTypes);
            return method.getAnnotation(ControllerWrapper.class);
        } catch (Exception e) {
            LOGGER.error("{} getAopMark error", methodName, e);
            return null;
        }
    }
}
