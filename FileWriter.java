import java.io.*;

public class FileWriter {
	private String path;
	private boolean append = false;
	
	public void  WriteFile( String file_path) {

		path = file_path;
		

		}
	public void WriteFile( String file_path , boolean append_value ) {

		path = file_path;
		append = append_value;

		}
	public void writeToFile(String file_path, String textLine ) throws IOException {
		//BufferedWriter bfwOut = new BufferedWriter(new FileWriter(path));
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(file_path), "utf-8"))) {
			writer.write(textLine);
		}catch(Exception e){
			
		}
	}
	

}
