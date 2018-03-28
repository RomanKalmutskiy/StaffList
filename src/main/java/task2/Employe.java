package task2;

import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;



public class Employe {
	private String name;
	private String surname;
	private String patronymic;
	private Date birthDate;
	private Date hireDate;
	private int yearB;
	private int monthB;
	private int dayB;
	private int yearH;
	private int monthH;
	private int dayH;
	private String type;
	private Integer id;
	
	

	public Employe() {
		
	}


	public Employe(String name, String surname, String patronymic, int yearB, int monthB, int dayB, int yearH, int monthH, int dayH) {
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;		
		
	}

	
	
	
	public Employe(String name, String surname, String patronymic, Date birthDate, Date hireDate, String type) {
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
		this.birthDate = birthDate;
		this.hireDate = hireDate;
		this.type = type;
	}


	public Employe(String name, String surname, String patronymic, Date birthDate, Date hireDate) {
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
		this.birthDate = birthDate;
		this.hireDate = hireDate;
		
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate() {
		GregorianCalendar calendarB = new GregorianCalendar(yearB, monthB-1, dayB);
		birthDate  = calendarB.getTime();
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate() {
		GregorianCalendar calendarH = new GregorianCalendar(yearH, monthH, dayH);
		hireDate  = calendarH.getTime();
	}

	public int getYearB() {
		return yearB;
	}


	public void setYearB(int yearB) {
		this.yearB = yearB;
	}


	public int getMonthB() {
		return monthB;
	}


	public void setMonthB(int monthB) {
		this.monthB = monthB;
	}


	public int getDayB() {
		return dayB;
	}


	public void setDayB(int dayB) {
		this.dayB = dayB;
	}


	public int getYearH() {
		return yearH;
	}


	public void setYearH(int yearH) {
		this.yearH = yearH;
	}


	public int getMonthH() {
		return monthH;
	}


	public void setMonthH(int monthH) {
		this.monthH = monthH;
	}


	public int getDayH() {
		return dayH;
	}


	public void setDayH(int dayH) {
		this.dayH = dayH;
	}

	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}


	@Override
	public String toString() {
		return "Employees [name=" + name + ", surname=" + surname + ", patronymic=" + patronymic + ", birthDate="
				+ birthDate + ", hireDate=" + hireDate + "]";
	}
	
	
	public static Comparator<Employe>  compareBySurname = new Comparator<Employe>(){
		public int compare(Employe one, Employe other){
			return one.surname.compareTo(other.surname);
		}
	};
	
	
	public static Comparator<Employe> compareByHireDate = new Comparator<Employe>(){
		public int compare(Employe one, Employe other){
			return one.hireDate.compareTo(other.hireDate);
		}
	};
	
	public static Comparator<Employe> compareById = new Comparator<Employe>(){
		public int compare(Employe one, Employe other){
			return one.id.compareTo(other.id);
		}
	};
	
	
	
}
