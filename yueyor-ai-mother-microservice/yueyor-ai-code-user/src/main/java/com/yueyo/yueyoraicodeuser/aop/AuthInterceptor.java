package com.yueyo.yueyoraicodeuser.aop;


import com.yueyo.yueyoraicodemother.annotation.AuthCheck;
import com.yueyo.yueyoraicodemother.exception.BusinessException;
import com.yueyo.yueyoraicodemother.exception.ErrorCode;
import com.yueyo.yueyoraicodemother.model.entity.User;
import com.yueyo.yueyoraicodemother.model.enums.UserRoleEnum;
import com.yueyo.yueyoraicodeuser.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class AuthInterceptor {


    @Resource
    private UserService userService;


    @Around("@annotation(authCheck)") //含义：拦截所有使用了AuthCheck注解的方法
    public Object doInterceptor(ProceedingJoinPoint proceedingJoinPoint, AuthCheck authCheck) throws Throwable{
        String mustRole = authCheck.mustRole();
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request =((ServletRequestAttributes) requestAttributes).getRequest();

        //获取当前接口所需的权限
        UserRoleEnum mustRoleEnum = UserRoleEnum.getEnumByValue(mustRole);

        // 不需要权限，放行
        if(mustRoleEnum == null) {
            return proceedingJoinPoint.proceed();
        }

        // 判断用户权限
        User loginUser = userService.getLoginUser(request);
        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByValue(loginUser.getUserRole());

        // 用户无任何权限
        if(userRoleEnum == null) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }

        // 要求管理员权限，用户无管理员权限
        if(UserRoleEnum.ADMIN.equals(mustRoleEnum) && !UserRoleEnum.ADMIN.equals(userRoleEnum)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }

        return proceedingJoinPoint.proceed();
    }
}
