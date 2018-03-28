package task2;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DownXML<intWorkerId> {

	private ArrayList<Employe> listEmployeParse;

	private Employe employe;
	private Employe worker;
	private Other other;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	ClassLoaderFile patn;

	NodeList nodeList;

	private Manager mg;

	Integer intWorkerId;
	private ArrayList<Integer> listWorkerID;
	private ArrayList<Employe> listWorker;

	public DownXML() {

	}

	public DownXML(ClassLoaderFile patn) {
		this.patn=patn;
	}

	public ArrayList<Employe> getListEmployeParse() {
		return listEmployeParse;
	}

	public void parsXML() throws ParserConfigurationException, SAXException, IOException {
		listEmployeParse = new ArrayList();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		//Document doc = documentBuilder.parse(new File("src/staff.xml"));
		Document doc = null;
		
		//doc.
		
		doc = documentBuilder.parse(new File("staff.xml").toURI().getPath());
		/*
		try {
			//doc = documentBuilder.parse(new File(patn.getPatch("staff.xml")));
			doc = documentBuilder.parse(new File("staff.xml").toURI().toString());
		} catch (URISyntaxException e1) {
			System.out.println("XML null");
			e1.printStackTrace();
		}
		*/
		
		String type = "";
		String name = "";
		String surname = "";
		String patronymic = "";
		int employeId = 0;
		String birthDateS = "";
		String hireDateS = "";
		String description = "";

		nodeList = doc.getElementsByTagName("employe");
		
		System.out.println("nodeList.Len"+nodeList.getLength());

		for (int ii = 0; ii < nodeList.getLength(); ii++) {

			Element elementEl = (Element) nodeList.item(ii);
			type = elementEl.getElementsByTagName("type").item(0).getChildNodes().item(0).getNodeValue();

			if (type.equals("Worker")) {
				worker = new Worker();

				worker.setType(type);
				name = elementEl.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue();
				worker.setName(name);
				surname = elementEl.getElementsByTagName("surname").item(0).getChildNodes().item(0).getNodeValue();
				worker.setSurname(surname);
				patronymic = elementEl.getElementsByTagName("patronymic").item(0).getChildNodes().item(0)
						.getNodeValue();
				worker.setPatronymic(patronymic);
				employeId = Integer.parseInt(elementEl.getAttributes().getNamedItem("id").getNodeValue());
				worker.setId(employeId);
				birthDateS = elementEl.getElementsByTagName("birthDate").item(0).getChildNodes().item(0).getNodeValue();
				try {
					worker.setBirthDate(formatter.parse(birthDateS));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				hireDateS = elementEl.getElementsByTagName("hireDate").item(0).getChildNodes().item(0).getNodeValue();
				try {
					worker.setHireDate(formatter.parse(hireDateS));
				} catch (ParseException e) {
					e.printStackTrace();
				}

				listEmployeParse.add(worker);

			} else if (type.equals("Manager")) {
				mg = new Manager();
				listWorker = new ArrayList<Employe>();
				int t = 0;
				NodeList workerOfManager = elementEl.getElementsByTagName("workerOfManager");

				ArrayList<intWorkerId> listWorkerID = new ArrayList<intWorkerId>();

				for (int wr = 0; wr < workerOfManager.getLength(); wr++) {
					Element elementW = (Element) workerOfManager.item(wr);

					intWorkerId = Integer.valueOf(elementW.getAttributes().getNamedItem("id").getNodeValue());

					for (int i1 = 0; i1 < nodeList.getLength(); i1++) {
						Element elementEl1 = (Element) nodeList.item(i1);
						type = elementEl1.getElementsByTagName("type").item(0).getChildNodes().item(0).getNodeValue();
						Integer employeId1 = Integer
								.parseInt(elementEl1.getAttributes().getNamedItem("id").getNodeValue());
						if (type.equals("Worker") && (employeId1.equals(intWorkerId))) {
							Employe employeM = new Worker();

							employeM.setType(type);
							name = elementEl1.getElementsByTagName("name").item(0).getChildNodes().item(0)
									.getNodeValue();
							employeM.setName(name);
							surname = elementEl1.getElementsByTagName("surname").item(0).getChildNodes().item(0)
									.getNodeValue();
							employeM.setSurname(surname);
							patronymic = elementEl1.getElementsByTagName("patronymic").item(0).getChildNodes().item(0)
									.getNodeValue();
							employeM.setPatronymic(patronymic);
							employeId = Integer.parseInt(elementEl1.getAttributes().getNamedItem("id").getNodeValue());
							employeM.setId(employeId);
							birthDateS = elementEl1.getElementsByTagName("birthDate").item(0).getChildNodes().item(0)
									.getNodeValue();
							try {
								employeM.setBirthDate(formatter.parse(birthDateS));
							} catch (ParseException e) {
								e.printStackTrace();
							}
							hireDateS = elementEl1.getElementsByTagName("hireDate").item(0).getChildNodes().item(0)
									.getNodeValue();
							try {
								employeM.setHireDate(formatter.parse(hireDateS));
							} catch (ParseException e) {
								e.printStackTrace();
							}

							listWorker.add(employeM);

						}
					}

					listWorkerID.add((intWorkerId) intWorkerId);

				}

				mg.setListWorker(listWorker);

				type = elementEl.getElementsByTagName("type").item(0).getChildNodes().item(0).getNodeValue();
				mg.setType(type);
				name = elementEl.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue();
				mg.setName(name);
				surname = elementEl.getElementsByTagName("surname").item(0).getChildNodes().item(0).getNodeValue();
				mg.setSurname(surname);
				patronymic = elementEl.getElementsByTagName("patronymic").item(0).getChildNodes().item(0)
						.getNodeValue();
				mg.setPatronymic(patronymic);
				employeId = Integer.parseInt(elementEl.getAttributes().getNamedItem("id").getNodeValue());
				mg.setId(employeId);
				birthDateS = elementEl.getElementsByTagName("birthDate").item(0).getChildNodes().item(0).getNodeValue();
				try {
					mg.setBirthDate(formatter.parse(birthDateS));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				hireDateS = elementEl.getElementsByTagName("hireDate").item(0).getChildNodes().item(0).getNodeValue();
				try {
					mg.setHireDate(formatter.parse(hireDateS));
				} catch (ParseException e) {
					e.printStackTrace();
				}

				listEmployeParse.add(mg);

			} else {

				other = new Other();

				other.setType(type);
				name = elementEl.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue();
				other.setName(name);
				surname = elementEl.getElementsByTagName("surname").item(0).getChildNodes().item(0).getNodeValue();
				other.setSurname(surname);
				patronymic = elementEl.getElementsByTagName("patronymic").item(0).getChildNodes().item(0)
						.getNodeValue();
				other.setPatronymic(patronymic);
				employeId = Integer.parseInt(elementEl.getAttributes().getNamedItem("id").getNodeValue());
				other.setId(employeId);
				birthDateS = elementEl.getElementsByTagName("birthDate").item(0).getChildNodes().item(0).getNodeValue();
				try {
					other.setBirthDate(formatter.parse(birthDateS));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				hireDateS = elementEl.getElementsByTagName("hireDate").item(0).getChildNodes().item(0).getNodeValue();
				try {
					other.setHireDate(formatter.parse(hireDateS));
				} catch (ParseException e) {
					e.printStackTrace();
				}

				description = elementEl.getElementsByTagName("description").item(0).getChildNodes().item(0)
						.getNodeValue();
				other.setDescription(description);

				listEmployeParse.add(other);

			}

		}

	}

	public Employe getEmploye() {
		return employe;
	}

	public ArrayList<Employe> getWorkerOfManager(int idMan) {
		ArrayList<Employe> listWorker = new ArrayList<Employe>();

		String type = "";
		String name = "";
		String surname = "";
		String patronymic = "";
		int employeId = 0;
		String birthDateS = "";
		String hireDateS = "";

		Element elementEl = (Element) nodeList.item(idMan);
		type = elementEl.getElementsByTagName("type").item(0).getChildNodes().item(0).getNodeValue();

		if (type.equals("Manager")) {
			mg = new Manager();
			listWorker = new ArrayList<Employe>();

			NodeList workerOfManager = elementEl.getElementsByTagName("workerOfManager");

			ArrayList<intWorkerId> listWorkerID = new ArrayList<intWorkerId>();

			for (int wr = 0; wr < workerOfManager.getLength(); wr++) {
				Element elementW = (Element) workerOfManager.item(wr);

				intWorkerId = Integer.valueOf(elementW.getAttributes().getNamedItem("id").getNodeValue());

				for (int i1 = 0; i1 < nodeList.getLength(); i1++) {
					Element elementEl1 = (Element) nodeList.item(i1);
					type = elementEl1.getElementsByTagName("type").item(0).getChildNodes().item(0).getNodeValue();
					Integer employeId1 = Integer.parseInt(elementEl1.getAttributes().getNamedItem("id").getNodeValue());
					if (type.equals("Worker") && (employeId1.equals(intWorkerId))) {
						Employe employeM = new Worker();

						employeM.setType(type);
						name = elementEl1.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue();
						employeM.setName(name);
						surname = elementEl1.getElementsByTagName("surname").item(0).getChildNodes().item(0)
								.getNodeValue();
						employeM.setSurname(surname);
						patronymic = elementEl1.getElementsByTagName("patronymic").item(0).getChildNodes().item(0)
								.getNodeValue();
						employeM.setPatronymic(patronymic);
						employeId = Integer.parseInt(elementEl1.getAttributes().getNamedItem("id").getNodeValue());
						employeM.setId(employeId);
						birthDateS = elementEl1.getElementsByTagName("birthDate").item(0).getChildNodes().item(0)
								.getNodeValue();
						try {
							employeM.setBirthDate(formatter.parse(birthDateS));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						hireDateS = elementEl1.getElementsByTagName("hireDate").item(0).getChildNodes().item(0)
								.getNodeValue();
						try {
							employeM.setHireDate(formatter.parse(hireDateS));
						} catch (ParseException e) {
							e.printStackTrace();
						}

						listWorker.add(employeM);
					}
				}

				listWorkerID.add((intWorkerId) intWorkerId);
			}

		}

		return listWorker;
	}

}
