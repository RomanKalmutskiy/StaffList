package task2;

import java.util.Date;

public class Worker extends Employe {
	private String type = "Worker";

	public Worker() {
		super();

	}

	public Worker(String name, String surname, String patronymic, int yearB, int monthB, int dayB, int yearH,
			int monthH, int dayH) {
		super(name, surname, patronymic, yearB, monthB, dayB, yearH, monthH, dayH);

	}

	public Worker(String name, String surname, String patronymic, Date birthDate, Date hireDate, String type) {
		super(name, surname, patronymic, birthDate, hireDate, type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return getType() + " [Name=" + getName() + ", Surname=" + getSurname() + ", Patronymic=" + getPatronymic()
				+ ", BirthDate=" + getBirthDate() + ", HireDate=" + getHireDate() + "]";
	}

}
