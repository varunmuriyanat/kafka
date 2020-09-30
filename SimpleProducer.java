import java.util.Properties;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class SimpleProducer {
    public static void main(String[] args) throws Exception {
        System.out.println("Test project");

        //check arguments length. Return if not topic was entered
        if (args.length == 0) {
            System.out.println("Enter topic name");
            return;
        }

        //assign topic name to string
        String topicName = args[0].toString(); 

        //create instance of properties to access producer config
        Properties props = new Properties();

        //assign bootstrap server
        props.put("bootstrap.servers", "localhost:9092");

        //set acknowledgements for producer requests
        props.put("acks", "all");

        props.put("key.serializer", 
                  "org.apache.kafka.common.serialization.StringSerializer");

        props.put("value.serializer", 
                  "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);

        producer.send(new ProducerRecord<String, String>(topicName, 
                                                         "testKey",
                                                         "Are you there Watson?"));

        producer.close();

    }
}
