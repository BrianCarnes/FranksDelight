package FranksDelight.webapp.controller;

import FranksDelight.webapp.exception.RecordNotFoundException;
import FranksDelight.webapp.model.User;
import FranksDelight.webapp.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping(value = "/api/users", produces = "application/json")
@CrossOrigin(origins = "*", exposedHeaders = "Content-Range")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(User user) {
        User created = service.createUser(user);
        return new ResponseEntity<>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "firstName") String sort) {
        try {

            List<User> comments;
            Page<User> page = service.getAllUsers(pageNo, pageSize, sort);
            comments = page.getContent();
            Map<String, Object> response = service.paginate(comments, page);
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long userId)
            throws RecordNotFoundException {
        User entity = service.getUserById(userId);

        return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PatchMapping("/update")
    @CrossOrigin
    public ResponseEntity<User> UpdateUser(User user) throws RecordNotFoundException {
        User updated = service.updateUser(user);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteUserById(@PathVariable("id") Long userId)
            throws RecordNotFoundException {
        service.deleteUserById(userId);
        return HttpStatus.OK;
    }
}
