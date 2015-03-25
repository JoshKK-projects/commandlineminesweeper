package mines;
import java.util.*;
public class Sweeper{
//TOTES NEEDS SWING TO BE REMOTALY PLAYABLE BUT MEH
public static Tile[][]minefield = new Tile[25][80];
public static int pointery = 12;
public static int pointerx = 40;
public static int winlose = 0;

public static void main(String[]args){
	for(int i=0;i<25;i++){
		for(int n=0;n<80;n++){
			Tile block = new Tile();
			minefield[i][n]=block;
		}
	}	
	for(int i=0;i<25;i++){
		for(int n=0;n<80;n++){
			if(i==pointery && n==pointerx){
				System.out.print("*");
			}
			else{
				minefield[i][n].printer(proxer(i,n));
			}
			
		}
	}
	gameloop();	

}
public static void gameloop(){
	Scanner scan = new Scanner(System.in);
	String dir = scan.next();
	switch(dir){
		case "w":
			if(pointery-1>=0){
				pointery--;
				break;
			}
		case "s":
			if(pointery+1<=24){
				pointery++;
				break;
			}
		case "a":
			if(pointery-1>=0){
				pointerx--;
				break;
			}
		case "d":
			if(pointery+1<=79){
				pointerx++;
				break;
			}
		case "g":
			minefield[pointery][pointerx].maybe=true;
			break;
		case "h":
			minefield[pointery][pointerx].def=true;
			break;
		case "f":
			minefield[pointery][pointerx].flipped=true;
			if(proxer(pointery,pointerx)==0){
				recursefind(pointery,pointerx);
			}
			if(minefield[pointery][pointerx].mine){
				for(int i=0;i<25;i++){
					for(int n=0;n<80;n++){	
						winlose--;
						minefield[i][n].flipped=true;
					}
				}		
			}
			didwin();
			break;
	}
	for(int i=0;i<25;i++){
		for(int n=0;n<80;n++){
			if(i==pointery && n==pointerx){
				System.out.print("*");
				
			}
			else{
				int prox = proxer(i,n);
				minefield[i][n].printer(prox);
			}
		}
	}
	if(winlose<0){
		System.out.println("Game Over Looser");
	}
	else{
		System.out.println("");
		gameloop();
	}
}
public static int proxer(int ydir,int xdir){
	int counter = 0;
	for(int i=-1;i<2;i++){
		for(int n=-1;n<2;n++){
			if(xdir+n>=0 && xdir+n<=79 && ydir+i>=0 && ydir+i<=24){
				if(minefield[i+ydir][n+xdir].mine){
					counter++;
				}
			}
		}
	}
	return counter;
}

public static void recursefind(int y,int x){
	/*Boolean still = false;//useless but im keeping it because it SHOULD work as a cuttoff
	for(int i=0;i<25;i++){//Iterativly because my recursion is too stronk
		for(int n=0;n<80;n++){
			if(proxer(i,n)==0 && minefield[i][n].flipped){
				for(int z=-1;z<2;z++){
					for(int c=-1;c<2;c++){
						//System.out.println(z+" "+i+" "+n+" "+c);
						if(i+z>=0 && i+z<=24 && n+c<=79 && n+c>=0){
							//System.out.println("HERE2");
							minefield[i+z][n+c].flipped = true;
							still=true;//should work but no
						}
					}
				}
			}
		}
	}
	counter++;
	if(counter<26){
		recursefind(counter);
	}*/
	for(int i=-1;i<2;i++){ Should work but the JVM is a WIMP,obvs needs parameters
		for(int n=-1;n<2;n++){
			if(x+n>=0 && x+n<=79 && y+i>=0 && y+i<=24){
				if(minefield[y+i][n+x].flipped==false){
					minefield[y+i][n+x].flipped=true;
					if(proxer(y+i,x+n)==0){
						recursefind(y+i,x+n);
					}
				}			
			}
		}
	}

	
	
}
public static void didwin(){
	Boolean done = true;
	for(int i=0;i<25;i++){
		for(int n=0;n<80;n++){
			if(!minefield[i][n].flipped && !minefield[i][n].mine){
				done = false;
			}
		}
	}
	if(done){
		System.out.println("WINNA WINNA CHICKEN DINNA");
	}
}



}