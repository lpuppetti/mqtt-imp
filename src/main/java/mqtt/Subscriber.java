package mqtt;

import java.util.Scanner;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

//Se suscribe a un topic y recibe los mensajes publicados
public class Subscriber {
	
	public static final String BROKER_URL = "tcp://localhost:1883";
	
	String clientId = Utils.getMacAddress() + "-sub";
	private MqttClient client;
	
	public Subscriber() {  // Constructor se define el cliente MQTT
		
		try {
			client = new MqttClient(BROKER_URL, clientId);
			
		}catch(MqttException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void startSub() { //Conecta con el Broker y se suscribe a un topico
		
		try {
			client.setCallback(new SubscriberCallback());
			client.connect();
			System.out.println("Conexion establecida con Broker");
			
    		System.out.println("Ingrese topic para suscribirse: ");
    		Scanner scanner = new Scanner(System.in);
    		String topic = scanner.nextLine();
    		
    		client.subscribe(topic);
    		System.out.println("Suscrito a topic: " + topic);
    		
		}catch(MqttException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static void main(String[] args) {
		Subscriber subscriber = new Subscriber();
		subscriber.startSub();

	}

}
