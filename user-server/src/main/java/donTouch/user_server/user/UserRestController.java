package donTouch.user_server.user;

import donTouch.user_server.user.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class UserRestController {
    private final UserServiceImpl userService;

    @GetMapping("/test")
    public void test() {
        try{
            userService.findUserByEmail();
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

}
