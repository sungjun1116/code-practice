package hello.helloboot;

import org.springframework.stereotype.Service;

@Service
public interface HelloService {
    String sayHello(String name);

    default int contOf(String name){
        return 0;
    };
}
