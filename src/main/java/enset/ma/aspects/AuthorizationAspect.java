package enset.ma.aspects;

import enset.ma.service.SecurityContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy
public class AuthorizationAspect {

    @Around(value = "@annotation(securedByAspect)", argNames = "pjp,securedByAspect")
    public Object secure(ProceedingJoinPoint pjp, SecuredByAspect securedByAspect) throws Throwable {
        String[] roles = securedByAspect.roles();
        boolean authorized = false;
        for (String r : roles){
            if(SecurityContext.hasRole(r)){
                authorized = true; break;
            }
        }
        if(authorized == true){
            Object result = pjp.proceed();
            return result;
        }else {
            throw new RuntimeException("Unauthorizied "+pjp.getSignature());
        }

    }
}


