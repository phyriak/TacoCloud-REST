package tacos.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tacos.model.Ingredient;
import tacos.service.IngredientService;

/**
 * Created by phyriak on 17/12/2020
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = "/ingredient", produces = "application/json")
@CrossOrigin(origins = "*")
public class IngredientController {
    private final IngredientService ingredientService;

    @GetMapping("/all")
    public Iterable<Ingredient> allIngredient() {
        // PageRequest page = PageRequest.of(0, 12, Sort.by("createAt").descending());
        return ingredientService.findAlTIngredient();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> IngredientById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(ingredientService.findTIngredientById(id), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
        Ingredient saved = ingredientService.createIngredient(ingredient);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Ingredient> updateIngredient(@RequestBody Ingredient ingredient) {
        Ingredient saved = ingredientService.createIngredient(ingredient);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIngredient(@PathVariable("id") Long id) {
        ingredientService.deleteIngredientById(id);
    }
}
