import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class topten {
	
	public static Frog[] tt = new Frog[11];

	public void openFile() throws IOException{
		Scanner reader = new Scanner(new File("TopTen.txt"));
		int count = 0;
		
		while( reader.hasNext()){
			tt[count]= new Frog(reader.next(), Integer.parseInt(reader.next()));
			//System.out.println(tt[count].toString());
			count++;
		}
		reader.close();
		tt[10] = new Frog("",0);
		
	}
	
	public void saveFile() throws IOException{
		PrintWriter writer = new PrintWriter(new File("TopTen.txt"));
		//System.out.println("Close");
		for(int i = 0; i < 10; i++){
			writer.println(tt[i].getInitials() + " " + tt[i].getScore());
		}
		writer.close();
	}
	
	public void sortTop(){
		Frog temp = new Frog();
		for(int i = 0; i <= 10; i++){
			if(tt[i].getInitials().equals(Screen.frog.getInitials()) && tt[i].getMe() == true){
				tt[i].setScore(Screen.frog.getScore());
			}
		}
		for(int front = 0; front < 10; front++){
			for(int back = front + 1; back <= 10; back++){
				if(tt[front].getScore() < tt[back].getScore()){
					temp = tt[front];
					tt[front] = tt[back];
					tt[back] = temp;
				}
			}
		}
	
	}
	
	public boolean checkTopTen(int score){
		for(int i = 0; i < 10; i++){
			if(score >= tt[i].getScore()){
				return true;
			}
		}
		return false;
	}
	
	public String toString(int num){
		return((num +1) + ". "  + tt[num].getInitials() + "  " + tt[num].getScore());
	}
}
