package hello.feign;

import hello.feign.config.FeignRetryConfiguration;
import hello.feign.config.HeaderConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "example", url = "http://localhost:8080", configuration = {HeaderConfiguration.class, FeignRetryConfiguration.class})
public interface ExampleClient {

    @GetMapping("/{status}")
    void status(@PathVariable(value = "status") int status);

}
