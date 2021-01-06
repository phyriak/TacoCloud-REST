package tacos.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.dto.TacoDto;
import tacos.model.Ingredient;
import tacos.model.Order;
import tacos.model.Taco;
import tacos.repository.IngredientRepository;
import tacos.repository.TypeRepository;
import tacos.service.TacoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
@SessionAttributes("order")
public class TacoController {
    private final IngredientRepository ingredientRepository;
    private final TacoService tacoService;
    private final TypeRepository typeRepository;
    private final EntityLinks entityLinks;
    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping("/recent")
    public Iterable<Taco> allTacos() {
        // PageRequest page = PageRequest.of(0, 12, Sort.by("createAt").descending());
        return tacoService.findAlTaco();
    }



    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(tacoService.findTacoById(id), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Taco> createTaco(@RequestBody TacoDto tacoDto, @ModelAttribute("order") Order order) {
        Taco saved = tacoService.createTacoFromListOfIngredients(tacoDto);
        order.setCreateAt(new Date());
        order.addDesign(saved);
        log.info("Przetwarzanie projektu taco: " + saved);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Taco> updateTaco(@RequestBody Taco taco) {
        return new ResponseEntity<>(tacoService.updateTaco(taco), HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaco(@PathVariable("id") Long id) {
        try {
            tacoService.deleteTacById(id);
        } catch (EmptyResultDataAccessException e) {}
    }
    
/*
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
    }*/


/*    @PostMapping
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
    }*/



}
