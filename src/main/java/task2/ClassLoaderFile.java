package task2;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class ClassLoaderFile {
	
	String nameFile;
	String patch;

	
	public String getPatch(String nameFile) throws URISyntaxException{
		
		ClassLoader classloader = this.getClass().getClassLoader();
		URL url = classloader.getResource(nameFile);
	
	//	System.out.println(Url.toURI());
	//	System.out.println(Url.getPath());
		System.out.println(url.toString());
		 return url.getPath();
		
		
		
	}
	

}
