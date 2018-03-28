package task2;

import java.util.ArrayList;
import java.util.Date;

public class Manager extends Employe {

	private ArrayList<Employe> listWorker = new ArrayList();
	private String type = "Manager";

	public Manager() {
		super();
	}

	public Manager(String name, String surname, String patronymic, int yearB, int monthB, int dayB, int yearH,
			int monthH, int dayH) {
		super(name, surname, patronymic, yearB, monthB, dayB, yearH, monthH, dayH);
	}

	public Manager(String name, String surname, String patronymic, Date birthDate, Date hireDate, String type) {
		super(name, surname, patronymic, birthDate, hireDate, type);
	}

	public ArrayList<Employe> getListWorker() {
		return listWorker;
	}

	public void setListWorker(ArrayList<Employe> listWorker) {
		this.listWorker = listWorker;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return getType() + " [Name: " + getName() + ", Surname: " + getSurname() + ", Patronymic: " + getPatronymic()
				+ ", BirthDate: " + getBirthDate() + ", HireDate: " + getHireDate() + ", listWorker: " + listWorker
				+ "]";
	}

}
