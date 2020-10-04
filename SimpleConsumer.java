import java.util.Properties;
import java.util.Collections;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class SimpleConsumer {
    public static void main(String[] args) throws Exception {
        System.out.println("Test project");

        //check arguments length. Return if not topic was entered
        if (args.length == 0) {
            System.out.println("Enter topic name");
            return;
        }

        //assign topic name to string
        String topicName = args[0].toString(); 

        //create instance of properties to access consumer config
        Properties props = new Properties();

        //assign bootstrap server
        props.put("bootstrap.servers", "localhost:9092");

        props.put("group.id", "MyGroup");

        props.put("key.deserializer", 
                  "org.apache.kafka.common.serialization.StringDeserializer");

        props.put("value.deserializer", 
                  "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new 
            KafkaConsumer<String, String>(props);
        
        consumer.subscribe(Collections.singletonList("progress"));

        try {
            while(true) {
                ConsumerRecord<String, String> records = consumer.poll(100);
                for(ConsumerRecord<String, String> record : records)
                {
                    String message = "topic = " + record.topic() + 
                        " partition = " + record.partition() +
                        " offset = " + record.offset() +
                        " key = " + record.key() +
                        " value = " + record.value();

                    int updatedCount = 1;

                    if(custCountryMap.containsValue(record.value())) {
                        updatedCount = custCountryMap.get(record.value()) + 1;
                    }
                    custCountryMap.put(record.value(), updatedCount);

                    JSONObject json = new JSONObject(custCountryMap);
                    System.out.println(json.toString(4));

                    System.out.println(message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}



