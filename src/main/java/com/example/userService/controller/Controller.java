package com.example.userService.controller;

import com.example.userService.model.User;
import com.example.userService.model.UserOperation;
import com.example.userService.model.UserRequisites;
import com.example.userService.model.UserUI;
import com.example.userService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
    @Autowired
    UserRepository repository;
    @GetMapping("/createFakeBdYouNeverKnowHow")
    public String createFakeBd(){
        repository.save(new User(123, "dudnikov123", "Kollbasa", "Dudnikov Igor", 0.165));
        repository.save(new User(124, "nagibator228", "Jesus007", "Davydov Kirill", 9999.0));
        repository.save(new User(125, "Dorogysa", "qqqweee", "Doroganova Elena", 1000.0));
        return "SCU";
    }

    @PostMapping("/create")
    public  String create(@RequestBody User user){
        repository.save(user);
        return "SUCCESS";
    }
    @GetMapping("/findall")
    public List<User> findAll(){
        return repository.findAll();

    }
    @RequestMapping("/search/{id}")
    public String findById(@PathVariable long id){
        return repository.findById(id).toString();
    }

   @RequestMapping("/getId")
   public ResponseEntity<?> getIdByAuth(@RequestBody UserOperation user){
        User example = new User();
        if (user.getRequisites().getPassword() == null){
            example = repository.findUserByLogin(user.getRequisites().getUsername());
        }
        else{
            example = repository.findUserByLoginAndPassword(user.getRequisites().getUsername(),user.getRequisites().getPassword());
        }

        if (example == null){
            return ResponseEntity.badRequest().body("Не удалось найти пользователя");
        }
        else{
            UserUI returnUser = new UserUI(example.getId(), example.getLogin(), example.getPassword());
            return ResponseEntity.ok().body(returnUser.getId());
        }

   }

   @RequestMapping("/addmoney/{id}/{count}")//Переписать
   public String addMoney(@PathVariable long id, @PathVariable long count){
       Optional<User> user = repository.findById(id);

    return "Suc";
  }
    @RequestMapping("/reducemoney")
    public ResponseEntity<?> addMoney(@RequestBody UserOperation user){
        User _user = repository.findUserByLoginAndPassword(user.getRequisites().getUsername(), user.getRequisites().getPassword());
        if (_user == null){
            return ResponseEntity.badRequest().body("Не удалось найти пользователя");

        }
        else{
            _user.setBalance(_user.getBalance() - user.getPrice());
            if (_user.getBalance() <= 0.0){
                return  ResponseEntity.badRequest().body("Недостаточно средств на счете");

            }
            else{
                repository.save(_user);
                return ResponseEntity.ok().body(_user.getId());
            }
        }


    }


}
