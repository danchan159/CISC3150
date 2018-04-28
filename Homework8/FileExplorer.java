import java.util.*;
import java.io.*;

public class FileExplorer{
	private int fileCount = 0;
	private int directoryCount = 0;

	private int levelCounter = 0;
	private File initialPathName;
	private File dir_tree; 
	private PrintWriter pw;

	private String horizontalLine = Character.toString((char)0x2500);
	private String boxCorner = Character.toString((char)0x2514);
	private String verticalLine = Character.toString((char)0x2502);
	private String pipeRight = Character.toString((char)0x251C);

	private FileFilter ignoreFilter = new FileFilter() {
		public boolean accept(File file) {
			Character c = file.getName().charAt(0);
			String cStr = c.toString();
			if(cStr.compareTo(".") == 0){
				return false;
			} else
				return true;
		}
	};

	private FileComparator ignoreCase = new FileComparator();

	public FileExplorer(String initialPathName) throws FileNotFoundException{
		this.initialPathName = new File(initialPathName);
		dir_tree = new File("/home/daniel/CISC3150/Homework8/dir_tree.txt");
		pw = new PrintWriter(dir_tree);
		pw.printf("FileExplorer\n/" + this.initialPathName.getName() + "/\n");
	}


	public void digDeeper(File dir, boolean lastInPreviousDir){
		int i = 0;
		File[] dirFiles = dir.listFiles(ignoreFilter);
		boolean previousDirLast = lastInPreviousDir;

		Arrays.sort(dirFiles, ignoreCase);
		for (i = 0; i < dirFiles.length; i++){
			if (i == dirFiles.length - 1 && dirFiles[i].isDirectory()){
				record(dirFiles[i], levelCounter, true, previousDirLast);
				directoryCount++;
				levelCounter++;
				digDeeper(dirFiles[i], true);
			} else if (i == dirFiles.length - 1) {
				fileCount++;
				record(dirFiles[i], levelCounter, true, previousDirLast);
			} else if (dirFiles[i].isDirectory()){
				record(dirFiles[i], levelCounter, false, previousDirLast);
				directoryCount++;
				levelCounter++;
				digDeeper(dirFiles[i], false);
			} else {
				fileCount++;
				record(dirFiles[i], levelCounter, false, previousDirLast);
			}
		}
		levelCounter--;
		return;
	}

	public void record(File fileDir, int level, boolean last, boolean previousDirLast){
		if(previousDirLast){
			if(level != 0)
				pw.printf("%-4s", verticalLine);
			for (int i = 1; i < level; i++){
				pw.printf("%-4s", "");
			}
			pw.printf("%-4s", boxCorner + horizontalLine + horizontalLine + " ");
			pw.printf(fileDir.getName() + "\n");
		} else if(last){
			for (int i = 0; i < level; i++){
				pw.printf("%-4s", verticalLine);
			}
			pw.printf("%-4s", boxCorner + horizontalLine + horizontalLine + " ");
			pw.printf(fileDir.getName() + "\n");
		} else {
			for (int i = 0; i < level; i++){
				pw.printf("%-4s", verticalLine);
			}
			pw.printf(pipeRight + horizontalLine + horizontalLine + " ");
			pw.printf(fileDir.getName() + "\n");
		}
	}

	public void close(){
		pw.printf("\n" + directoryCount + " directories, " + fileCount + " files");
		pw.close();
	}

	public static void main(String[] args) throws FileNotFoundException{
		try{
			File start = new File("/home/daniel/CISC3150");
			FileExplorer fe = new FileExplorer("/home/daniel/CISC3150");
			fe.digDeeper(start, false);
			fe.close();
		} catch(FileNotFoundException e){
			System.out.println("File was not found. Try using the absolute path.");
		}
	}
}

class FileComparator implements Comparator<File>{
	public int compare(File a, File b){
		return a.getName().compareToIgnoreCase(b.getName());
	}
}