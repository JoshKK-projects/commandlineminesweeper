package mines;
import java.util.*;
public class Tile{
	public Boolean mine;
	public Boolean flipped = false;
	char boom = 'M';
	public Boolean maybe = false;
	public Boolean def = false;

	Tile(){
		Random rand = new Random();
		int mineput = rand.nextInt(100)+1;
		if(mineput<=15){
			this.mine=true;
		}
		else{
			this.mine=false;
		}
	}
	public void printer(int nearby){
		if(!flipped && maybe){
			System.out.print('?');
		}
		if(!flipped && def){
			System.out.print('X');
		}
		else if(!flipped){
			System.out.print('#');
		}
		else if (mine==false){
			System.out.print(nearby);
		}
		else{
			System.out.print('M');
		}
	}
}