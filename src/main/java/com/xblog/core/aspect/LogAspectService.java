package com.xblog.core.aspect;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @ClassName LogAspectService
 * @Description: Aop 业务层统一日志打印
 * @Author caobing
 * @Date 2020/3/19
 * @Version V1.0
 **/
@Aspect
@Component
@Slf4j
public class LogAspectService {

    private JSONObject jsonObject = new JSONObject();

    // 申明一个切点 里面是 execution表达式
    @Pointcut("execution(public * com.xblog.service.*.*(..))")
    private void executAspect(){
    }

    // 请求method前打印内容
    @Before(value = "executAspect()")
    public void methodBefore(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info(">>>>>>>>>>>>>>> 请求内容-Start >>>>>>>>>>>>>>>");
        try {
            // 打印请求内容
            log.info("请求地址:" + request.getRequestURL().toString());
            log.info("请求方式:" + request.getMethod());
            log.info("请求类方法:" + joinPoint.getSignature());
            log.info("请求类方法参数:" + Arrays.toString(joinPoint.getArgs()));
        } catch (Exception e) {
            log.error("###LogAspectServiceApi.class methodBefore() ### ERROR:", e);
        }
        log.info(">>>>>>>>>>>>>>> 请求内容-End >>>>>>>>>>>>>>>");
    }

    // 在方法执行完结后打印返回内容
    @AfterReturning(returning = "obj", pointcut = "executAspect()")
    public void methodAfterReturing(Object obj) {
        log.info("<<<<<<<<<<<<<<< 返回内容-Start <<<<<<<<<<<<<<<");
        try {
            log.info("Response内容:" + jsonObject.toJSONString(obj));
        } catch (Exception e) {
            log.error("###LogAspectService.class methodAfterReturing() ### ERROR:", e);
        }
        log.info("<<<<<<<<<<<<<<< 返回内容-End <<<<<<<<<<<<<<<");
    }
}
