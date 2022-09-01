package hello.tistory.feign.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * feign client 용 configuration class
 *
 * @Configuration 을 사용을 하게 되면 feign client 별로 설정하지 못하고, 모든 feign client 에 적용되므로 사용하지 않음
 */
public class HeaderConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }
}
