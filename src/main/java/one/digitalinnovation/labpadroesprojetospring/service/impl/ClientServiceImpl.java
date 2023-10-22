package one.digitalinnovation.labpadroesprojetospring.service.impl;

import one.digitalinnovation.labpadroesprojetospring.model.Address;
import one.digitalinnovation.labpadroesprojetospring.model.AddressRepository;
import one.digitalinnovation.labpadroesprojetospring.model.Client;
import one.digitalinnovation.labpadroesprojetospring.model.ClientRepository;
import one.digitalinnovation.labpadroesprojetospring.service.ClientService;
import one.digitalinnovation.labpadroesprojetospring.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ViaCepService viaCepService;
    @Override
    public Iterable<Client> buscarTodos() {
        return clientRepository.findAll();
    }

    @Override
    public Client buscarPorId(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.get();
    }

    @Override
    public void inserir(Client cliente) {
        saveClientWithCep(cliente);
    }

    private void saveClientWithCep(Client cliente) {
        Address address = addressRepository.findById(cliente.getAddress().getCep()).orElseGet(() -> {
            Address novoAdress = viaCepService.consultarCep(cliente.getAddress().getCep());
            clientRepository.save(cliente);
            return null;
        });
        cliente.setAddress(address);
        clientRepository.save(cliente);
    }

    @Override
    public void atualizar(Long id, Client cliente) {
        Optional<Client> clientDb = clientRepository.findById(id);
        if (clientDb.isPresent()) {
            saveClientWithCep(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
        clientRepository.deleteById(id);
    }
}
