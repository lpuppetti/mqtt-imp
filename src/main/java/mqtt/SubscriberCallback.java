package mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class SubscriberCallback implements MqttCallback {

	public void connectionLost(Throwable arg0) {
		// TODO Auto-generated method stub
		
	}

	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub
		
	}
	//Cuando llega un mensaje lo muestra por pantalla
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("Mensaje recibido - " + topic + ":" + message.toString());
		
	}

}
