package com.ley.springboot.web.flux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * 类描述: web flux(响应式编程)
 *
 * @author liuenyuan
 * @date 2019/5/10 14:15
 * @describe
 */
@SpringBootApplication
public class WebFluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebFluxApplication.class, args);
    }


    @Bean
    public RouterFunction<ServerResponse> helloWorld() {
        return route(GET("/hello-world"), request -> ok()
                .body(Mono.just("Hello,World"), String.class));
    }
}
