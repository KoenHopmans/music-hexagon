package com.novi.hexagon.controller;

import com.novi.hexagon.model.Producer;
import com.novi.hexagon.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins={"*"})
@RequestMapping("/api/v1/")
public class ProducerController {

    @Autowired
    private ProducerRepository producerRepository;


    //get producer
    @GetMapping("producers")
    public ResponseEntity<Object> getAllProducers(){
        List<Producer> AllProducers = this.producerRepository.findAll();
        return new ResponseEntity<>(AllProducers, HttpStatus.OK);
    }


    //get producer by Id
    @GetMapping("producers/{id}")
    public ResponseEntity<Object> getProducerById(@PathVariable("id") Long id){
        Optional<Producer> producer = this.producerRepository.findById(id);
        return new ResponseEntity<>(producer, HttpStatus.OK);
    }


    //save producer
    @PostMapping("producers")
    public ResponseEntity<Object> createProducer(@RequestBody Producer producer){
        this.producerRepository.save(producer);
        return new ResponseEntity<>("Producer is created successfully", HttpStatus.CREATED);
    }

    //delete producer by Id
    @DeleteMapping("producers/{id}")
    public ResponseEntity<Object> deleteProducerById(@PathVariable("id") Long id){
        this.producerRepository.deleteById(id);
        return new ResponseEntity<>("Producer is deleted successfully", HttpStatus.OK);
    }

    //update producer by Id
    @PutMapping("producers/{id}")
    public ResponseEntity<Object> updateProducerById(@PathVariable("id") Long id, @RequestBody Producer producer){
        Optional<Producer> oldProducer = this.producerRepository.findById(id);
        if (oldProducer.isPresent()) {
            Producer updatedProducer = oldProducer.get();
            updatedProducer.setFirstName(producer.getFirstName());
            updatedProducer.setFirstName(producer.getFirstName());
            updatedProducer.setLastName(producer.getLastName());
            updatedProducer.setEmail(producer.getEmail());
            producerRepository.save(updatedProducer);
            return new ResponseEntity<>("Producer is updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        }


}

