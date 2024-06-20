package donTouch.estate_server.estate.service;

import donTouch.estate_server.estate.dto.BuyEstateFundForm;
import donTouch.estate_server.estate.dto.EstateFundDto;

import java.util.List;

public interface EstateFundService {

    List<EstateFundDto> getAllEstateFund();

    Boolean buyEstateFund(BuyEstateFundForm buyEstateFundForm);

    Boolean sellEstateFund(BuyEstateFundForm buyEstateFundForm);

    List<EstateFundDto> getEstateDtoList(List<Integer> ids);
}
