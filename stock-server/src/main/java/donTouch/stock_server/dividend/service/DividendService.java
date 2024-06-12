package donTouch.stock_server.dividend.service;

import donTouch.stock_server.dividend.dto.DividendDTO;
import donTouch.stock_server.dividend.dto.DividendForm;

import java.util.List;

public interface DividendService {
    List<DividendDTO> findCalendar(DividendForm dividendForm);
}
