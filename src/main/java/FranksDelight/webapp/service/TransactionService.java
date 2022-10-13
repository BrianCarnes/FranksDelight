package FranksDelight.webapp.service;

import FranksDelight.webapp.exception.RecordNotFoundException;
import FranksDelight.webapp.model.Transaction;
import FranksDelight.webapp.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public Page<Transaction> getAllTransactions(Integer pageNo, Integer pageSize, String sortBy) throws RecordNotFoundException {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Transaction> pagedResult = repository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult;
        } else {
            throw new RecordNotFoundException("No user record exist");
        }
    }

    public Transaction getTransactionById(Long id) throws RecordNotFoundException
    {
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException("No user record exist for given id"));
    }

    public Transaction updateTransaction(Transaction entity) throws RecordNotFoundException {
        return repository.findById(entity.getId()).map(transaction -> {
            transaction.setCode(entity.getCode());
            transaction.setContent(entity.getContent());
            transaction.setType(entity.getType());
            transaction.setStatus(entity.getStatus());
            return repository.save(transaction);
        }).orElseThrow(() -> new RecordNotFoundException("No user record exist for given id"));
    }

    public Transaction createTransaction(Transaction entity) {
        return repository.save(entity);
    }

    public void deleteTransactionById(Long id) throws RecordNotFoundException
    {
        Optional<Transaction> transaction = repository.findById(id);

        if(transaction.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No user record exist for given id");
        }
    }
}

