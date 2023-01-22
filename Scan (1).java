package conuHacks;
//The following class will takes in an input of a string then checks if it belongs to a valid categories
//If not, an error message will shown
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.sourceforge.tess4j.*;
import java.io.File;


public class Scan {
	private String text;
	private String[] categories={"Foods","Beverages","Kitchenware","Electronics", "Clothing", "Online Subscriptions", "Transportation", "Entertainment", "Miscellaneous", "Beauty and Personal Care", "Sports and Outdoor"};
	public ArrayList<String> beverages=new ArrayList<String>();
	private ArrayList<String> electronics=new ArrayList<String>();
	private ArrayList<String> foods= new ArrayList<String>();
	private ArrayList<String> kitchenware= new ArrayList<String>();
	private ArrayList<String> clothing = new ArrayList<String>();
    private ArrayList<String> onlineSubscriptions = new ArrayList<String>();
    private ArrayList<String> transportation = new ArrayList<String>();
    private ArrayList<String> entertainment = new ArrayList<String>();
    private ArrayList<String> miscellaneous = new ArrayList<String>();
    private ArrayList<String> beautyAndPersonalCare = new ArrayList<String>();
    private ArrayList<String> sportsAndOutdoor = new ArrayList<String>();
	private ArrayList<String> possible_inputs= new ArrayList<String>();
	private List<String> purchases = new ArrayList<String>();
    private List<Double> prices = new ArrayList<Double>();
    private List<String> Newpurchases = new ArrayList<String>();
    private List<Double> Newprices = new ArrayList<Double>();
    private HashMap<String,Double> Newtotal=new HashMap<String,Double>();
	private HashMap<String,HashMap<String,Double>> hash_categories=new HashMap<String,HashMap<String,Double>>();
	public Scan() {
		// Create a Tesseract instance
        Tesseract instance = new Tesseract();

        // Set the tessdata path (replace with the path to your tessdata folder)
        instance.setDatapath("C:\\Users\\Phu Hoang\\Documents\\Tess4J-3.4.8-src\\Tess4J");

        try {
            // Read in the receipt image
            File imageFile = new File("C:\\Users\\Phu Hoang\\Downloads\\Screen_Shot_2023-01-21_at_11.18.08_PM.png");

            // Extract text from image
            this.text = instance.doOCR(imageFile);

            // Print the extracted text
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
		String[] lines = text.split("\n");

        // Iterate over each line
        for (String line : lines) {
            // Split the line by space
            String[] words = line.split(" ");
            if(words[0].matches("^-?\\d+(\\.\\d+)?$")){
                // check if the last word is a number
                if(words[words.length-1].matches("^-?\\d+(\\.\\d+)?$")){
                    double price = Double.parseDouble(words[words.length-1]);
                    // if it's a number, then it's a purchase
                    String purchase = "";
                    for(int i=1; i<words.length-1; i++){
                        purchase += words[i]+" ";
                    }
                    purchases.add(purchase);
                    prices.add(price);
                    
            }else{
                //if it's not a purchase continue
                continue;
            }
        }
        }
        for(int i = 0; i< purchases.size(); i++) {
            String purchase = purchases.get(i).trim();
            String[] parts = purchase.split(" ");
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < parts.length-1; j++) {
                sb.append(parts[j]);
                sb.append(" ");
            }
            Newpurchases.add(sb.toString().trim());
            Newprices.add(prices.get(i));
    }
     
        
		 Iterator<String> keyIter = Newpurchases.iterator();
	     Iterator<Double> valueIter = Newprices.iterator();
	     while (keyIter.hasNext() && valueIter.hasNext()) {
	            Newtotal.put(keyIter.next(), valueIter.next());
	        }
			possible_inputs.addAll(Newtotal.keySet());

	
	}

