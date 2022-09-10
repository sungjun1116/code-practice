package hello.feign.config;

import hello.ExerciseApplication;
import hello.feign.model.FeignErrorDecode;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;

/**
 * feign client 용 전역 configuration class
 *
 * <p>{@link org.springframework.cloud.openfeign.FeignClientsConfiguration} 에서 어떤 Bean 들이 자동으로 등록되는지 확인 가능
 */
@EnableFeignClients(basePackageClasses = ExerciseApplication.class)
@Configuration
public class FeignConfiguration {

    /**
     * RequestParam 에서 LocalDate, LocalDateTime, LocalTime 을 사용을 할 때 ISO formatter 로 보내기 위한 설정
     *
     * @see org.springframework.web.bind.annotation.RequestParam
     */
    @Bean
    public FeignFormatterRegistrar localDateFeignFormatterRegister() {
        return registry -> {
            DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
            registrar.setUseIsoFormat(true);
            registrar.registerFormatters(registry);
        };
    }

    @Bean
    public FeignErrorDecode decoder() {
        return new FeignErrorDecode();
    }

}
