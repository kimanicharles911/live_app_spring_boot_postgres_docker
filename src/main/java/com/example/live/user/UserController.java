package com.example.live.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping
  public List<User> getAllUsers(){
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  public User getUserById(@PathVariable Long id) {
    Optional<User> user = userService.getUserById(id);
    return user.orElse(null);
  }

  @PostMapping
  public User createUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  @PutMapping("/{id}")
  public User updateUser(@PathVariable Long id, @RequestBody User user){
    return userService.updateUser(id, user);
  }

  @DeleteMapping("/{id}")
  public String deleteUser(@PathVariable Long id){
    return userService.deleteUser(id) ? "User deleted successfully." : "User not found.";
  }

}
