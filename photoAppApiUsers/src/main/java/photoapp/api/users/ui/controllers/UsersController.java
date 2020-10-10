package photoapp.api.users.ui.controllers;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import photoapp.api.users.ui.data.UserEntity;
import photoapp.api.users.ui.dto.UserDto;
import photoapp.api.users.ui.exceptions.UsersNotFoundException;
import photoapp.api.users.ui.models.UserCreateResponse;
import photoapp.api.users.ui.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {
    private UserService userService;
    private Environment env;

    @Autowired
    public UsersController(UserService userService, Environment env) {
        this.userService = userService;
        this.env = env;
    }

    @GetMapping("/status/check")
    public String getStatus() {
        return "Working on port " + env.getProperty("server.port") + ", " + env.getProperty("token.secret");
    }

    @GetMapping
    public ResponseEntity<Iterable<UserEntity>> getUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserCreateResponse> create(@Valid @RequestBody UserDto userDto) {
        if (userDto == null) {
            throw new UsersNotFoundException();
        }
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDtoCreated = userService.create(userDto);
        UserCreateResponse userCreateResponse = modelMapper.map(userDtoCreated, UserCreateResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(userCreateResponse);
    }
}
