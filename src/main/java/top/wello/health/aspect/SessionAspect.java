package top.wello.health.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import top.wello.health.Request.BaseRequest;
import top.wello.health.Request.RestResult;
import top.wello.health.dao.UserDTO;
import top.wello.health.service.UserSessionService;
import top.wello.health.session.SessionHolder;

import javax.annotation.Resource;
import java.util.Arrays;

import static top.wello.health.Request.RestResult.ERROR_EXCEPTION;


@Aspect
@Component
@Lazy
public class SessionAspect {

    private Logger logger = LoggerFactory.getLogger(SessionAspect.class);
//    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Resource
    UserSessionService userSessionService;

    @Pointcut("execution(public * top.wello.health.controller.HealthController.*(..))")
    public void healthPoint() {}

    @Around("healthPoint()")
    public Object trackAround(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        String name = point.getSignature().getName();
        if (!"login".equals(name) && !"test".equals(name) && !"baiduToken".equals(name)) {
            initSession(point);
            if (SessionHolder.getUser() == null) {
                return RestResult.fail(RestResult.ERROR_INVAILD_SESSION, "invaild session");
            }
        }
        Object ret = null;
        try {
            ret = point.proceed();
        } catch (Exception e) {
            logger.error("catch error ! " + name);
            return RestResult.fail(ERROR_EXCEPTION, "uncaught exception");
        }
        logger.info("running method " + name + " cost " + (System.currentTimeMillis() - start) + "ms");
        logger.info(ret == null ? "ret null" : ret.toString());
        return ret;
    }

    private void initSession(ProceedingJoinPoint point) {
        Object[] args = point.getArgs();
        if (args == null) {
            return;
        }
        for (Object arg: args) {
            if (arg instanceof BaseRequest) {
                String session = ((BaseRequest) arg).getSession();
                UserDTO user = userSessionService.getUserBySession(session);
                SessionHolder.putUser(user);
                return;
            }
        }
    }
}
