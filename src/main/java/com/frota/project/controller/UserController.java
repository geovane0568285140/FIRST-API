package com.frota.project.controller;

import com.frota.project.dtos.users.InputUserRecordDto;
import com.frota.project.dtos.users.OutPutUserRecordDto;
import com.frota.project.dtos.users.RegisterRecordDto;
import com.frota.project.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/frotas/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{nameUser}")
    public ResponseEntity<OutPutUserRecordDto> getUser(@PathVariable String nameUser){
        return userService.getName_User(nameUser);
    }

    @GetMapping
    public ResponseEntity<List<com.frota.project.model.UserModel>> geALL(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getALL());
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRecordDto data) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(data));
    }

    @PutMapping("update/{uuid}")
    public ResponseEntity updateRegister(@PathVariable UUID uuid, @RequestBody InputUserRecordDto data){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(uuid, data));
    }

    @DeleteMapping("delete/{uuid}")
    public ResponseEntity deleteRegister(@PathVariable UUID uuid){
    return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(uuid));
    }

    /*
    private final List<CustomerModel> customers = new ArrayList<>();

    @GetMapping
    public List<CustomerModel> getAll(@RequestParam(required = false) String name) {
        if (name != null && !name.isEmpty()) {
            return customers.stream()
                    .filter(c -> c.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return customers;
    }

    @GetMapping("/{id}")
    public CustomerModel getCustomer(@PathVariable int id) {
        return customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CustomerModel customer) {
        int id = customers.isEmpty() ? 1 : customers.get(customers.size() - 1).getId() + 1;
        customers.add(new CustomerModel(id, customer.getName(), customer.getEmail()));
        System.out.println(customer);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int id, @RequestBody PutCustomerRequest customer) {
        Optional<CustomerModel> existing = customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst();

        existing.ifPresent(c -> {
            c.setName(customer.getName());
            c.setEmail(customer.getEmail());
        });
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        customers.removeIf(c -> c.getId() == id);
    }
     */
}
