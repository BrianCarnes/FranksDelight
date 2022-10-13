package FranksDelight.webapp.service;

import FranksDelight.webapp.exception.RecordNotFoundException;
import FranksDelight.webapp.model.User;
import FranksDelight.webapp.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;



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

    public User updateUser(User entity) throws RecordNotFoundException {
        Optional<User> user = repository.findById(entity.getId());

        if(user.isPresent())
        {
            User newUser = user.get();
            newUser.setFirstName(entity.getFirstName());
            newUser.setMiddleName(entity.getMiddleName());
            newUser.setLastName(entity.getLastName());
            newUser.setEmail(entity.getEmail());
            newUser.setRole(entity.getRole());
            newUser.setActive(entity.isActive());
            newUser.setMobile(entity.getMobile());
            newUser.setIntro(entity.getIntro());
            newUser.setProfile(entity.getProfile());
            newUser.setActive(entity.isActive());
            return repository.save(newUser);
        } else {
            throw new RecordNotFoundException("No user record exist for given id");
        }
    }

    public User createUser(User entity) {
        return repository.save(entity);
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
