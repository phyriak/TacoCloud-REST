package tacos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tacos.model.Ingredient;


/**
 * Created by phyriak on 29/10/2020
 */

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
