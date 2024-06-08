package donTouch.user_server.user;

import donTouch.user_server.kafka.service.KafkaService;
import donTouch.user_server.user.domain.Users;
import donTouch.user_server.user.dto.UsersDto;
import donTouch.user_server.user.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/user/{email}")
    public UsersDto getUser(@PathVariable String email) {
        try {
            return userService.findUserByEmail(email);
        }catch(NullPointerException e) {
            return null;
        }
    }
}
