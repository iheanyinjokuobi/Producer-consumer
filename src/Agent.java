import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Agent implements Runnable {
    private Table table;
    public Agent(Table table){
        this.table = table;
    }

    @Override
    public void run() {
        int count = 0;
        for (int i = 0; i < 20; i++) {
            String[] options = new String[]{"Rice", "Nori", "Filling"};

            // agent tries to place the 2 ingredients from the given options

            table.addIngredient(options);
            count++;
            System.out.println(count);
            // make the agent wait a bit
            try{
                Thread.sleep(500);
            }catch (Exception e){}

        }

    }
}
