package donTouch.estate_server.estate.service;

import donTouch.estate_server.estate.dto.EstateFundDetailDto;

public interface EstateFundDetailService {

    EstateFundDetailDto findEstateInfoById(int id);
}
