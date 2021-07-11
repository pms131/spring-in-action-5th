package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
    
    /* JDBC 연결 예제
    Iterable<Ingredient> findAll();
    Ingredient findById(String id);
    Ingredient save(Ingredient ingredient);
    */
}
