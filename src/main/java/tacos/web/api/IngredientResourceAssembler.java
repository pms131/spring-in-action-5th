package tacos.web.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import tacos.Ingredient;

public class IngredientResourceAssembler
        extends RepresentationModelAssemblerSupport<Ingredient, IngredientResource> {

    public IngredientResourceAssembler() {
        super(IngredientController.class, IngredientResource.class);
    }

    @Override
    public IngredientResource toModel(Ingredient ingredient) {
        return createModelWithId(ingredient.getId(), ingredient);
    }

    @Override
    protected IngredientResource instantiateModel(Ingredient ingredient) {
        return new IngredientResource(ingredient);
    }

    @Override
    public CollectionModel<IngredientResource> toCollectionModel(Iterable<? extends Ingredient> entities) {
        return super.toCollectionModel(entities);
    }
}
