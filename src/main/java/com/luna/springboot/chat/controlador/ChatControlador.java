package com.luna.springboot.chat.controlador;

import java.util.Date;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.luna.springboot.chat.modelo.Mensaje;
import com.luna.springboot.chat.servicio.ChatServicio;


/**
 * Constrolador del chat
 * @author Hector
 *
 */
@Controller
public class ChatControlador {
	
	
	private String[] colores= {"red","green","blue","magenta","purple","orange"};
	

	/**
	 * Servicio del chat
	 */
	@Autowired
	private ChatServicio chatServicio;
	
	
	@Autowired
	private SimpMessagingTemplate webSocket;
	
	/**
	 * End point a donde a a llegar el mensaje
	 */
	@MessageMapping("/mensaje")
	@SendTo("/chat/mensaje")
	public Mensaje recibeMensaje(Mensaje mensaje) {
		
		mensaje.setFecha(new Date().getTime());
		
		if(mensaje.getTipo().equals("NUEVO_USUARIO")) {
			mensaje.setColor(colores[new Random().nextInt(colores.length) ]);
			mensaje.setTexto("nuevo usuario");
		}else {
			chatServicio.guardar(mensaje);
		}
		
		//mensaje.setTexto("Recibido por el broker: "+mensaje.getTexto());
		
		
		
		return mensaje;
	}
	
	/**
	 * Metodo para saber quien esta escribiendo
	 * @param username
	 * @return
	 */
	@MessageMapping("/escribiendo")
	@SendTo("/chat/escribiendo")
	public String escribiendo(String username) {
		
		
		return username.concat(" esta escribiendo ...");
	}
	
	
	/**
	 * Obtener historial(ultimos 10 mensajes del chat)
	 * @return
	 */
	@MessageMapping("/historial")
	public void historialMensajes(String clienteId) {
		webSocket.convertAndSend("/chat/historial/"+clienteId, chatServicio.obtenerUltimos10Mensajes());
	}
	

}
