package hello;

import org.springframework.stereotype.Service;

@Service
public interface HelloService {
    String sayHello(String name);
}
