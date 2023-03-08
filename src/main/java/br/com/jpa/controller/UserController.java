package br.com.jpa.controller;

import br.com.jpa.entities.User;
import br.com.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/page")
    public ResponseEntity<Page<User>> findAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(pageable));
    }

    @GetMapping(value = "/search-salary")
    public ResponseEntity<Page<User>> searchBySalary(
            @RequestParam(defaultValue = "0") Double minSalary,
            @RequestParam(defaultValue = "50000000") Double maxSalary, Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findBySalaryBetween(minSalary, maxSalary, pageable));
    }

    @GetMapping(value = "/search-name")
    public ResponseEntity<Page<User>> searchByName(@RequestParam(defaultValue = "") String name, Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByName(name, pageable));
    }

}
