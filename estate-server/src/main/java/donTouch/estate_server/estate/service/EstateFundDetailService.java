package donTouch.estate_server.estate.service;

import donTouch.estate_server.estate.domain.EstateFundDetail;
import donTouch.estate_server.estate.dto.EstateFundDetailDto;

public interface EstateFundDetailService {

    EstateFundDetail findEstateInfoById(int id);
}
