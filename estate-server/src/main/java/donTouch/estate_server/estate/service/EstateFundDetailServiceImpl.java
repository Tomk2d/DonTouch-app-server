package donTouch.estate_server.estate.service;

import donTouch.estate_server.estate.domain.EstateFundDetail;
import donTouch.estate_server.estate.domain.EstateFundDetailJpaRepository;
import donTouch.estate_server.estate.dto.EstateFundDetailDto;
import donTouch.estate_server.estate.utils.EstateFundMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EstateFundDetailServiceImpl implements EstateFundDetailService {
    private final EstateFundDetailJpaRepository estateFundDetailRepository;

    @Override
    public EstateFundDetailDto findEstateInfoById(int id) {
       EstateFundDetailDto result = estateFundDetailRepository.findEstateInfoById(id);
       if (result == null) {
           throw new NullPointerException("EstateFundDetail with id " + id + " not found");
       }
       return result;
    }
}
