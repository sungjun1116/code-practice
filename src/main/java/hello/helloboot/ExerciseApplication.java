package hello.helloboot;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class ExerciseApplication {
    private final JdbcTemplate jdbcTemplate;

    public ExerciseApplication(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    ApplicationRunner run(ConditionEvaluationReport report) {;
        return args -> report.getConditionAndOutcomesBySource().entrySet().stream()
                .filter(co -> co.getValue().isFullMatch())
                .filter(co -> !co.getKey().contains("Jmx"))
                .forEach(co -> {
                    System.out.println(co.getKey());
                    co.getValue().forEach(c -> System.out.println("\t" +  c));
                    System.out.println();
                });
    }

    @PostConstruct
    void init() {
        jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
    }

    public static void main(String[] args) {
        SpringApplication.run(ExerciseApplication.class, args);
    }

}
