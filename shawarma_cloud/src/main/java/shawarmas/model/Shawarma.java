package shawarmas.model;

import java.util.List;
import lombok.Data;

@Data
public class Shawarma {
	private String name;
	private List<Ingredient> ingredients;
}