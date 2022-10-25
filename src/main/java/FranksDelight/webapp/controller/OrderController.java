package FranksDelight.webapp.controller;

import FranksDelight.webapp.exception.RecordNotFoundException;
import FranksDelight.webapp.model.Order;
import FranksDelight.webapp.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping(value = "/api/orders", produces = "application/json")
@CrossOrigin(origins = "*", exposedHeaders = "Content-Range")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(Order order) {
        Order created = service.createOrder(order);
        return new ResponseEntity<>(created, new HttpHeaders(), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllComments(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "id") String sort) {
        try {

            List<Order> comments;
            Page<Order> page = service.getAllOrders(pageNo, pageSize, sort);
            comments = page.getContent();
            Map<String, Object> response = service.paginate(comments, page);
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long OrderId)
            throws RecordNotFoundException {
        Order entity = service.getOrderById(OrderId);

        return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> UpdateOrder(Order Order) throws RecordNotFoundException {
        Order updated = service.updateOrder(Order);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteOrderById(@PathVariable("id") Long OrderId)
            throws RecordNotFoundException {
        service.deleteOrderById(OrderId);
        return HttpStatus.OK;
    }
}
