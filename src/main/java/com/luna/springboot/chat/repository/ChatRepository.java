package com.luna.springboot.chat.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.luna.springboot.chat.modelo.Mensaje;

public interface ChatRepository  extends MongoRepository<Mensaje, String> {
	
	List<Mensaje> findFirst10ByOrderByFechaDesc();

}
