package shawarmas.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;


@Data
public class Shawarma {
	@NotNull
	@Size(min=5, message="Name must be at least 5 characters long")
	private String name;
	@NotNull
	@Size(min = 1, message="Shawarma should have at least 1 ingredient")
	private List<Ingredient> ingredients;
}