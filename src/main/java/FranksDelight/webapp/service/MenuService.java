package FranksDelight.webapp.service;

import FranksDelight.webapp.exception.RecordNotFoundException;
import FranksDelight.webapp.model.Menu;
import FranksDelight.webapp.model.User;
import FranksDelight.webapp.repository.MenuRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MenuService {

    private final MenuRepository repository;

    public MenuService(MenuRepository repository) {
        this.repository = repository;
    }

    public Page<Menu> getAllMenus(Integer pageNo, Integer pageSize, String sortBy) throws RecordNotFoundException {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Menu> pagedResult = repository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult;
        } else {
            throw new RecordNotFoundException("No user record exist");
        }
    }

    public Menu getMenuById(Long id) throws RecordNotFoundException
    {
        Optional<Menu> menu = repository.findById(id);

        if(menu.isPresent()) {
            return menu.get();
        } else {
            throw new RecordNotFoundException("No movie record exist for given id");
        }
    }

    public Menu createMenu(Menu entity) {
        return repository.save(entity);
    }

    public Menu updateMenu(Menu entity) throws RecordNotFoundException {
        Optional<Menu> menu = repository.findById(entity.getId());

        if(menu.isPresent())
        {
            Menu newMenu = menu.get();
            newMenu.setContent(entity.getContent());
            newMenu.setTitle(entity.getTitle());
            newMenu.setSummary(entity.getSummary());
            return newMenu;
        } else {
            throw new RecordNotFoundException("No menu record exist for given id");
        }
    }

    public void deleteMenuById(Long id) throws RecordNotFoundException
    {
        Optional<Menu> menu = repository.findById(id);

        if(menu.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No movie record exist for given id");
        }
    }
    public Map<String, Object> paginate(List<Menu> menus, Page<Menu> page) {
        return Map.of(
                "data", menus,
                "currentPage", page.getNumber(),
                "totalItems", page.getTotalElements(),
                "totalPages", page.getTotalPages()
        );
    }
}
