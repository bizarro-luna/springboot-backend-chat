package com.luna.springboot.chat.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.luna.springboot.chat.modelo.Mensaje;


/**
 * Repositorio de {@link Mensaje}
 * @author Hector
 *
 */
public interface ChatRepository  extends MongoRepository<Mensaje, String> {
	
	List<Mensaje> findFirst10ByOrderByFechaDesc();

}
