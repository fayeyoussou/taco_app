package sn.youdev.repository;

import org.springframework.data.repository.CrudRepository;
import sn.youdev.entities.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient,String> {

}
