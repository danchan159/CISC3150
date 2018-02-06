import java.util.*;
import java.io.*;

public class FileSpitter{
	public static void main(String[] args){

		try (InputStream in = System.in;
			BufferedReader reader = new BufferedReader(new InputStreamReader(in))){
			char[] fileText = new char[500];
			int size = reader.read(fileText);
			for(int i = 0; i < size; i++){
				System.out.print(fileText[i]);}		
		} catch (IOException x) {
			System.err.println(x);
		}
		System.exit(0);
	}
}