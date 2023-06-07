package com.nineya.haloplus.controller.content.api;

import com.nineya.haloplus.model.dto.UserDTO;
import com.nineya.haloplus.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Content user controller.
 *
 * @author johnniang
 * @date 2019-04-03
 */
@RestController("ApiContentUserController")
@RequestMapping("/api/content/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("profile")
    @ApiOperation("Gets blogger profile")
    public UserDTO getProfile() {
        return userService.getCurrentUser().map(user -> (UserDTO) new UserDTO().convertFrom(user))
            .get();
    }
}
