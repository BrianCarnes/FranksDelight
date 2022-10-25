package FranksDelight.webapp.controller;

import FranksDelight.webapp.exception.RecordNotFoundException;
import FranksDelight.webapp.model.Item;
import FranksDelight.webapp.model.User;
import FranksDelight.webapp.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping(value = "/api/items", produces = "application/json")
@CrossOrigin(origins = "https://franks.fulgentcorp.com:5173/", exposedHeaders = "Content-Range")
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Item> createItem(Item item) {
        Item created = service.createItem(item);
        return new ResponseEntity<>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllComments(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "id") String sort) {
        try {

            List<Item> comments;
            Page<Item> page = service.getAllItems(pageNo, pageSize, sort);
            comments = page.getContent();
            Map<String, Object> response = service.paginate(comments, page);
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") Long ItemId)
            throws RecordNotFoundException {
        Item entity = service.getItemById(ItemId);

        return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Item> UpdateItem(Item Item) throws RecordNotFoundException {
        Item updated = service.updateItem(Item);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteItemById(@PathVariable("id") Long ItemId)
            throws RecordNotFoundException {
        service.deleteItemById(ItemId);
        return HttpStatus.OK;
    }
}
