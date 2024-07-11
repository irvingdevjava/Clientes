package com.personal.irving.demo.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.personal.irving.demo.model.entity.Cliente;

public interface ClienteDao extends CrudRepository<Cliente, Integer> {

}
