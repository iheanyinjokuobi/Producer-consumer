import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Table {
    private List<String> content;
    // If true, there is room for at least one object in the buffer.
    private boolean writeable = true;

    // If true, there is at least one object stored in the buffer.
    private boolean readable = false;

    private Random rand = new Random();

    public Table() {
        content = new ArrayList<>(2);
    }

    public synchronized void addIngredient(String[] options) {
        while (!writeable) {
            try {
                System.out.println("Producer waiting");
                wait();
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
        // add 2 ingredients only if empty
        if (content.isEmpty()) {
            // randomly pick ingredients
            int index1 = Math.abs(rand.nextInt() % 3);
            int index2 = Math.abs(rand.nextInt() % 3);
            while (index2 == index1) { // to ensure the agent doesn't drop 2 of the same ingredients
                index2 = Math.abs(rand.nextInt() % 3);
            }
            this.content.add(options[index1]);
            this.content.add(options[index2]);
            System.out.println("The agent has produced " + options[index1] + " and " + options[index2]);
            readable = true;
            // if the table is full, don't allow agent to produce
            if (content.size() >= 2) {
                writeable = false;
            }

            notifyAll();
        }else {
            System.out.println("The table is not empty");
        }

    }

    public synchronized void removeIngredients(String ingredient) {
        while (!readable) {
            try {
                System.out.println("Consumer waiting");
                wait();
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
        // only cook if the agent has dropped the other 2 ingredients
        if (content.size() >= 2) {
            // cook if the chef has the necessary ingredients
            if (!content.contains(ingredient)) {
                System.out.println("Sushi has been rolled by the chef with " + ingredient);
                content.clear(); // empty the table
                writeable = true; // allow the producer to produce
                readable = false; // stop consumers from consuming
                notifyAll();
            } else {
                System.out.println("This chef cannot roll sushi");
            }
        }else{
            System.out.println("Not enough ingredients");
        }

    }

    public static void main(String[] args){
        Table table = new Table();
        Agent agent = new Agent(table);
        Chef chef1 = new Chef(table, "Rice");
        Chef chef2 = new Chef(table, "Nori");
        Chef chef3 = new Chef(table, "Filling");

        // create threads for the runnable objects
        Thread agentThread = new Thread(agent, "Agent Thread");
        Thread chef1Thread = new Thread(chef1, "Chef 1");
        Thread chef2Thread = new Thread(chef2, "Chef 2");
        Thread chef3Thread = new Thread(chef3, "Chef 3");

        // start the threads
        agentThread.start();
        chef1Thread.start();
        chef2Thread.start();
        chef3Thread.start();
    }
    
}
