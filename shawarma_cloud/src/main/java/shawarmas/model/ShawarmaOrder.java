package shawarmas.model;

import java.util.List;
import java.util.ArrayList;
import lombok.Data;

@Data
public class ShawarmaOrder {
	private String deliveryName;
	private String deliveryStreet;
	private String deliveryCity;
	private String deliveryState;
	private String deliveryZip;
	private String ccNumber;
	private String ccExpiration;
	private String ccCVV;
	private List<Shawarma> shawarmas = new ArrayList<>();
	
	public void addShawarma(Shawarma shawarma) {
		this.shawarmas.add(shawarma);
	}
}
