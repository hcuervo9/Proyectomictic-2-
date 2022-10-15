package com.example.proyectomictic.service;

import com.example.proyectomictic.entities.Message;
import com.example.proyectomictic.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    public List<Message> getAll(){
        return messageRepository.getAll();

       // return (List<Message>) messageRepository.getAll();
    }
    public Optional<Message> getMessage(int id) {

        return messageRepository.getMessage(id);
    }

    public Message save(Message m){
        if (m.getIdMessage() == null){
            return messageRepository.save(m);
        } else {
            Optional<Message> messageEncontrado = getMessage(m.getIdMessage());
           // Optional<Message> messageEncontrado = messageRepository.getMessage(message.getIdMessage());
            if (messageEncontrado.isEmpty()){
                return messageRepository.save(m);
            }else {
                return m;
            }
        }
    }
    public Message update(Message m){
        if (m.getIdMessage() != null){
            Optional<Message> messageEncontrado = getMessage(m.getIdMessage());
           // Optional<Message> messageEncontrado = messageRepository.getMessage(m.getIdMessage());
            if (!messageEncontrado.isEmpty()) {
                if (m.getMessageText() != null) {
                    messageEncontrado.get().setMessageText(m.getMessageText());
                }

                return messageRepository.save(messageEncontrado.get());
            }
        }
        return m;
    }

    public boolean deleteMessage(int id){
        boolean resultado = getMessage(id).map(messageporeliminar ->{
            messageRepository.delete(messageporeliminar);
            return true;
        }).orElse(false);
        return resultado;
    }
    public Optional<Message> getMessageId(int id){
        return messageRepository.getMessage(id);}

}
/*
    public boolean deleteMessage(int messageId){
        Boolean resultado = getMessage(messageId).map(messagePorEliminar ->{
            messageRepository.delete(messagePorEliminar);
            return true;
        }).orElse(false);
        return resultado;
    }*/