package cl.corellana.demouser.adapters.web;

import cl.corellana.demouser.adapters.web.dto.UserDTO;
import cl.corellana.demouser.adapters.web.dto.UserResponseDTO;
import cl.corellana.demouser.adapters.web.exceptions.BusinessException;
import cl.corellana.demouser.application.port.in.SingUpPortIn;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/sign-up")
@Validated
@RequiredArgsConstructor
public class SingUpController {

    private final SingUpPortIn singUpPortIn;

    @ApiOperation(value = "Register a user", nickname = "signUpPost", notes = "", response = UserResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User registered successfully", response = UserResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    @RequestMapping(value = "",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<UserResponseDTO> signUpPost(@ApiParam(value = "" ,required=true ) @Valid @RequestBody UserDTO singUp) throws BusinessException {
        return new ResponseEntity<>(singUpPortIn.execute(singUp), HttpStatus.CREATED);
    }
}
