package task2;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javafx.animation.PathTransition;

public class DownEmploye {

	private NodeList nodeList;
	
	private Other other;
	private Employe employe;
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
//	private ClassLoaderFile path;

	private String type = "";
	private String name = "";
	private String surname = "";
	private String patronymic = "";
	private String birthDateS = "";
	private String hireDateS = "";

	private Date birthDate;
	private Date hireDate;

	private String description = "";

	private Document doc;

	public DownEmploye() {

	}

	public DownEmploye(ClassLoaderFile path) {
		//this.path=path;
	}

	public void parsXMLEmploye() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = null;
		try {
			documentBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			
			//doc = documentBuilder.parse(new File(path.getPatch("employe.xml")));
			doc = documentBuilder.parse(new File(new File("employe.xml").toURI().getPath()));
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		nodeList = doc.getElementsByTagName("employe");

		Element elementEl = (Element) nodeList.item(0);

		type = elementEl.getElementsByTagName("name").item(0).getParentNode().getNodeName();
		setType(type);

		name = elementEl.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue();
		surname = elementEl.getElementsByTagName("surname").item(0).getChildNodes().item(0).getNodeValue();
		patronymic = elementEl.getElementsByTagName("patronymic").item(0).getChildNodes().item(0).getNodeValue();
		birthDateS = elementEl.getElementsByTagName("birthDate").item(0).getChildNodes().item(0).getNodeValue();
		hireDateS = elementEl.getElementsByTagName("hireDate").item(0).getChildNodes().item(0).getNodeValue();

		try {
			birthDate = formatter.parse(birthDateS);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			hireDate = formatter.parse(hireDateS);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (type.equals("Other")) {
			description = elementEl.getElementsByTagName("description").item(0).getChildNodes().item(0).getNodeValue();

		}

		if (type.equals("Worker")) {

			employe = new Worker(name, surname, patronymic, birthDate, hireDate, type);
		} else if (type.equals("Manager")) {
			employe = new Manager(name, surname, patronymic, birthDate, hireDate, type);
		} else {
			employe = new Other(name, surname, patronymic, birthDate, hireDate, type);
			
		}

	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public Employe getEmploye() {
		return employe;
	}

	public String getDescription() {
		return description;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	@Override
	public String toString() {
		return "DownEmploye [name=" + name + ", surname=" + surname + ", patronymic=" + patronymic + ", birthDate="
				+ birthDate + ", hireDate=" + hireDate + "]";
	}

}
