package com.luna.springboot.chat.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luna.springboot.chat.modelo.Mensaje;
import com.luna.springboot.chat.repository.ChatRepository;
import com.luna.springboot.chat.servicio.ChatServicio;


@Service
public class ChatServicioImpl implements ChatServicio{

	@Autowired
	private ChatRepository chatRepository;
	
	@Override
	public List<Mensaje> obtenerUltimos10Mensajes() {
		return chatRepository.findFirst10ByOrderByFechaDesc();
	}

	@Override
	public Mensaje guardar(Mensaje mensaje) {
		return chatRepository.save(mensaje);
	}

}
