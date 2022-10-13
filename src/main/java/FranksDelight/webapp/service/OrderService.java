package FranksDelight.webapp.service;

import FranksDelight.webapp.exception.RecordNotFoundException;
import FranksDelight.webapp.model.Order;
import FranksDelight.webapp.model.User;
import FranksDelight.webapp.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Page<Order> getAllOrders(Integer pageNo, Integer pageSize, String sortBy) throws RecordNotFoundException {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Order> pagedResult = repository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult;
        } else {
            throw new RecordNotFoundException("No order record exist");
        }
    }

    public Order getOrderById(Long id) throws RecordNotFoundException
    {
        Optional<Order> order = repository.findById(id);

        if(order.isPresent()) {
            return order.get();
        } else {
            throw new RecordNotFoundException("No order record exist for given id");
        }
    }

    public Order createOrder(Order entity) {
        return repository.save(entity);
    }

    public Order updateOrder(Order entity) throws RecordNotFoundException {
        Optional<Order> order = repository.findById(entity.getId());

        if(order.isPresent())
        {
            Order newOrder = order.get();
            newOrder.setFirstName(entity.getFirstName());
            newOrder.setMiddleName(entity.getMiddleName());
            newOrder.setLastName(entity.getLastName());
            newOrder.setMobile(entity.getMobile());
            newOrder.setEmail(entity.getEmail());
            newOrder.setItem(entity.getItem());
            newOrder.setItemDiscount(entity.getItemDiscount());
            newOrder.setPromo(entity.getPromo());
            newOrder.setPromoDiscount(entity.getPromoDiscount());
            newOrder.setAddress(entity.getAddress());
            newOrder.setCity(entity.getCity());
            newOrder.setState(entity.getState());
            newOrder.setZip(entity.getZip());
            newOrder.setStatus(entity.getStatus());
            return newOrder;
        } else {
            throw new RecordNotFoundException("No order record exist for given id");
        }
    }

    public void deleteOrderById(Long id) throws RecordNotFoundException
    {
        Optional<Order> order = repository.findById(id);

        if(order.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No order record exist for given id");
        }
    }
}
