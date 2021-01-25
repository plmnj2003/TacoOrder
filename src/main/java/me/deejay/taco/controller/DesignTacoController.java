package me.deejay.taco.controller;


import lombok.extern.slf4j.Slf4j;
import me.deejay.taco.domain.Ingredient;
import me.deejay.taco.domain.Taco;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static me.deejay.taco.domain.Ingredient.*;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignFrom(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tourtilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tourtilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN","Carnitas",Type.PROTEIN),
                new Ingredient("TMTO","Diced Tomatoes",Type.VEGGIES),
                new Ingredient("LETC","Lettuce",Type.VEGGIES),
                new Ingredient("CHED","Cheddar",Type.CHEESE),
                new Ingredient("JACK","Monterrey Jack",Type.CHEESE),
                new Ingredient("SLSA","Salsa",Type.SAUCE),
                new Ingredient("SRCR","Sour Cream",Type.SAUCE)
        );
        Type[]types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        model.addAttribute("taco", new Taco());

        return "design";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x->x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processDesign(Taco design){
        //이 지점에서 타코디자인을 저장한다.
        //이 작업은 3장에서 할 것이다.
        log.info("Processing design: "+design);
        return "redirect:/orders/current";
    }

}
