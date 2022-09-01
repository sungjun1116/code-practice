package hello.tistory.feign.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;

/**
 * feign client 용 configuration class
 *
 * @Configuration 을 사용을 하게 되면 feign client 별로 설정하지 못하고, 모든 feign client 에 적용되므로 사용하지 않음
 */
public class FeignRetryConfiguration {

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(1000, 2000, 3);
    }
}
