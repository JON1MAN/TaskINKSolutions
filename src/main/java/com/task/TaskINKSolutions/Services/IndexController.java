package com.task.TaskINKSolutions.Services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class IndexController {

    @GetMapping("/")
    public ResponseEntity<String> index(){
        return ResponseEntity.ok("Cool");
    }

}
