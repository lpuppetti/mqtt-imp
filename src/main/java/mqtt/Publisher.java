package mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

// Publica mensajes al broker por medio de MQTT
public class Publisher {
 
	public static final String BROKER_URL = "tcp://localhost:1883"; //Broker local
	public static final String TOPIC_TEMPERATURE = "sensor1/temperatura"; // Topico bajo el cual publica
	
    private MqttClient client;
    
    public Publisher(){ // Constructor se define el cliente MQTT

         String clientId = Utils.getMacAddress() + "-pub"; // Obtiene dir MAC de la pc que publica
         try
         {
              client = new MqttClient(BROKER_URL, clientId);

         }
         catch (MqttException e)
        {
             e.printStackTrace();
             System.exit(1);
         }
    }
    public void startPub() { //Conecta con el broker y comienza a publicar mensajes
    	try {
    		MqttConnectOptions options = new MqttConnectOptions();
    		options.setCleanSession(false);
        
    		client.connect(options);
    		
    		while(true) {
    			publishTemp();  //Publica temperatura cada 5 seg
    			Thread.sleep(5000);
    		}
    	}catch(MqttException e) {
    		e.printStackTrace();
    	} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    private void publishTemp() throws MqttException { //Publica temperatura generando un numero aleatoreo
    	MqttTopic topic = client.getTopic(TOPIC_TEMPERATURE);
    	
    	String temp = Utils.createRandomNumberBetween(15, 25) + "°C";
    	
    	topic.publish(new MqttMessage(temp.getBytes())); //Publica mensaje transformandolo en una secuencia de Bytes
    }
    
	public static void main(String[] args) {
		Publisher publisher = new Publisher();
		publisher.startPub();
    }
    
}