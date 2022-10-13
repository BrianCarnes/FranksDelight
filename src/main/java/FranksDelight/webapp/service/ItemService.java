package FranksDelight.webapp.service;

import FranksDelight.webapp.exception.RecordNotFoundException;
import FranksDelight.webapp.model.Item;
import FranksDelight.webapp.model.User;
import FranksDelight.webapp.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository itemRepository) {
        this.repository = itemRepository;
    }

    public Page<Item> getAllItems(Integer pageNo, Integer pageSize, String sortBy) throws RecordNotFoundException {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Item> pagedResult = repository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult;
        } else {
            throw new RecordNotFoundException("No user record exist");
        }
    }

    public Item getItemById(Long id) throws RecordNotFoundException
    {
        Optional<Item> item = repository.findById(id);

        if(item.isPresent()) {
            return item.get();
        } else {
            throw new RecordNotFoundException("No user record exist for given id");
        }
    }

    public Item updateItem(Item entity) throws RecordNotFoundException {
        Optional<Item> item = repository.findById(entity.getId());

        if(item.isPresent())
        {
            Item newItem = item.get();
            newItem.setTitle(entity.getTitle());
            newItem.setPrice(entity.getPrice());
            newItem.setQuantity(entity.getQuantity());
            newItem.setIsAvailable(entity.getIsAvailable());
            newItem.setInstructions(entity.getInstructions());
            newItem.setCooking(entity.getCooking());
            return repository.save(newItem);
        } else {
            throw new RecordNotFoundException("No user record exist for given id");
        }
    }

    public Item createItem(Item entity) {
        return repository.save(entity);
    }

    public void deleteItemById(Long id) throws RecordNotFoundException
    {
        Optional<Item> item = repository.findById(id);

        if(item.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No user record exist for given id");
        }
    }

    public Map<String, Object> paginate(List<Item> items, Page<Item> page) {
        return Map.of(
                "data", items,
                "currentPage", page.getNumber(),
                "totalItems", page.getTotalElements(),
                "totalPages", page.getTotalPages()
        );
    }
}
