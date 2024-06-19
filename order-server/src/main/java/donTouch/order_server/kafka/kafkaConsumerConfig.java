package donTouch.order_server.kafka;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.messaging.converter.StringMessageConverter;

@EnableKafka
@Configuration
public class kafkaConsumerConfig {
    private Environment env;
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public kafkaConsumerConfig(Environment env, KafkaTemplate<String, Object> kafkaTemplate) {
        this.env = env;
        this.kafkaTemplate = kafkaTemplate;
    }

    public ConsumerFactory<String, Object> consumerFactory() {
        Map<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
            env.getProperty("bootstrap.servers"));
        props.put(ConsumerConfig.GROUP_ID_CONFIG,
            "order_group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
            ErrorHandlingDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
            ErrorHandlingDeserializer.class);
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS,
            StringDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS,
            JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        props.put(JsonDeserializer.TYPE_MAPPINGS,
            "UsersDto:donTouch.order_server.kafka.dto.UsersDto,"
                + "BankCalculateForm:donTouch.order_server.holding.dto.BankCalculateForm,"
                + "IsSuccessDto:donTouch.order_server.kafka.dto.IsSuccessDto,"
                + "HoldingEstateFundForm:donTouch.order_server.holding.dto.HoldingEstateFundForm,"
                + "BankAccountLogDto:donTouch.order_server.kafka.dto.BankAccountLogDto"
        );

        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, LinkedHashMap.class); // 의진아 밥사라^_^
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
    @Bean
    public StringMessageConverter jsonConverter(){
        return new StringMessageConverter();
    }
}
