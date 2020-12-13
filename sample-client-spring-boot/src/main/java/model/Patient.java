package model;

/*@Data
@Entity
@Table(name="Patient")*/
public class Patient {
	
	/* @Column(name="pname") */
	private String pname;
	
	/*
	 * @Id
	 * 
	 * @GeneratedValue(generator = "Patient")
	 * 
	 * @SequenceGenerator(name = "patient",sequenceName = "patient_seq")
	 * 
	 * @Column(name="pid")
	 */
	private long pid;
	
	/* @Column(name="pdate") */
	private String pdate;
	
	/* @Column(name="gender") */
	private String gender;

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public String getPdate() {
		return pdate;
	}

	public void setPdate(String pdate) {
		this.pdate = pdate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
	

}
