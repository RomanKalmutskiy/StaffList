package task2;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) throws ParseException {

		ArrayList<Employe> listEmployeParse;
		ArrayList<Employe> listWorker;
		boolean sort = true;
		ClassLoaderFile path = new ClassLoaderFile();

		boolean flag = true;
		ValidationXML validXML = new ValidationXML();

		String staffXmlString = new File("staff.xml").toURI().getPath();
		
		String staffXsdlString = new File("staff.xsd").toURI().getPath();

		try { validXML.validate(staffXmlString, staffXsdlString);}
		
		catch (SAXException e) {
			flag = false;
		} catch (IOException e) {
			flag = false;
		}
		System.out.println("xml file is valid " + flag);

		UploadXML upXML = new UploadXML(path);
		DownXML downXML = new DownXML(path);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

		boolean go = true;
		Scanner in = null;
		Scanner jn = null;
		int m = 0;
		int maxId = 0;
		while (go) {

			try {
				downXML.parsXML();
			} catch (ParserConfigurationException e1) {

				e1.printStackTrace();
			} catch (SAXException e1) {

				e1.printStackTrace();
			} catch (IOException e1) {

				e1.printStackTrace();
			}

			listEmployeParse = downXML.getListEmployeParse();

			if (sort) {
				sortById(listEmployeParse);
			}

			if (listEmployeParse.size() > 0) {

				maxId = listEmployeParse.get(listEmployeParse.size() - 1).getId();
			} else {
				maxId = 0;
			}

			System.out.printf("%-2s|%-9s|%-10s|%-15s|%-15s|%-12s|%-12s|\n", "employe ID", "Type", "Name", "Surname",
					"Patronymic", "Birth Date", "Hire Date");
			System.out.printf("%-2s|%-9s|%-10s|%-15s|%-15s|%-12s|%-12s|\n", "----------", "---------", "----------",
					"---------------", "---------------", "------------", "------------");

			for (Employe employes : listEmployeParse) {
				System.out.printf("%-10s|%-9s|%-10s|%-15s|%-15s|%-12s|%-12s|\n", employes.getId(), employes.getType(),
						employes.getName(), employes.getSurname(), employes.getPatronymic(),
						formatter.format(employes.getBirthDate()), formatter.format(employes.getHireDate()));
				System.out.printf("%-2s|%-9s|%-10s|%-15s|%-15s|%-12s|%-12s|\n", "----------", "---------", "----------",
						"---------------", "---------------", "------------", "------------");

			}

			in = new Scanner(System.in);
			int n;
			String s;
			System.out.println("'1' - add employee; '2' - delete employee; '3'- change employee;");
			System.out.println("'4' - add worker to manager; '5' - del worker from manager;");
			System.out.println("'6'- sort by Id; '7' - sort by surname; '8'- sort by HireDate");
			System.out.println("'9'- Exit");
			n = in.nextInt();

			switch (n) {
			case 1:

				System.out.println("You want add employe from console? Y/N");
				String y = in.next();
				if (y.equals("Y") || y.equals("y")) {

					System.out.println("'1' - add worker; '2' - add manager; '3'- add other; '0' - Back");
					n = in.nextInt();

					switch (n) {
					case 1:
						System.out.println("Please fill worker");
						Employe worker = new Worker();
						System.out.println("Enter name of worker:");

						worker.setName(in.next());
						System.out.println("Enter surname of worker:");

						worker.setSurname(in.next());

						System.out.println("Enter patronymic of worker:");

						worker.setPatronymic(in.next());
						int yearB = 0;

						System.out.println("Enter year of Birth:");

						while (yearB == 0) {
							jn = new Scanner(System.in);
							if (jn.hasNextInt()) {
								yearB = jn.nextInt();
								worker.setYearB(yearB);
							}
						}

						System.out.println("Enter month of Birth:");
						int monthB = 0;
						while (monthB == 0) {
							jn = new Scanner(System.in);
							if (jn.hasNextInt()) {
								monthB = jn.nextInt();
								worker.setMonthB(monthB);
							}
						}

						System.out.println("Enter day of Birth:");
						int dayB = 0;
						while (dayB == 0) {
							jn = new Scanner(System.in);
							if (jn.hasNextInt()) {
								dayB = jn.nextInt();
								worker.setDayB(dayB);
							}
						}

						worker.setBirthDate();

						System.out.println("Enter year of Hire:");
						int yearH = 0;
						while (yearH == 0) {
							jn = new Scanner(System.in);
							if (jn.hasNextInt()) {
								yearH = jn.nextInt();

								worker.setYearH(yearH);
							}
						}

						System.out.println("Enter month of Hire:");

						int monthH = 0;
						while (monthH == 0) {
							jn = new Scanner(System.in);
							if (jn.hasNextInt()) {
								monthH = jn.nextInt();
								worker.setMonthH(monthH);
							}
						}

						System.out.println("Enter day of Hire:");

						int dayH = 0;
						while (dayH == 0) {
							jn = new Scanner(System.in);
							if (jn.hasNextInt()) {
								dayH = jn.nextInt();
								worker.setDayH(dayH);
							}
						}

						worker.setHireDate();

						worker.setId(maxId + 1);

						System.out.println("Save: " + worker);
						System.out.println("Y/N ?");
						s = in.next();
						if (s.equals("Y") || s.equals("y")) {
							listEmployeParse.add(worker);
							System.out.println("Worker saved");
						} else
							System.out.println("No");

						break;
					case 2:
						System.out.println("Please fill manager");
						Employe manager = new Manager();
						System.out.println("Enter name of manager:");

						manager.setName(in.next());
						System.out.println("Enter surname of manager:");

						manager.setSurname(in.next());
						System.out.println("Enter patronymic of manager:");

						manager.setPatronymic(in.next());

						System.out.println("Enter year of Birth:");

						yearB = 0;
						while (yearB == 0) {
							jn = new Scanner(System.in);
							if (jn.hasNextInt()) {
								yearB = jn.nextInt();
								manager.setYearB(yearB);
							}
						}

						System.out.println("Enter month of Birth:");
						monthB = 0;
						while (monthB == 0) {
							jn = new Scanner(System.in);
							if (jn.hasNextInt()) {
								monthB = jn.nextInt();
								manager.setMonthB(monthB);
							}
						}

						System.out.println("Enter day of Birth:");

						dayB = 0;
						while (dayB == 0) {
							jn = new Scanner(System.in);
							if (jn.hasNextInt()) {
								dayB = jn.nextInt();
								manager.setDayB(dayB);
							}
						}

						manager.setBirthDate();

						System.out.println("Enter year of Hire:");
						yearH = 0;
						while (yearH == 0) {
							jn = new Scanner(System.in);
							if (jn.hasNextInt()) {
								yearH = jn.nextInt();
								manager.setYearH(yearH);
							}
						}

						System.out.println("Enter month of Hire:");
						monthH = 0;
						while (monthH == 0) {
							jn = new Scanner(System.in);
							if (jn.hasNextInt()) {
								monthH = jn.nextInt();
								manager.setMonthH(monthH);
							}
						}

						System.out.println("Enter day of Hire:");
						dayH = 0;
						while (dayH == 0) {
							jn = new Scanner(System.in);
							if (jn.hasNextInt()) {
								dayH = jn.nextInt();
								manager.setDayH(dayH);
							}
						}

						manager.setHireDate();
						manager.setId(maxId + 1);

						System.out.println("Save: " + manager);
						System.out.println("Y/N ?");
						s = in.next();
						if (s.equals("Y") || s.equals("y")) {
							listEmployeParse.add(manager);
							System.out.println("Manage saved");
						} else
							System.out.println("No");

						break;
					case 3:
						System.out.println("add other");
						System.out.println("Please fill other");
						Other other = new Other();
						System.out.println("Enter name of worker:");

						other.setName(in.next());
						System.out.println("Enter surname of other:");

						other.setSurname(in.next());
						System.out.println("Enter patronymic of other:");

						other.setPatronymic(in.next());
						System.out.println("Enter year of Birth:");

						yearB = 0;
						while (yearB == 0) {
							jn = new Scanner(System.in);
							if (jn.hasNextInt()) {
								yearB = jn.nextInt();
								other.setYearB(yearB);
							}
						}

						System.out.println("Enter month of Birth:");
						monthB = 0;
						while (monthB == 0) {
							jn = new Scanner(System.in);
							if (jn.hasNextInt()) {
								monthB = jn.nextInt();
								other.setMonthB(monthB);
							}
						}

						System.out.println("Enter day of Birth:");
						dayB = 0;
						while (dayB == 0) {
							jn = new Scanner(System.in);
							if (jn.hasNextInt()) {
								dayB = jn.nextInt();
								other.setDayB(dayB);
							}
						}

						other.setBirthDate();
						System.out.println("Enter year of Hire:");
						yearH = 0;
						while (yearH == 0) {
							jn = new Scanner(System.in);
							if (jn.hasNextInt()) {
								yearH = jn.nextInt();
								other.setYearH(yearH);
							}
						}

						System.out.println("Enter month of Hire:");
						monthH = 0;
						while (monthH == 0) {
							jn = new Scanner(System.in);
							if (jn.hasNextInt()) {
								monthH = jn.nextInt();
								other.setMonthH(monthH);
							}
						}

						System.out.println("Enter day of Hire:");
						dayH = 0;
						while (dayH == 0) {
							jn = new Scanner(System.in);
							if (jn.hasNextInt()) {
								dayH = jn.nextInt();
								other.setDayH(dayH);
							}
						}

						other.setHireDate();

						System.out.println("Enter Description:");
						other.setDescription(in.next());

						other.setId(maxId + 1);

						System.out.println("Save: " + other);
						System.out.println("Y/N ?");
						s = in.next();
						if (s.equals("Y") || s.equals("y")) {
							listEmployeParse.add(other);
							System.out.println("Other saved");
						} else
							System.out.println("No");
						break;
					// default:
					case 0:
						System.out.println("back to main menu");
						break;
					}
				} else {

					System.out.println("'1' - add worker; '2' - add manager; '3'- add other; '0' - Back");

					n = in.nextInt();

					String type = null;
					switch (n) {
					case 1:
						type = "Worker";
						break;
					case 2:
						type = "Manager";
						break;
					case 3:
						type = "Other";
						break;

					case 0:
						System.out.println("back to main menu");
						break;
					}

					if (type != null) {
						
						UploadEmploueFile upEmp = new UploadEmploueFile(path);

						try {
							upEmp.madeXMLWorker(type);
						} catch (ParserConfigurationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						System.out.println("Please fill the file: " + new File("employe.xml").toURI().getPath());

						System.out.println("Did you fill file? : Y/N?");
						s = in.next();
						if (s.equals("Y") || s.equals("y")) {
							DownEmploye downEmploye = new DownEmploye(path);
							downEmploye.parsXMLEmploye();
							type = downEmploye.getType();

							if (type.equals("Worker")) {
								Employe worker = downEmploye.getEmploye();

								worker.setId(maxId + 1);

								System.out.println("Save: " + worker);
								System.out.println("Y/N ?");
								s = in.next();
								if (s.equals("Y") || s.equals("y")) {
									listEmployeParse.add(worker);
									System.out.println("Worker saved");
									break;
								} else {
									System.out.println("Don't saved");
									break;
								}

							} else if (type.equals("Manager")) {
								Employe manager = downEmploye.getEmploye();

								manager.setId(maxId + 1);

								System.out.println("Save: " + manager);
								System.out.println("Y/N ?");
								s = in.next();
								if (s.equals("Y") || s.equals("y")) {
									listEmployeParse.add(manager);
									System.out.println("Manager saved");
									break;
								} else {
									System.out.println("Don't saved");
									break;
								}

							} else {

								Other other = new Other(downEmploye.getEmploye().getName(),
										downEmploye.getEmploye().getSurname(), downEmploye.getEmploye().getPatronymic(),
										downEmploye.getEmploye().getBirthDate(),
										downEmploye.getEmploye().getHireDate());

								other.setDescription(downEmploye.getDescription());
								other.setId(maxId + 1);

								System.out.println("Save: " + other);
								System.out.println("Y/N ?");
								s = in.next();
								if (s.equals("Y") || s.equals("y")) {
									listEmployeParse.add(other);
									System.out.println("Manager saved");
									break;
								} else {
									System.out.println("Don't saved");
									break;
								}

							}

						}

					}
				}
				break;

			case 2:
				System.out.println("Select ID of employe for DEL");
				n = in.nextInt();
				if (n <= listEmployeParse.size()) {

					int l = 0;
					for (l = 0; l < listEmployeParse.size(); l++) {
						if (listEmployeParse.get(l).getId() == n)
							break;
					}

					n = l;

					System.out.println("You want del emploey ID:");
					System.out.printf("%-2s|%-9s|%-10s|%-15s|%-15s|%-12s|%-12s|\n", "employe ID", "Type", "Name",
							"Surname", "Patronymic", "Birth Date", "Hire Date");
					System.out.printf("%-2s|%-9s|%-10s|%-15s|%-15s|%-12s|%-12s|\n", "----------", "---------",
							"----------", "---------------", "---------------", "------------", "------------");
					System.out.printf("%-10s|%-9s|%-10s|%-15s|%-15s|%-12s|%-12s|\n", listEmployeParse.get(n).getId(),
							listEmployeParse.get(n).getType(), listEmployeParse.get(n).getName(),
							listEmployeParse.get(n).getSurname(), listEmployeParse.get(n).getPatronymic(),
							formatter.format(listEmployeParse.get(n).getBirthDate()),
							formatter.format(listEmployeParse.get(n).getHireDate()));
					System.out.println("DEL: Y/N ?");
					s = in.next();
					if (s.equals("Y") || s.equals("y")) {
						System.out.println(listEmployeParse.size());
						System.out.println(n);
						System.out.println("До: " + listEmployeParse.size());
						listEmployeParse.remove((n));
						System.out.println("После: " + listEmployeParse.size());
						// listEmployeParse.remove(n)
					}

				}
				System.out.println("Del employ");

				break;
			case 3: {
				System.out.println("Change");
				System.out.println("Select ID for change type of employe");
				n = in.nextInt();
				if (n <= listEmployeParse.size()) {

					int l = 0;
					int id = n;
					for (l = 0; l < listEmployeParse.size(); l++) {
						if (listEmployeParse.get(l).getId() == n)
							break;
					}

					n = l;

					System.out.println("You want change type of employe:");
					System.out.printf("%-2s|%-9s|%-10s|%-15s|%-15s|%-12s|%-12s|\n", "employe ID", "Type", "Name",
							"Surname", "Patronymic", "Birth Date", "Hire Date");
					System.out.printf("%-2s|%-9s|%-10s|%-15s|%-15s|%-12s|%-12s|\n", "----------", "---------",
							"----------", "---------------", "---------------", "------------", "------------");
					System.out.printf("%-10s|%-9s|%-10s|%-15s|%-15s|%-12s|%-12s|\n", listEmployeParse.get(n).getId(),
							listEmployeParse.get(n).getType(), listEmployeParse.get(n).getName(),
							listEmployeParse.get(n).getSurname(), listEmployeParse.get(n).getPatronymic(),
							formatter.format(listEmployeParse.get(n).getBirthDate()),
							formatter.format(listEmployeParse.get(n).getHireDate()));
					System.out.println("Change type emploea to: '1' - Worker; '2' - Manager; '3' - Other; '0' - back;");
					m = in.nextInt();
					switch (m) {
					case 1: {
						Employe workerCh = new Worker();
						workerCh.setType("Worker");
						workerCh.setId(id);
						workerCh.setName(listEmployeParse.get(n).getName());
						workerCh.setPatronymic(listEmployeParse.get(n).getPatronymic());
						workerCh.setSurname(listEmployeParse.get(n).getSurname());
						workerCh.setBirthDate(listEmployeParse.get(n).getBirthDate());
						workerCh.setHireDate(listEmployeParse.get(n).getHireDate());
						System.out.println("Change: Y/N ?");
						s = in.next();
						if (s.equals("Y") || s.equals("y")) {
							listEmployeParse.set(n, workerCh);
							System.out.println("Change type >>> Worker");
						} else
							break;

					}
						break;

					case 2: {
						Employe managerCh = new Manager();
						managerCh.setType("Manager");
						managerCh.setId(id);
						managerCh.setName(listEmployeParse.get(n).getName());
						managerCh.setPatronymic(listEmployeParse.get(n).getPatronymic());
						managerCh.setSurname(listEmployeParse.get(n).getSurname());
						managerCh.setBirthDate(listEmployeParse.get(n).getBirthDate());
						managerCh.setHireDate(listEmployeParse.get(n).getHireDate());
						System.out.println("Change: Y/N ?");
						s = in.next();
						if (s.equals("Y") || s.equals("y")) {
							listEmployeParse.set(n, managerCh);
							System.out.println("Change type >>> Manager");
						} else
							break;

					}
						break;

					case 3: {
						Other otherCh = new Other();
						otherCh.setType("Other");
						otherCh.setId(id);
						otherCh.setName(listEmployeParse.get(n).getName());
						otherCh.setPatronymic(listEmployeParse.get(n).getPatronymic());
						otherCh.setSurname(listEmployeParse.get(n).getSurname());
						otherCh.setBirthDate(listEmployeParse.get(n).getBirthDate());
						otherCh.setHireDate(listEmployeParse.get(n).getHireDate());
						otherCh.setId(listEmployeParse.get(n).getId());
						otherCh.setDescription("In past: " + listEmployeParse.get(n).getType());
						System.out.println("Change: Y/N ?");
						s = in.next();
						if (s.equals("Y") || s.equals("y")) {
							listEmployeParse.set(n, otherCh);
							System.out.println("Change type of>>>");
						} else
							break;
					}
						break;

					case 0:
						break;
					}
				}
			}
				break;

			case 4: {
				System.out.println("Add Worker to Manager");

				System.out.println("Set of Manager");
				n = in.nextInt();
				System.out.println("Size: " + listEmployeParse.size());
				int l = 0;
				for (l = 0; l < listEmployeParse.size(); l++) {
					if (listEmployeParse.get(l).getId() == n)
						break;
				}

				n = l;

				if (n <= listEmployeParse.size() && ((listEmployeParse.get(n).getType().equals("Manager")))) {

					{

						System.out.println("You want add worker to manager:");
						System.out.printf("%-2s|%-9s|%-10s|%-15s|%-15s|%-12s|%-12s|\n", "employe ID", "Type", "Name",
								"Surname", "Patronymic", "Birth Date", "Hire Date");
						System.out.printf("%-2s|%-9s|%-10s|%-15s|%-15s|%-12s|%-12s|\n", "----------", "---------",
								"----------", "---------------", "---------------", "------------", "------------");
						System.out.printf("%-10s|%-9s|%-10s|%-15s|%-15s|%-12s|%-12s|\n",
								listEmployeParse.get(n).getId(), listEmployeParse.get(n).getType(),
								listEmployeParse.get(n).getName(), listEmployeParse.get(n).getSurname(),
								listEmployeParse.get(n).getPatronymic(),
								formatter.format(listEmployeParse.get(n).getBirthDate()),
								formatter.format(listEmployeParse.get(n).getHireDate()));

						System.out.println("Please set ID Worker for add");

						listEmployeParse = downXML.getListEmployeParse();

						System.out.printf("%-2s|%-9s|%-10s|%-15s|%-15s|%-12s|%-12s|\n", "----------", "---------",
								"----------", "---------------", "---------------", "------------", "------------");

						for (Employe employes : listEmployeParse) {
							if (employes.getType().equals("Worker")) {

								System.out.printf("%-10s|%-9s|%-10s|%-15s|%-15s|%-12s|%-12s|\n", employes.getId(),
										employes.getType(), employes.getName(), employes.getSurname(),
										employes.getPatronymic(), formatter.format(employes.getBirthDate()),
										formatter.format(employes.getHireDate()));
								System.out.printf("%-2s|%-9s|%-10s|%-15s|%-15s|%-12s|%-12s|\n", "----------",
										"---------", "----------", "---------------", "---------------", "------------",
										"------------");
							}
						}

						int w = in.nextInt();
						Manager managerADD = new Manager();

						Employe workerADD = new Worker();

						workerADD.setId(listEmployeParse.get(w).getId());
						workerADD.setType(listEmployeParse.get(w).getType());
						workerADD.setName(listEmployeParse.get(w).getName());
						workerADD.setSurname(listEmployeParse.get(w).getSurname());
						workerADD.setPatronymic(listEmployeParse.get(w).getPatronymic());
						workerADD.setBirthDate(listEmployeParse.get(w).getBirthDate());
						workerADD.setHireDate(listEmployeParse.get(w).getHireDate());

						listWorker = downXML.getWorkerOfManager(n);
						boolean mark = true;

						for (int wd = 0; wd < listWorker.size(); wd++)

							if (listWorker.get(wd).getId() == w) {

								mark = false;

							}

						if (mark) {

							listWorker.add(workerADD);

							managerADD.setId(listEmployeParse.get(n).getId());
							managerADD.setType(listEmployeParse.get(n).getType());
							managerADD.setName(listEmployeParse.get(n).getName());
							managerADD.setSurname(listEmployeParse.get(n).getSurname());
							managerADD.setPatronymic(listEmployeParse.get(n).getPatronymic());
							managerADD.setBirthDate(listEmployeParse.get(n).getBirthDate());
							managerADD.setHireDate(listEmployeParse.get(n).getHireDate());
							managerADD.setListWorker(listWorker);

							listEmployeParse.set(n, managerADD);

						}
					}

					break;
				}

			}
				break;

			case 6: {

				sortById(listEmployeParse);
			}
				break;

			case 7: {

				sortBySurname(listEmployeParse);
				sort = false;
			}
				break;

			case 8: {

				sortByHireDate(listEmployeParse);
				sort = false;
			}
				break;

			case 9: {

				System.exit(0);
			}
				break;

			}

			System.out.println("UpLoadXML");
			try {
				upXML.makeXML(listEmployeParse);
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}

		}
		in.close();

	}

	private static List<Employe> sortById(List<Employe> listEmployeParse) {

		Collections.sort(listEmployeParse, Employe.compareById);
		return listEmployeParse;
	}

	private static List<Employe> sortBySurname(List<Employe> listEmployeParse) {

		Collections.sort(listEmployeParse, Employe.compareBySurname);
		return listEmployeParse;

	}

	private static List<Employe> sortByHireDate(List<Employe> listEmployeParse) {

		Collections.sort(listEmployeParse, Employe.compareByHireDate);
		return listEmployeParse;

	}

}
