package com.jjdev.demo.controller;

import com.jjdev.demo.dto.JUserDto;
import com.jjdev.demo.response.JResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin(origins = "*")
public class JUserController {

    private JUserDto localUserDto;
    private static final Logger log = LoggerFactory.getLogger(JUserController.class);

    @PostMapping
    public ResponseEntity<JResponse<JUserDto>> create(@Valid @RequestBody JUserDto userDto, BindingResult result) {
        JResponse<JUserDto> response = new JResponse<>();

        if (result.hasErrors()) {
            log.info("Validation erros: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        localUserDto = userDto;

        response.setData(userDto);
        return ResponseEntity.ok(response);
    }


    @GetMapping
    public ResponseEntity<JResponse<JUserDto>> read() {
        JResponse<JUserDto> response = new JResponse<>();
        response.setData(localUserDto);
        return ResponseEntity.ok(response);
    }

}
