package donTouch.estate_server.estate.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import donTouch.estate_server.estate.domain.EstateFund;
import donTouch.estate_server.estate.domain.EstateFundDetail;
import donTouch.estate_server.estate.domain.EstateFundDetailJpaRepository;
import donTouch.estate_server.estate.domain.EstateFundJpaRepository;
import java.io.File;
import java.io.IOException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DbService {

    private final EstateFundJpaRepository estateFundJpaRepository;
    private final EstateFundDetailJpaRepository estateFundDetailJpaRepository;

    public String saveEstate(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


        try {
            List<EstateFund> estateFunds = objectMapper.readValue(
                new File("/Users/shin-uijin/Downloads/8percent.json"),
                objectMapper.getTypeFactory().constructCollectionType(List.class, EstateFund.class)
            );
            List<EstateFundDetail> estateFundDetails = objectMapper.readValue(
                new File("/Users/shin-uijin/Downloads/8percent.json"),
                objectMapper.getTypeFactory().constructCollectionType(List.class, EstateFundDetail.class)
            );

            for (int i = 0; i < estateFunds.size(); i++) {
                EstateFund product = estateFunds.get(i);
                EstateFundDetail detail = estateFundDetails.get(i);
                estateFundJpaRepository.save(product);
                estateFundDetailJpaRepository.save(detail);
            }
        } catch (
            IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
