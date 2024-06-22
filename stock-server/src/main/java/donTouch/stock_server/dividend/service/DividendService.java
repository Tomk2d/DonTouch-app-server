package donTouch.stock_server.dividend.service;

import donTouch.stock_server.dividend.dto.DividendDTO;
import donTouch.stock_server.dividend.dto.DividendForm;
import donTouch.stock_server.web.dto.PurchaseInfoDTO;

import java.util.List;
import java.util.Map;

public interface DividendService {
    List<DividendDTO> findCalendar(DividendForm dividendForm, Map<String, List<PurchaseInfoDTO>> holdingPurchases);
}
