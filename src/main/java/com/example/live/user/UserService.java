package com.example.live.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
  private static final String FILE_PATH = "users.json";
  private final ObjectMapper objectMapper = new ObjectMapper();

  public List<User> readUsersFromFile() {
    try {
      File file = new File(FILE_PATH);
      if(!file.exists()){
        return new ArrayList<>();
      }
      return objectMapper.readValue(file, new TypeReference<List<User>>() {});
    } catch (IOException e){
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  public void writeUsersToFile(List<User> users) {
    try {
      objectMapper.writeValue(new File(FILE_PATH), users);
    } catch(IOException e){
      e.printStackTrace();
    }
  }

  public List<User> getAllUsers() {
    return readUsersFromFile();
  }

  public Optional<User> getUserById(Long id) {
    return readUsersFromFile().stream().filter(user -> user.getId().equals(id)).findFirst();
  }

  public User createUser(User user) {
    List<User> users = readUsersFromFile();
    user.setId(users.isEmpty() ? 1L : users.get(users.size() -1).getId() + 1);
    users.add(user);
    writeUsersToFile(users);
    return user;
  }

  public User updateUser(Long id, User user) {
    List<User> users = readUsersFromFile();
    for(User existingUser : users) {
      if(existingUser.getId().equals(id)){
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        writeUsersToFile(users);
        return existingUser;
      }
    }
    return null;
  }

  public boolean deleteUser(Long id) {
    List<User> users = readUsersFromFile();
    boolean removed = users.removeIf(user -> user.getId().equals(id));
    if(removed){
      writeUsersToFile(users);
    }
    return removed;
  }

}
