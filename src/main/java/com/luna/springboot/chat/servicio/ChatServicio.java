package com.luna.springboot.chat.servicio;

import java.util.List;

import com.luna.springboot.chat.modelo.Mensaje;

/**
 * Servicio para obtener los mensajes
 * @author Hector
 *
 */
public interface ChatServicio {
	
	List<Mensaje> obtenerUltimos10Mensajes();
	Mensaje guardar(Mensaje mensaje);

}
