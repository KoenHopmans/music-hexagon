package com.novi.hexagon.repository;

import com.novi.hexagon.model.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface DemoRepository extends JpaRepository<Demo, String> {
    boolean existsByDemo(String filename);

    @Transactional
    void deleteByDemo(String filename);

    Optional<Demo> findByDemo(String filename);

}
