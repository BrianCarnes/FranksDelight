package FranksDelight.webapp.controller;

import FranksDelight.webapp.exception.RecordNotFoundException;
import FranksDelight.webapp.model.Menu;
import FranksDelight.webapp.service.MenuService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping(value = "/api/menu", produces = "application/json")
@CrossOrigin(origins = "*", exposedHeaders = "Content-Range")
public class MenuController {
    private final MenuService service;

    public MenuController(MenuService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Menu> createMenu(Menu menu) {
        Menu created = service.createMenu(menu);
        return new ResponseEntity<>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllMenus(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "id") String sort) {
        try {

            List<Menu> comments;
            Page<Menu> page = service.getAllMenus(pageNo, pageSize, sort);
            comments = page.getContent();
            Map<String, Object> response = service.paginate(comments, page);
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> getUserById(@PathVariable("id") Long menuId)
            throws RecordNotFoundException {
        Menu entity = service.getMenuById(menuId);

        return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Menu> UpdateMenu(Menu menu) throws RecordNotFoundException {
        Menu updated = service.updateMenu(menu);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteMenuById(@PathVariable("id") Long menuId)
            throws RecordNotFoundException {
        service.deleteMenuById(menuId);
        return HttpStatus.OK;
    }
}
