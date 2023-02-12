package hello.helloboot;

import org.springframework.stereotype.Component;

@Component
public class SimpleHelloService implements HelloService {
    private final HelloRepository helloRepository;

    public SimpleHelloService(final HelloRepository helloRepository) {
        this.helloRepository = helloRepository;
    }

    @Override
    public String sayHello(String name) {
        helloRepository.increaseCount(name);

        return "Hello " + name;
    }

    @Override
    public int contOf(final String name) {
        return helloRepository.countOf(name);
    }
}
