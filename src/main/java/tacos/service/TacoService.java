package tacos.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import tacos.controller.OrderController;
import tacos.dto.TacoDto;
import tacos.exception.OrderNotFound;
import tacos.exception.TacoNotFound;
import tacos.model.Ingredient;
import tacos.model.Order;
import tacos.model.Taco;
import tacos.repository.IngredientRepository;
import tacos.repository.OrderRepository;
import tacos.repository.TacoRepository;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by phyriak on 17/12/2020
 */
@Service
@AllArgsConstructor
@Validated
public class TacoService {
    private final TacoRepository tacoRepository;
    private final OrderRepository orderRepository;
    private final IngredientRepository ingredientRepository;


    public Taco createTaco(@Valid Taco taco) {
        return tacoRepository.save(taco);
    }

    public Taco createTacoFromListOfIngredients(TacoDto tacoDto) {
        List<Ingredient> allById = ingredientRepository.findAllById(tacoDto.getIngredients());
        Taco newTaco = Taco.builder()
                .comment(tacoDto.getComment())
                .ingredients(allById)
                .build();
        return tacoRepository.save(newTaco);
    }

    public Taco updateTaco(@Valid Taco taco) {
        return tacoRepository.save(taco);
    }

    public Taco findTacoById(Long id) {
        if (tacoRepository.findById(id).isPresent()) {
            return tacoRepository.findById(id).get();
        } else throw new TacoNotFound("Taco not found!");
    }

    public List<Taco> findAlTaco() {
        return tacoRepository.findAll();
    }

    public void deleteTacById(Long id) {
        if (tacoRepository.findById(id).isPresent()) {
            Taco taco = tacoRepository.findById(id).get();
            tacoRepository.delete(taco);
        } else throw new TacoNotFound("Taco not found!");
    }
}
