package enset.ma.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@Component
@Aspect
@EnableAspectJAutoProxy
public class LogAspect {

    Logger logger = Logger.getLogger(this.getClass().getName());

    public LogAspect() throws IOException {
        logger.addHandler(new FileHandler("log.xml"));
        logger.setUseParentHandlers(false);
    }

    @Around("@annotation(enset.ma.aspects.Log)")
    public Object log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long t1 = System.currentTimeMillis();
        logger.info("from logging aspect before "+proceedingJoinPoint.getSignature());
        Object result = proceedingJoinPoint.proceed();
        long t2 = System.currentTimeMillis();
        logger.info("from logging aspect after "+proceedingJoinPoint.getSignature());
        logger.info("Duration "+(t2-t1));
        return result;
    }
}
