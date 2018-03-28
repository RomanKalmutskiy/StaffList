package task2;

import java.net.URL;

public class PatchMain {
	private String context;

	URL url;

	public PatchMain() {

		 url = this.getClass().getClassLoader().getResource("");

	}

	public String getPatch() {
		String context = url.toString();
		
		return context;
	}

}
