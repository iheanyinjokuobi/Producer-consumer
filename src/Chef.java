import java.util.HashMap;
import java.util.List;

public class Chef implements Runnable{
//    private HashMap<String, Integer> ingredientOptions;
    String ingredient;
    private Table table;
    public Chef(Table table, String ingredient) {
//        ingredientOptions = new HashMap<>();
//        ingredientOptions.put("Rice", 1);
//        ingredientOptions.put("Nori", 2);
//        ingredientOptions.put("Filling", 3);
        this.ingredient = ingredient;
        this.table = table;
    }

    @Override
    public void run() {
        for (int j = 0; j < 20; j++) {
            // only cook if the agent has dropped the other 2 ingredients
            if (table.getContent().size() >= 2){
                table.removeIngredients(ingredient); // clear the table
            }

            // make the chef program rest
            try{
                Thread.sleep(1000);
            }catch (Exception e){}

        }
    }
}
