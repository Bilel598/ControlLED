package org.o7planning.hellospringboot.controller;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	public void envoi(String msg) throws MqttException {
		MqttClient client = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
		client.connect();
		MqttMessage message = new MqttMessage();
		message.setPayload(msg.getBytes());
		client.publish("iot_data", message);
	}
	
	@RequestMapping("/")
	public String home() {
		return "bienvenue";
	}

	@RequestMapping(path = "/allumer", method = RequestMethod.GET)
	public String allumer() throws MqttException  {
		envoi("ON");
		return "bienvenue";
	}
	@RequestMapping(path = "/eteindre", method = RequestMethod.GET)
	public String eteindre() throws MqttException {
		envoi("OFF");
		return "bienvenue";
	}
}