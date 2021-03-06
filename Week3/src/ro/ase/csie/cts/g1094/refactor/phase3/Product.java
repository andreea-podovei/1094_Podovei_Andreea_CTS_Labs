package ro.ase.csie.cts.g1094.refactor.phase3;

import java.nio.channels.UnsupportedAddressTypeException;

import ro.ase.csie.cts.g1094.refactor.exceptions.InvalidAgeException;
import ro.ase.csie.cts.g1094.refactor.exceptions.InvalidPriceException;
import ro.ase.csie.cts.g1094.refactor.phase3.services.MarketingServiceInterface;
import ro.ase.csie.cts.g1094.refactor.phase3.services.ValidatorServiceInterface;
import ro.ase.csie.cts.g1094.refactor.phase3.test.TestProduct;

public class Product {

	MarketingServiceInterface mkService = null;
	ValidatorServiceInterface validator = null;
	public Product(MarketingServiceInterface mkService, ValidatorServiceInterface validator) {
		/*if(mkService == null) {
			throw new NullPointerException();
		}
		this.mkService = mkService;*/
		this.setMarketingService(mkService);
		this.validator = validator;
	}
	
	//version 4 - use the global service collection
	public Product() {
		//dependency injection based on the global services collection
		for(Object service : TestProduct.services) {
			if(service instanceof MarketingServiceInterface) {
				this.setMarketingService((MarketingServiceInterface)service);
			}
			if(service instanceof ValidatorServiceInterface) {
				this.validator = (ValidatorServiceInterface) service;
			}
		}
		if(this.mkService == null) {
			throw new UnsupportedOperationException();
		}
		if(this.validator == null) {
			throw new UnsupportedOperationException();
		}
	}
	
	//optional - based on design specs
	public void setMarketingService(MarketingServiceInterface mkService) {
		if(mkService == null) {
			throw new NullPointerException();
		}
		this.mkService = mkService;
	}
	
	public static float getDiscountValue(float price, float discount) {
		return discount*price;
	}
	
	private static float getPriceWtihDiscountAndFidelity(float price, float discountValue,
			float fidelityDiscount) {
		return (price - discountValue) * (1- fidelityDiscount);
	}
	
	private static float getFinalPrice(float price, float fidelityDiscount, ProductType type) {
		float discountValue = getDiscountValue(price, type.getDiscount());
    	float finalPrice = 
    			getPriceWtihDiscountAndFidelity(price, discountValue, fidelityDiscount);
    	return finalPrice;
	}
	
	public float computePriceWithDiscount(ProductType productType, float price, int accountAge) throws InvalidPriceException, InvalidAgeException
	  {
		validator.validatePrice(price);
		validator.validateAge(accountAge);
		
	    float fidelityDiscount =
	    		(productType == ProductType.NEW) ? 0 : mkService.getFidelityDiscount(accountAge);
	    
	    float finalPrice = getFinalPrice(price, fidelityDiscount, productType);
	    
	    return finalPrice;
	  }
}