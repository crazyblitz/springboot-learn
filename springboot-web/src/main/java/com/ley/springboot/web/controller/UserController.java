package com.ley.springboot.web.controller;

import com.ley.springboot.web.bean.User;
import com.ley.springboot.web.http.result.Result;
import com.ley.springboot.web.http.result.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "刘恩源", 20));
        users.add(new User(2, "刘恩源", 21));
        users.add(new User(3, "刘恩源", 22));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Result get(@PathVariable Integer id) {
        return ResultEntity.success("success", users.get(id - 1));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result delete(@PathVariable Integer id) {
        return ResultEntity.success("success", users.remove(id - 1));
    }

    @GetMapping("")
    @ResponseBody
    public Result list() {
        return ResultEntity.success("success", users);
    }


    @PostMapping("")
    @ResponseBody
    public Result add(User user) {
        return ResultEntity.success("success", users.add(user));
    }

    @PostMapping("/add1")
    public String add1(User user) {
        users.add(user);
        return "redirect:/api/user/";
    }

    @PutMapping("")
    @ResponseBody
    public Result update(@RequestParam String name, @RequestParam Integer age, @RequestParam Integer id) {
        User user = new User(id, name, age);
        int removeIndex = id - 1;
        users.remove(removeIndex);
        users.add(removeIndex, user);
        log.info("users: {}", users);
        return ResultEntity.success("success");
    }
}
