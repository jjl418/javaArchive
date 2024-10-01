import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PokeDex {
    private static final String FILE_NAME = "output_" + System.currentTimeMillis() + ".pkdx";

    private static ArrayList<Pokemon> loadPokemonData(String filename) {
        // Todo: Complete this function
    	//deserialize it
    	ArrayList<Pokemon>  al = new ArrayList<Pokemon>(); 
    	try
    	{
    		ObjectInputStream in = new ObjectInputStream(new FileInputStream("output.ser"));
			al = (ArrayList)in.readObject();
        	in.close();
    	}
    	catch(IOException ioe)
    	{
    		ioe.printStackTrace();
    	}
    	catch (ClassNotFoundException c) 
        {
            System.out.println("Class not found");
            c.printStackTrace();
            //return;
        }
        return al;
    }

    private static void savePokemonData(String filename, ArrayList<Pokemon> arr) {
        // Todo: Complete this function
    	//you serialize it
    	try
    	{
    		FileOutputStream out = new FileOutputStream("output.ser");
        	ObjectOutputStream outStream = new ObjectOutputStream(out);
        	outStream.writeObject(arr);
        	outStream.close();
        	out.close();
    	}
    	catch(IOException ioe)
    	{
    		ioe.printStackTrace();
    	}
    }

    public static void main(String[] args) throws InterruptedException {
        Util.printTitle();
        System.out.println("Loading data...");
        ArrayList<Pokemon> initialData = loadPokemonData(FILE_NAME);  // Should show error since file doesn't exist
        assert initialData.size() == 0;

        // Lets create some sample data
        Pokemon squirtle = new Pokemon(7, "Squirtle", "Turtol", new ArrayList<>(List.of("water")));
        Pokemon ditto = new Pokemon(132, "Ditto", "Jelly", new ArrayList<>(List.of("normal")));
        Pokemon pidgeot = new Pokemon(18, "Pidgeot", "Big Bird", new ArrayList<>(List.of("normal", "flying")));
        Util.showLoadingMessage("\nRebuilding database");

        // Lets serialize!!
        Util.showLoadingMessage("Saving collected data, please do not power off");
        savePokemonData(FILE_NAME, new ArrayList<>(List.of(squirtle, ditto, pidgeot)));
        Thread.sleep(1000);
        Util.showLoadingMessage("\nSystem rebooting");
        Util.showLoadingMessage("\nLoading data");
        ArrayList<Pokemon> loadedMons = loadPokemonData(FILE_NAME);

        System.out.println("DATA:");
        for (Pokemon mon: loadedMons) {
            assert mon.getNickname() == null;  // Nicknames should not be saved
            System.out.println(mon);
            System.out.println();
        }
        System.out.println("===============");
    }
}