	public void categorize() {
		for(int i=0;i<possible_inputs.size();i++) {
			switch(possible_inputs.get(i)) {
			case "Pepsi Can":
                beverages.add(possible_inputs.get(i));
                break;
            case "Sprite":
                beverages.add(possible_inputs.get(i));
                break;
            case "Mineral water":
                beverages.add(possible_inputs.get(i));
                break;
            case "Gatorade":
                beverages.add(possible_inputs.get(i));
                break;
            case "Green Tea":
                beverages.add(possible_inputs.get(i));
                break;
			case "Coke":
				beverages.add(possible_inputs.get(i));
				break;
			case "Alienware":
			case "Samsung TV":
				electronics.add(possible_inputs.get(i));
				break;
			case "TV Set":
                electronics.add(possible_inputs.get(i));
                break;
			case "Cable Wire":
                electronics.add(possible_inputs.get(i));
                break;
			case "Electronics Parts":
                electronics.add(possible_inputs.get(i));
                break;
			case "TV Stand":
                electronics.add(possible_inputs.get(i));
                break;
			case "MacBook Pro":
                electronics.add(possible_inputs.get(i));
                break;
            case "Google Pixel Phone":
                electronics.add(possible_inputs.get(i));
                break;
            case "Canon DSLR Camera":
                electronics.add(possible_inputs.get(i));
                break;
            case "Nintendo Switch":
                electronics.add(possible_inputs.get(i));
                break;
			case "Roasted Chicken":
				foods.add(possible_inputs.get(i));	
				break;
			case "Pizza":
                foods.add(possible_inputs.get(i));
                break;
            case "Sushi":
                foods.add(possible_inputs.get(i));
                break;
            case "Steak":
                foods.add(possible_inputs.get(i));
                break;
            case "Ramen":
                foods.add(possible_inputs.get(i));
                break;
            case "Tacos":
                foods.add(possible_inputs.get(i));
                break;
			case "Frying Pan":
				kitchenware.add(possible_inputs.get(i));
				break;
			case "Blender":
	            kitchenware.add(possible_inputs.get(i));
	                break;
	        case "Microwave":
	            kitchenware.add(possible_inputs.get(i));
	             break;
	        case "Toaster":
	                kitchenware.add(possible_inputs.get(i));
	                break;
	        case "Coffee Maker":
	                kitchenware.add(possible_inputs.get(i));
	                break;
	        case "Rice Cooker":
	                kitchenware.add(possible_inputs.get(i));
	                break;
			 case "Nike Shoes":
                 clothing.add(possible_inputs.get(i));
                 break;
             case "Netflix":
                 onlineSubscriptions.add(possible_inputs.get(i));
                 break;
             case "Amazon Prime":
                 onlineSubscriptions.add(possible_inputs.get(i));
                 break;
             case "Hulu":
                 onlineSubscriptions.add(possible_inputs.get(i));
                 break;
             case "Spotify":
                 onlineSubscriptions.add(possible_inputs.get(i));
                 break;
             case "Disney+":
                 onlineSubscriptions.add(possible_inputs.get(i));
                 break;
             case "Audible":
                 onlineSubscriptions.add(possible_inputs.get(i));
                 break;
             case "Uber":
                 transportation.add(possible_inputs.get(i));
                 break;
             case "Bicycle":
                 transportation.add(possible_inputs.get(i));
                 break;
             case "Bus Pass":
                 transportation.add(possible_inputs.get(i));
                 break;
             case "Car Rental":
                 transportation.add(possible_inputs.get(i));
                 break;
             case "Train Ticket":
                 transportation.add(possible_inputs.get(i));
                 break;
             case "Gasoline":
                 transportation.add(possible_inputs.get(i));
                 break;   
             case "Shampoo":
            	    beautyAndPersonalCare.add(possible_inputs.get(i));
            	    break;
             case "L'Oréal Shampoo":
            	    beautyAndPersonalCare.add(possible_inputs.get(i));
            	    break;
            	case "Neutrogena Soap":
            	    beautyAndPersonalCare.add(possible_inputs.get(i));
            	    break;
            	case "Avene Lotion":
            	    beautyAndPersonalCare.add(possible_inputs.get(i));
            	    break;
            	case "Maybelline Mascara":
            	    beautyAndPersonalCare.add(possible_inputs.get(i));
            	    break;
            	case "Lancome Perfume":
            	    beautyAndPersonalCare.add(possible_inputs.get(i));
            	    break;
            	case "Camping Tent":
            	    sportsAndOutdoor.add(possible_inputs.get(i));
            	    break;
            	case "Nike Running Shoes":
            	    sportsAndOutdoor.add(possible_inputs.get(i));
            	    break;
            	case "Columbia Jacket":
            	    sportsAndOutdoor.add(possible_inputs.get(i));
            	    break;
            	case "Mountain Bike":
            	    sportsAndOutdoor.add(possible_inputs.get(i));
            	    break;
            	case "Kayak":
            	    sportsAndOutdoor.add(possible_inputs.get(i));
            	    break;
            	case "Sleeping Bag":
            	    sportsAndOutdoor.add(possible_inputs.get(i));
            	    break;
             case "others":
                 miscellaneous.add(possible_inputs.get(i));
                 break;
              default:
            	  System.out.println("Invalid input");
            	  break;

				}
			
		}
		for(int i=0;i<this.categories.length;i++) {
			if(this.categories[i]=="Foods"&& foods.size()>0) {
				if(Newtotal.containsKey(foods.get(i))) {
					hash_categories.put("Foods", Newtotal);
					}
			}
			if(this.categories[i]=="Beverages"&&beverages.size()>0) {
				if(Newtotal.containsKey(beverages.get(i))) {
					hash_categories.put("Beverages", Newtotal);
					}
			}
			if(this.categories[i]=="Kitchenware"&&kitchenware.size()>0) {
				if(Newtotal.containsKey(kitchenware.get(i))) {
				hash_categories.put("Kitchenware", Newtotal);
				}
				
			}
            if(this.categories[i]=="Electronics"&&electronics.size()>0) {
            	if(Newtotal.containsKey(electronics.get(i))) {
				hash_categories.put("Electronics", Newtotal);
            	}
				
			
			}
		    if (this.categories[i].equals("Clothing")&&clothing.size()>0) {
		    	if(Newtotal.containsKey(clothing.get(i))) {
					hash_categories.put("Clothing", Newtotal);
	            	}
					
		
			}
			 if (this.categories[i].equals("Online Subscriptions")&&onlineSubscriptions.size()>0) {
				 if(Newtotal.containsKey(onlineSubscriptions.get(i))) {
						hash_categories.put("Online Subscriptions", Newtotal);
		            	}
			}
			if (this.categories[i].equals("Transportation")&&transportation.size()>0) {
				 if(Newtotal.containsKey(transportation.get(i))) {
						hash_categories.put("Transportation", Newtotal);
		            	}
			}
			if (this.categories[i].equals("Entertainment")&&entertainment.size()>0) {
				if(Newtotal.containsKey(entertainment.get(i))) {
					hash_categories.put("Entertainment", Newtotal);
	            	}
			
			}
			if (this.categories[i].equals("Beauty and Personal Care")&&beautyAndPersonalCare.size()>0) {
				if(Newtotal.containsKey(beautyAndPersonalCare.get(i))) {
					hash_categories.put("Beauty and Personal Care", Newtotal);
	            	}
		    }
		    if (this.categories[i].equals("Sports and Outdoor")&&sportsAndOutdoor.size()>0) {
		    	if(Newtotal.containsKey(sportsAndOutdoor.get(i))) {
					hash_categories.put("Sports and Outdoor", Newtotal);
	            	}

		    }
			if (this.categories[i].equals("Miscellaneous")&&miscellaneous.size()>0) {
				if(Newtotal.containsKey(miscellaneous.get(i))) {
					hash_categories.put("Misc", Newtotal);
	            	}
			
			}
		}
		}
	public String display() {
		return hash_categories.toString();
		
	}
	
	public static void main(String[] args) {
		
		Scan a=new Scan();
		a.categorize();
		System.out.println(a.display());

	}

}
