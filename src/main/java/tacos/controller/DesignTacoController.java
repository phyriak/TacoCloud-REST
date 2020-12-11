package tacos.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.model.Ingredient;
import tacos.model.Order;
import tacos.model.Taco;
import tacos.model.Type;
import tacos.repository.IngredientRepository;
import tacos.repository.TacoRepository;
import tacos.repository.TypeRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
@AllArgsConstructor
public class DesignTacoController {
    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;
    private final TypeRepository typeRepository;

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping()
    public String showDesignForm(Model model) {
        List<Ingredient> all = ingredientRepository.findAll();
        List<String> ingredientList = new ArrayList<>();
        List<Type> types = typeRepository.findAll();
        for (Type type : types) {
            model.addAttribute(type.getType().toLowerCase(),
                    filterByType(all, type));
        }
        model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute("order") Order order) {
        if (errors.hasErrors()) {
            log.info("errors");
            return "redirect:/";
        }
        Taco saved = tacoRepository.save(design);
        order.setCreateAt(new Date());
        order.addDesign(saved);
        log.info("Przetwarzanie projektu taco: " + design);
        return "redirect:/design";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(a -> a.getType().getType().equals(type.getType())).collect(Collectors.toList());
    }
}
