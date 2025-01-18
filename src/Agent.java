import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Agent implements Runnable {
    private Random rand = new Random();
    private Table table;
    public Agent(Table table){
//        rand.setSeed(System.currentTimeMillis());
        this.table = table;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            String[] options = new String[]{"Rice", "Nori", "Filling"};

            // add 2 ingredients only if empty
            if (table.getContent().isEmpty()){
                table.addIngredient(options);
            }else{
                System.out.println("The table is not empty");
            }

            // make the agent wait a bit
            try{
                Thread.sleep(500);
            }catch (Exception e){}

        }
    }
}
