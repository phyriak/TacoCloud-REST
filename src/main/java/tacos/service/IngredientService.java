package tacos.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tacos.exception.IngredientNotFound;
import tacos.exception.TacoNotFound;
import tacos.model.Ingredient;
import tacos.model.Taco;
import tacos.repository.IngredientRepository;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by phyriak on 17/12/2020
 */
@Service
@AllArgsConstructor
public class IngredientService {
    private IngredientRepository ingredientRepository;

    public Ingredient createIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient updateTIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient findTIngredientById(Long id) {
        if (ingredientRepository.findById(id).isPresent()) {
            return ingredientRepository.findById(id).get();
        } else throw new IngredientNotFound("Ingredient not found!");
    }

    public List<Ingredient> findAlTIngredient(){
        return ingredientRepository.findAll();
    }

    public void deleteIngredientById(Long id) {
        if (ingredientRepository.findById(id).isPresent()) {
            Ingredient taco = ingredientRepository.findById(id).get();
            ingredientRepository.delete(taco);
        } else throw new IngredientNotFound("Ingredient not found!");
    }
}
