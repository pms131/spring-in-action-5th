package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
    /* JDBC 연결 예제
    Order save(Order order);
    */
}
