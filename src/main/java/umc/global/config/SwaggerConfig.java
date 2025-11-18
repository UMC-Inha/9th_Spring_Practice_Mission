package umc.global.config;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI swagger(){
        Info info = new Info().title("Project").description("Project Swagger").version("0.0.1");
        //API 명세서의 표지 만드는 부분
        //title: API 문서 제목
        //description: API 문서의 상세 설명
        //version: API의 버전

        //JWT 토큰 헤더 방식
        String securityScheme = "JWT token";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(securityScheme);
        //이 인증을 적용하라고 명령하는 부분
        //이 코드는 이 API 문서의 모든 API는 기본적으로 JWT token 인증 방식이 필요하다고 전역 설정
        //이렇게 하면 각 API 옆에 자물쇠 아이콘이 생김

        //스웨거 오른쪽 상단에 Authorize 버튼 만드는 코드
        Components components = new Components()
                .addSecuritySchemes(securityScheme,new SecurityScheme()
                        .name(securityScheme)
                        .type(SecurityScheme.Type.HTTP) //인증 방식이 HTTP
                        .scheme("Bearer") //Bearer 토큰 방식을 사용한다고 명시
                        .bearerFormat("JWT")); //이 Bearer 토큰의 형식이 JWT라고 알려줌

        //위에서 만든 모든 설정을 합쳐서 OpenAPI라는 최종 객체를 만듦
        return new OpenAPI()
                .info(info)
                .addServersItem(new Server().url("/"))
                .addSecurityItem(securityRequirement) //전역 인증 적용 규칙 추가
                .components(components);//Authorize 버튼/인증 정의를 추가
    }
}
