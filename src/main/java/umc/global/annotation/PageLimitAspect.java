package umc.global.annotation;


import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.global.apiPayload.code.PageLimitErrorCode;
import umc.global.apiPayload.exception.PageLimitException;

@Aspect
@Component
@RequiredArgsConstructor
public class PageLimitAspect {


    @Around("@annotation(PageLimit)")
    public Object validatePageSize(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();

        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Pageable pageable) {
                int size = pageable.getPageSize();


                if (size < 1) {
                    throw new PageLimitException(PageLimitErrorCode.PAGE_LIMIT_ERROR_CODE);
                }
            }
        }

        return pjp.proceed(args);
    }

}
