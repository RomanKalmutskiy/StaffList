package task2;

import java.io.File;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class UploadXML {

	private ArrayList<Employe> listEmploye;
	private Manager mg;
	private Other other;
	DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
	ClassLoaderFile path;

	public UploadXML() {

	}

	public UploadXML(ClassLoaderFile path) {
		this.path=path;
		
	}

	public ArrayList<Employe> getListWork() {
		return listEmploye;
	}

	public void setListWork(ArrayList<Employe> listEmploye) {
		this.listEmploye = listEmploye;
	}

	public void makeXML(ArrayList<Employe> listEmploye) throws ParserConfigurationException {
		this.listEmploye = listEmploye;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();

		Element staffEl = document.createElement("staff");
		document.appendChild(staffEl);

		for (int i = 0; i < listEmploye.size(); i++) {
			
			
			if(listEmploye.get(i).getType().equals("Manager"))
			{
			mg = (Manager)listEmploye.get(i); //создать манагера, проверить длину масива workerOfManager, и столько раз создать воркера
		
			}
			
			Element employeEl = document.createElement("employe");
			Attr attrEmploye = document.createAttribute("id");
			//attrEmploye.setValue(Integer.toString(i));
			
			attrEmploye.setValue(Integer.toString(listEmploye.get(i).getId())); // чтоб ИД не менялся
			employeEl.setAttributeNode(attrEmploye);
			staffEl.appendChild(employeEl);

			Element typeEl = document.createElement("type");
			typeEl.appendChild(document.createTextNode(listEmploye.get(i).getType()));
			employeEl.appendChild(typeEl);

			Element nameEl = document.createElement("name");
			nameEl.appendChild(document.createTextNode(listEmploye.get(i).getName()));
			employeEl.appendChild(nameEl);

			Element surnameEl = document.createElement("surname");
			surnameEl.appendChild(document.createTextNode(listEmploye.get(i).getSurname()));
			employeEl.appendChild(surnameEl);

			Element patronymicEl = document.createElement("patronymic");
			patronymicEl.appendChild(document.createTextNode(listEmploye.get(i).getPatronymic()));
			employeEl.appendChild(patronymicEl);

			Element birthDateEl = document.createElement("birthDate");
			birthDateEl.appendChild(document.createTextNode(df.format(listEmploye.get(i).getBirthDate())));
			employeEl.appendChild(birthDateEl);

			Element hireDateEl = document.createElement("hireDate");
			hireDateEl.appendChild(document.createTextNode(df.format(listEmploye.get(i).getHireDate())));
			employeEl.appendChild(hireDateEl);

			if (listEmploye.get(i).getType().equals("Manager")) {
				Element workersOfManager = document.createElement("workersOfManager");
				// workersOfManager.appendChild(document.createTextNode("workersOfManager"));
				employeEl.appendChild(workersOfManager);

				if (listEmploye.get(i).getType().equals("Manager")) {
					if(mg.getListWorker().size()!=0){
					
					
					for (int u = 0; u < mg.getListWorker().size(); u++) {// выводит работников 
						Element workerOfManager = document.createElement("workerOfManager");
						Attr attrWorkerMange = document.createAttribute("id");
						attrWorkerMange.setValue(Integer.toString(mg.getListWorker().get(u).getId()));
						workerOfManager.setAttributeNode(attrWorkerMange);
						workersOfManager.appendChild(workerOfManager);
						
						// добавляю параметры оф воркер
						Element typeWorkerOfManager = document.createElement("type");
						typeWorkerOfManager.appendChild(document.createTextNode(mg.getListWorker().get(u).getType()));
						workerOfManager.appendChild(typeWorkerOfManager);
						
						Element nameWorkerOfManager = document.createElement("name");
						nameWorkerOfManager.appendChild(document.createTextNode(mg.getListWorker().get(u).getName()));
						workerOfManager.appendChild(nameWorkerOfManager);
						
						Element surnameWorkerOfManager = document.createElement("surname");
						surnameWorkerOfManager.appendChild(document.createTextNode(listEmploye.get(i).getSurname()));
						workerOfManager.appendChild(surnameWorkerOfManager);

						Element patronymicWorkerOfManager = document.createElement("patronymic");
						patronymicWorkerOfManager.appendChild(document.createTextNode(listEmploye.get(i).getPatronymic()));
						workerOfManager.appendChild(patronymicWorkerOfManager);

						Element birthDateWorkerOfManager = document.createElement("birthDate");
						birthDateWorkerOfManager.appendChild(document.createTextNode(df.format(listEmploye.get(i).getBirthDate())));
						workerOfManager.appendChild(birthDateWorkerOfManager);

						Element hireDateWorkerOfManager = document.createElement("hireDate");
						hireDateWorkerOfManager.appendChild(document.createTextNode(df.format(listEmploye.get(i).getHireDate())));
						workerOfManager.appendChild(hireDateWorkerOfManager);				
						
					}
					}
				}
				
				
				   
				//  if(mg.getListWorker().size()!=0){
				//  System.out.println("Name of workerM" + mg.getListWorker().get(0).getName());
				//  }
				 

			}

			if (listEmploye.get(i).getType().equals("Other")) {
				other = (Other)listEmploye.get(i);

				Element otherDescriptionEl = document.createElement("description");
				
				otherDescriptionEl.appendChild(document.createTextNode(other.getDescription())); 
				employeEl.appendChild(otherDescriptionEl);
			}

		}

		TransformerFactory factoryTr = TransformerFactory.newInstance();
		try {
			Transformer transformer = factoryTr.newTransformer();
			DOMSource domSourse = new DOMSource(document);
		//	StreamResult streamFile = new StreamResult(new File("src/staff.xml"));
			StreamResult streamFile = null;
			try {
				streamFile = new StreamResult(new File(path.getPatch("staff.xml")));
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			transformer.transform(domSourse, streamFile);
			System.out.println("Document saved!!");

		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
