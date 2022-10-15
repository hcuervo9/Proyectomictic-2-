package com.example.proyectomictic.service;


import com.example.proyectomictic.entities.Client;
import com.example.proyectomictic.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {
        return clientRepository.getAll();
        //return  (List<Client>) clientRepository.getAll();
    }
    public Optional<Client> getClient(int id) {
        return clientRepository.getClient(id);}

    public Client save(Client client) {
        if (client.getIdClient() == null) {
            return clientRepository.save(client);
        } else {
            Optional<Client> clientGuardado = getClient(client.getIdClient());
           // Optional<Client> clientEncontrado = clientRepository.getClient(client.getIdClient());
            if (clientGuardado.isEmpty()) {

                return clientRepository.save(client);
            } else {
                return client;
            }
        }
    }

    //Metodo Actualizar
    public Client update(Client client) {
        if (client.getIdClient() != null) {
            Optional<Client> clientEncontrado = getClient(client.getIdClient());
            //Optional<Client> clientEncontrado = clientRepository.getClient(client.getIdClient());
            if (!clientEncontrado.isEmpty()) {
                if (client.getName() != null) {
                    clientEncontrado.get().setName(client.getName());
                }

                if (client.getEmail() != null) {
                    clientEncontrado.get().setEmail(client.getEmail());
                }
                if (client.getPassword() != null) {
                    clientEncontrado.get().setPassword(client.getPassword());
                }

                if (client.getAge() != null) {
                    clientEncontrado.get().setAge(client.getAge());
                }


                return clientRepository.save(clientEncontrado.get());
            }
        }
        return client;
    }

    public boolean deleteClient(int id){
        Boolean resultado= getClient(id).map(elementoaeliminar ->{
            clientRepository.delete(elementoaeliminar);
            return  true;
        }).orElse(false);
        return resultado;
    }
    public Optional<Client> getClientId(int id){
        return clientRepository.getClient(id);
    }

/*

    public boolean deleteClient(int clientId) {
        Boolean resultado = getClient(clientId).map(ClientPorEliminar -> {
            clientRepository.delete(ClientPorEliminar);
            return true;
        }).orElse(false);
        return resultado;
    }

 */




}






