import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

public static void main(String[] args) {
 
	String s = extractFile("baseChess3.txt");
	String baseBoard = extractFile("baseBoard.txt");
	
	String[][] f = formatFile(s);
	String[][] base= formatFile (baseBoard);
	printBoard(f);
	//printBoard(base);
	//System.out.println(f[0][7]);
	System.out.println("Move a piece?");
	movePiece(f , "f",2);
	
	//setBoard(f);
	//setPiece(f, 6, 3, 4, 3,base);
	
	
	}
	public static void setBoard(String[][] board) {
		
		StringBuilder sb = new StringBuilder(System.lineSeparator());
		for (String[] row : board) {
			sb.append(Arrays.toString(row))
			.append(",%");
		}
		String result = sb.toString();
		
		result = result.replace("[","");
		result = result.replace("]","");
		result = result.replace(", ",",");
		
		//System.out.println(result);
		try{
		writeToFile("baseChess3.txt", result);
		}catch(Exception e){
			System.out.println("Couldn't write to file");
		}
	}

	public static void setPiece(String[][] board, int iniI, int iniJ, int finI, int finJ, String[][] baseBoard){
		String[][] newBoard = board;
		newBoard[finI][finJ] = newBoard[iniI][iniJ];
		newBoard[iniI][iniJ] = baseBoard[iniI][iniJ];
		setBoard(newBoard);
	}
	public static void movePiece(String[][] board, String letter, int num){
		int lToNum = 0;
		int verticleNum = 8-num;
		if (letter == "a"){
			lToNum = 0;
		}
		else if(letter =="b"){
			lToNum = 1;
		}
		else if(letter =="c"){
			lToNum = 2;
		}
		else if(letter =="d"){
			lToNum = 3;
		}
		else if(letter =="e"){
			lToNum = 4;
		}
		else if(letter =="f"){
			lToNum = 5;
		}
		else if(letter =="g"){
			lToNum = 6;
		}
		else if(letter =="h"){
			lToNum = 7;
		}
		if (board[verticleNum][lToNum].equals("//")||board[verticleNum][lToNum].equals("  ")){
			System.out.println("That space is empty");
			return;
		}
		else {
			System.out.println("The piece on that square is: " + board[verticleNum][lToNum]);
			pieceLogic(board, verticleNum, lToNum);
		}
	}
	public static String spaceInfo(String[][] board, int i , int j){
		return board[i][j];
	}
	
	public static void pieceLogic(String[][] board, int i,int j){
		String pieceInfo = board[i][j];
		if (pieceInfo.equals("  ")||pieceInfo.equals("//")){
			System.out.println("That space is empty");
		}
		else if (pieceInfo.equals("WK")||pieceInfo.equals("BK")){
			System.out.println("This piece can move 1 square in any direction");
			
		}
		else if (pieceInfo.equals("WQ")||pieceInfo.equals("BQ")){
			
		}
		else if (pieceInfo.equals("WB")||pieceInfo.equals("BB")){
			
		}
		else if (pieceInfo.equals("WN")||pieceInfo.equals("BN")){
			
		}
		else if (pieceInfo.equals("WC")||pieceInfo.equals("BC")){
			
		}
		else if (pieceInfo.equals("WP")||pieceInfo.equals("BP")){
			if(pieceInfo.equals("WP")){
				if (spaceInfo(board, i-1, j).equals("  ")||spaceInfo(board, i-1, j).equals("//")){
					if ( i==6 && (spaceInfo(board, i-2, j).equals("  ")||spaceInfo(board, i-2, j).equals("//"))){
						System.out.println("This piece can move up to 2 squares towards the enemy");
					}
					else{
						System.out.println("This piece can move 1 square towards the enemy");
					}
				}
				else{
					System.out.println("This piece cannot move");
				}
			}
			else{
				if (spaceInfo(board, i-1, j).equals("  ")||spaceInfo(board, i-1, j).equals("//"))
					
					if (i == 1 ){
						
						System.out.println("This piece can move up to 2 squares towards the enemy");
					}
					else{
						System.out.println("This piece can move 1 square towards the enemy");
					}
				else{
					System.out.println("This piece cannot move");
				}
			}
		}
		return;
	}
	
	public static void printBoard(String[][] inptString)
	{
		System.out.println("  ____ ____ ____ ____ ____ ____ ____ ____");
		String inString[][];
		inString = inptString;
		for(int i=0; i<8; i++){
			if (i % 2 ==1){
				System.out.print(8-i);
				printOddRows(inString[i]);
			}
			else{
				System.out.print(8-i);
				printEvenRows(inString[i]);
			}
		}
		System.out.println("  a    b    c    d    e    f    g    h");
	}

	public static String[][] formatFile(String fileString)
	{
		String completeFile = fileString;
		String delim1 = "[%]";
		String delim2 = "[,]";
		//String[][] spaces = new String[7][7];
		String[] sOuter = completeFile.split(delim1);
		int length = sOuter.length;
		String [][] result = new String [length][];
		int count = 0;
	    for (String line : sOuter)
	    {
	        result [count] = line.split (delim2);
	        ++count;
	    }
	    
		return result;
	}
	public static String extractFile(String fileName) //read from the file//
	{
		String fileInfo = "";
		File x = new File(fileName);
		try
		{
			Scanner sc = new Scanner(x);
			while(sc.hasNext())
			{
				fileInfo += (sc.nextLine() + "");
			}
			sc.close();
			return fileInfo;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return "System Alert!";
		}
	}

//print board structure//
//print even rows//
public static void printEvenRows(String[] row)
	{
	System.out.print("|");
	for(int i = 0; i < 8; i++)
		//for each row//
	{
		if(i % 2 == 1)
//for all odd numbers...//
		{
			System.out.print(row[i] + "//|");  
		}
		else
		{
			System.out.print(row[i] + "  |"); 
		}
	}
	System.out.println("");
	System.out.println(" |____|////|____|////|____|////|____|////|");
	}
//print odd rows//
public static void printOddRows(String[] row)
	{
	System.out.print("|");
	for(int i = 0; i < 8; i++)
	{
		if(i % 2 == 0)
		{
			System.out.print(row[i] + "//|");
		}
		else
		{
			System.out.print(row[i] + "  |"); 
		}
	}
	System.out.println("");
	System.out.println(" |////|____|////|____|////|____|////|____|");
	}

public static void writeToFile(String file_path, String textLine ) throws IOException {
	//BufferedWriter bfwOut = new BufferedWriter(new FileWriter(path));
	try (Writer writer = new BufferedWriter(new OutputStreamWriter(
              new FileOutputStream(file_path), "utf-8"))) {
		writer.write(textLine);
	}catch(Exception e){
		
	}
}
}


