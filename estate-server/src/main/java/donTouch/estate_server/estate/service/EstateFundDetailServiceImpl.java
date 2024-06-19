package donTouch.estate_server.estate.service;

import donTouch.estate_server.estate.domain.EstateFundDetail;
import donTouch.estate_server.estate.domain.EstateFundDetailJpaRepository;
import donTouch.estate_server.estate.dto.EstateFundDetailDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EstateFundDetailServiceImpl implements EstateFundDetailService {
    private final EstateFundDetailJpaRepository estateFundDetailRepository;

    @Override
    public EstateFundDetail findEstateInfoById(int id) {
       EstateFundDetail result = estateFundDetailRepository.findByEstateId(id);
       if (result == null) {
           throw new NullPointerException("EstateFundDetail with id " + id + " not found");
       }
       return result;
    }
}
