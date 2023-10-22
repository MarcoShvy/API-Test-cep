package one.digitalinnovation.labpadroesprojetospring.controller;

import one.digitalinnovation.labpadroesprojetospring.model.Client;
import one.digitalinnovation.labpadroesprojetospring.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.buscarPorId(id));
    }
    @PostMapping
    public ResponseEntity<Client> insert(@RequestBody Client client) {
        clientService.inserir(client);
        return ResponseEntity.ok(client);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, Client client) {
        clientService.atualizar(id, client);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> delete(@PathVariable Long id) {
        clientService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
