package task2;

import java.util.Date;

public class Other extends Employe {

	private String description;
	private String type = "Other";

	public Other() {
		super();
	}

	public Other(String name, String surname, String patronymic, Date birthDate, Date hireDate) {
		super(name, surname, patronymic, birthDate, hireDate);
	}

	public Other(String name, String surname, String patronymic, int yearB, int monthB, int dayB, int yearH, int monthH,
			int dayH) {
		super(name, surname, patronymic, yearB, monthB, dayB, yearH, monthH, dayH);
	}

	public Other(String name, String surname, String patronymic, Date birthDate, Date hireDate, String type) {
		super(name, surname, patronymic, birthDate, hireDate, type);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return getType() + " [getName()=" + getName() + ", getSurname()=" + getSurname() + ", getPatronymic()="
				+ getPatronymic() + ", getBirthDate()=" + getBirthDate() + ", getHireDate()=" + getHireDate()
				+ "description=" + description + "]";
	}

}
