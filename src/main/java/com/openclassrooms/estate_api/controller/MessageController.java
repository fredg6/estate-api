package com.openclassrooms.estate_api.controller;

import com.openclassrooms.estate_api.model.dto.EmptyDto;
import com.openclassrooms.estate_api.model.dto.MessageDto;
import com.openclassrooms.estate_api.model.dto.ResponseDto;
import com.openclassrooms.estate_api.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class MessageController implements MessageApi {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    @PostMapping(value = "/messages")
    public ResponseEntity<Object> send(@RequestBody MessageDto messageDto) {
        if (messageDto.message() == null || messageDto.userId() == null || messageDto.rentalId() == null) {
            return ResponseEntity.badRequest().body(new EmptyDto());
        }
        messageService.send(messageDto);
        return ResponseEntity.ok(new ResponseDto("Message sent with success !"));
    }
}