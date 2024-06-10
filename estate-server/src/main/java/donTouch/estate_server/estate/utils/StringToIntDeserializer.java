package donTouch.estate_server.estate.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class StringToIntDeserializer extends JsonDeserializer<Integer> {
    @Override
    public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
        throws IOException, JsonProcessingException {
        String value = jsonParser.getText();
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0; // 기본값 설정 (예: 0)
        }
    }
}
