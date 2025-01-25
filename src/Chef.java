import java.util.HashMap;
import java.util.List;

public class Chef implements Runnable{
    private String ingredient;
    private Table table;
    public Chef(Table table, String ingredient) {
        this.ingredient = ingredient;
        this.table = table;
    }

    @Override
    public void run() {
        for (int j = 0; j < 20; j++) {
            // chef tries to cook
            table.removeIngredients(ingredient); // make the sushi and clear the table

            // make the chef program rest
            try{
                Thread.sleep(1000);
            }catch (Exception e){}

        }

    }
}
