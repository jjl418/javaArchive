import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pokemon extends Creature implements Serializable {
    private static final long serialVersionUID = 1;

    // Todo: Complete this class (don't forget to override the toString method!)
    String pokemon;
    int Dex_ID;
    //String type;
    transient String nickname;
    ArrayList<String> arr;
    //make a contructor
    public Pokemon(int Dex_ID, String pokemon, String nickname, ArrayList<String> arr)
    {
    	super();
    	this.pokemon = pokemon;
    	this.Dex_ID = Dex_ID;
    	this.nickname = nickname;
    	this.arr = arr;
    }
    //tostring
    public String toString()
    {
    	String str = "Pokemon: ";
    	str += pokemon + "\n";
    	str += "Dex_ID: ";
    	str += Dex_ID + "\n";
    	str += "Type: ";
    	str += "[";
    	for(int i = 0; i < arr.size(); i++)
    	{
    		str += arr.get(i);
    		//str += ",";
    		if(i != arr.size()-1)
    		{
    			str += ",";
    		}
    	}
    	str += "]";
    	str += "\n";
    	return str;
    	//return "Pokemon: " + pokemon + "\n" + "Dex_ID: " + Dex_ID + "\n" + "Type: " + type + "\n";
    }
    //getNickname
    public String getNickname()
    {
    	return nickname;
    }
}
