package sn.youdev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sn.youdev.entities.Ingredient;
@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,String> {

}
