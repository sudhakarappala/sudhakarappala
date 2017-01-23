package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Contoller
{
    @Autowired
    RabbitmqProducerService service;

    @GetMapping(value = "/test/{str}")
    public ResponseEntity<String> produce(@PathVariable String str)
    {
        for (int i = 1; i <=10; i++)
        {
            service.send(str+i);
        }
        
        return new ResponseEntity<String>("Message sent or queue",
            HttpStatus.OK);
    }
}
