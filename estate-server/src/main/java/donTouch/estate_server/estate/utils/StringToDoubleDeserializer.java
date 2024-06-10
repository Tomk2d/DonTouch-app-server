package donTouch.estate_server.estate.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public class StringToDoubleDeserializer extends JsonDeserializer<Double> {
    @Override
    public Double deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
        throws IOException, JsonProcessingException {
        String value = jsonParser.getText();
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0; // 기본값 설정 (예: 0.0)
        }
    }
}
