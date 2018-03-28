package task2;

import java.io.File;
import java.net.URISyntaxException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class UploadEmploueFile {

	ClassLoaderFile path;
	
	public UploadEmploueFile() {
		
	}
	
	public UploadEmploueFile(ClassLoaderFile path) {
		this.path=path;
	}

	public void madeXMLWorker(String type) throws ParserConfigurationException{
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();
		
		Element employeEl = document.createElement("employe");
		document.appendChild(employeEl);
		
		
		Element typeEl = document.createElement(type);
		employeEl.appendChild(typeEl);
		
		Element nameEl = document.createElement("name");
		nameEl.appendChild(document.createTextNode("please fill name of worker"));
		typeEl.appendChild(nameEl);
		
		Element surnameEl = document.createElement("surname");
		surnameEl.appendChild(document.createTextNode("please fill surname of worker"));
		typeEl.appendChild(surnameEl);
		
		Element patronymicEl = document.createElement("patronymic");
		patronymicEl.appendChild(document.createTextNode("please fill patronymic of worker"));
		typeEl.appendChild(patronymicEl);
		
		Element birthDateEl = document.createElement("birthDate");
		birthDateEl.appendChild(document.createTextNode("YYYY/MM/DD"));
		typeEl.appendChild(birthDateEl);
		
		Element hireDateEl = document.createElement("hireDate");
		hireDateEl.appendChild(document.createTextNode("YYYY/MM/DD"));
		typeEl.appendChild(hireDateEl);
		
		if (type.equals("Other")){
			Element descriptionEl = document.createElement("description");
			descriptionEl.appendChild(document.createTextNode("please fill description of employe"));
			typeEl.appendChild(descriptionEl);	
		}
		
		
		TransformerFactory factoryTr = TransformerFactory.newInstance();
		try {
			Transformer transformer = factoryTr.newTransformer();
			DOMSource domSourse = new DOMSource(document);
		//	StreamResult streamFile = new StreamResult(new File("src/employe.xml"));

			StreamResult streamFile = null;
			//streamFile = new StreamResult(new File(path.getPatch("employe.xml")));
			streamFile = new StreamResult(new File(new File("employe.xml").toURI().getPath()));
			
			
			transformer.transform(domSourse, streamFile);
			System.out.println("Document saved!!");

		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
