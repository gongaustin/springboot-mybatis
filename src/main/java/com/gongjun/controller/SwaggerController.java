package com.gongjun.controller;

import com.gongjun.model.User;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zl on 2015/8/27.
 */
@RestController
@RequestMapping(value="/users")
public class SwaggerController extends BaseController {


    /*
     *  http://localhost:8080/swagger/index.html
     */

    /**
     *
     * @return
     */
    @ApiOperation(value="Get all users",notes="requires noting")
    @GetMapping
    public List<User> getUsers(){
        List<User> list=new ArrayList<User>();

        User user=new User();
        user.setName("hello");
        list.add(user);

        User user2=new User();
        user.setName("world");
        list.add(user2);
        return list;
    }

    @ApiOperation(value="Get user with id",notes="requires the id of user")
    @GetMapping(value = "${my.name}")
    public User getUserById(@PathVariable String name){
        User user=new User();
        user.setName("hello world");
        return user;
    }
}
