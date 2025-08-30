package com.openclassrooms.estate_api.service;

import com.openclassrooms.estate_api.model.Message;
import com.openclassrooms.estate_api.model.dto.MessageDto;
import com.openclassrooms.estate_api.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final UserService userService;
    private final RentalService rentalService;
    private final MessageRepository messageRepository;

    public MessageService(UserService userService,
                          RentalService rentalService,
                          MessageRepository messageRepository) {
        this.userService = userService;
        this.rentalService = rentalService;
        this.messageRepository = messageRepository;
    }

    public Message send(MessageDto messageDto) {
        var sender = userService.getById(messageDto.userId());
        var rental = rentalService.getById(messageDto.rentalId());
        return messageRepository.save(new Message(messageDto.message(), rental, sender));
    }
}