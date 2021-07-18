package tacos.web.api;

import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import tacos.Ingredient;
import tacos.Taco;

import java.util.Date;
import java.util.List;

@Getter
public class TacoResource extends RepresentationModel<TacoResource> {

    private static final IngredientResourceAssembler
            INGREDIENT_RESOURCE_ASSEMBLER = new IngredientResourceAssembler();

    private final String name;
    private final Date createdAt;
    private final CollectionModel<IngredientResource> ingredients;

    public TacoResource(Taco taco) {
        this.name = taco.getName();
        this.createdAt = taco.getCreatedAt();
        this.ingredients = INGREDIENT_RESOURCE_ASSEMBLER.toCollectionModel(taco.getIngredients());
    }
}
