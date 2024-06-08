package donTouch.user_server.user;

import donTouch.user_server.kafka.service.KafkaService;
import donTouch.user_server.user.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class UserRestController {
    private final UserServiceImpl userService;
    private final KafkaService kafkaService;

    @GetMapping("/test")
    public void test() {
        kafkaService.sendResponse();
    }
}
