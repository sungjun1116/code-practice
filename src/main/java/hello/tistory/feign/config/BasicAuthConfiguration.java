package hello.tistory.feign.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * feign client 용 configuration class
 *
 * @Configuration 을 사용을 하게 되면 feign client 별로 설정하지 못하고, 모든 feign client 에 적용되므로 사용하지 않음
 */
public class BasicAuthConfiguration {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user", "1234567890");
    }
}
