package FranksDelight.webapp.controller;

import FranksDelight.webapp.exception.RecordNotFoundException;
import FranksDelight.webapp.model.Transaction;
import FranksDelight.webapp.service.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping(value = "/api/Transactions", produces = "application/json")
@CrossOrigin(origins = "http://localhost:5173/", exposedHeaders = "Content-Range")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllComments(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "firstName") String sort) {
        try {

            List<Transaction> comments;
            Page<Transaction> page = service.getAllTransactions(pageNo, pageSize, sort);
            comments = page.getContent();
            Map<String, Object> response = service.paginate(comments, page);
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") Long TransactionId)
            throws RecordNotFoundException {
        Transaction entity = service.getTransactionById(TransactionId);

        return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Transaction> UpdateTransaction(Transaction Transaction) throws RecordNotFoundException {
        Transaction updated = service.updateTransaction(Transaction);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteTransactionById(@PathVariable("id") Long TransactionId)
            throws RecordNotFoundException {
        service.deleteTransactionById(TransactionId);
        return HttpStatus.OK;
    }
}

