package cl.corellana.demouser.adapters.web;

import cl.corellana.demouser.adapters.web.dto.UserResponseDTO;
import cl.corellana.demouser.adapters.web.exceptions.BusinessException;
import cl.corellana.demouser.application.port.in.LoginPortIn;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/login")
@Validated
@RequiredArgsConstructor
public class LoginController {

    private final LoginPortIn loginPortIn;

    @ApiOperation(value = "Authenticate a user", nickname = "loginPost", notes = "", response = UserResponseDTO.class, authorizations = {
            @Authorization(value = "BearerAuth")
    }, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User authenticated successfully", response = UserResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = "",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<UserResponseDTO> loginPost(@ApiParam(value = "accessToken") @RequestHeader("Authorization") @Valid String accessToken) throws BusinessException {
        return ResponseEntity.ok(loginPortIn.execute(accessToken));
    }
}
