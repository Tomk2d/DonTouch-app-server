package donTouch.estate_server.estate;

import donTouch.estate_server.estate.service.DbService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class EstateRestController {
    private final DbService dbService;

    @GetMapping("/saveEstate")
    public String saveEstate() {
        return dbService.saveEstate();
    }
}
