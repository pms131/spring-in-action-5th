package tacos.data;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import tacos.Order;
import tacos.User;

import java.util.List;
import java.util.Optional;


public interface OrderRepository extends CrudRepository<Order, Long> {
    Optional<List<Order>> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
    /* JDBC 연결 예제
    Order save(Order order);
    */
}
