package FranksDelight.webapp.service;

import FranksDelight.webapp.enums.UserRole;
import FranksDelight.webapp.exception.RecordNotFoundException;
import FranksDelight.webapp.model.User;
import FranksDelight.webapp.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.time.LocalDateTime.now;


@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    public Page<User> getAllUsers(Integer pageNo, Integer pageSize, String sortBy) throws RecordNotFoundException {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<User> pagedResult = repository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult;
        } else {
            throw new RecordNotFoundException("No user record exist");
        }
    }

    public User getUserById(Long id) throws RecordNotFoundException
    {
        Optional<User> user = repository.findById(id);

        if(user.isPresent()) {
            return user.get();
        } else {
            throw new RecordNotFoundException("No user record exist for given id");
        }
    }

    public User updateUser(@RequestBody User passedInUser) throws RecordNotFoundException {
        Optional<User> currentUser = repository.findById(passedInUser.getId());
        System.out.println(currentUser);
        if(currentUser.isPresent())
        {
            User newUser = currentUser.get();
            newUser.setLastLoginDate(now());
            newUser.setUpdatedDate(now());
            BeanUtils.copyProperties(passedInUser, newUser, FieldHelper.getNullPropertyNames(passedInUser));
            return repository.save(newUser);
        } else {
            throw new RecordNotFoundException("No user record exist for given id");
        }
    }

    public User createUser(User entity) {
        User newUser = new User();

        BeanUtils.copyProperties(entity, newUser, FieldHelper.getNullPropertyNames(entity));
        newUser.setActive(true);
        newUser.setCreatedDate(now());
        newUser.setLastLoginDate(now());
        newUser.setUpdatedDate(now());
        newUser.setMiddleName(null);
        newUser.setIntro("Hello, I am a new user");
        newUser.setProfile("This is my profile");
        newUser.setRole(UserRole.GUEST);
        newUser.setMobile(null);
        return repository.save(newUser);
    }

    public void deleteUserById(Long id) throws RecordNotFoundException
    {
        Optional<User> user = repository.findById(id);

        if(user.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No user record exist for given id");
        }
    }


    public Map<String, Object> paginate(List<User> users, Page<User> page) {
        return Map.of(
                "data", users,
                "currentPage", page.getNumber(),
                "totalItems", page.getTotalElements(),
                "totalPages", page.getTotalPages()
        );
    }
}
