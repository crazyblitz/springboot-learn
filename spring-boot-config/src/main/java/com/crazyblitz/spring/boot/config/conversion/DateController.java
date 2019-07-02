package com.crazyblitz.spring.boot.config.conversion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author liuenyuan
 **/
@RestController
@Slf4j
public class DateController {

    @PostMapping("/saveOrder")
    public Order saveOrder(Order order) {
        log.info("receive parameter: {}", order);
        return order;
    }


    @GetMapping("/users")
    public List<String> getUserNames(String[] names){
        return Collections.unmodifiableList(Arrays.asList(names));
    }
}
