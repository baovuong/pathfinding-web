package com.vuongideas.pathfinding.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/graph")
public class GraphRestController {

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String test(@PathVariable String name) {
        String result = "Hello " + name;
        return result;
    }
}
