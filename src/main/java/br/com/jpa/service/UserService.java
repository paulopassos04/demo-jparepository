package br.com.jpa.service;

import br.com.jpa.entities.User;
import br.com.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<User> findAll(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    public Page<User> findBySalaryBetween(Double minSalary, Double maxSalary, Pageable pageable){
        return userRepository.searchBySalary(minSalary, maxSalary, pageable);
    }

    public Page<User> findByName(String name, Pageable pageable){
        return userRepository.searchByName(name, pageable);
    }
}
