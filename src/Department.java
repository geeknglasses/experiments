
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

@Entity
public class Department {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy="department")
    @MapKey(name="name")
    private Map<String, Student> students;

    public Department() {
        students = new HashMap<String, Student>();
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String deptName) {
        this.name = deptName;
    }
    
    public void addStudent(Student student) {
        if (!getStudents().containsKey(student.getName())) {
            getStudents().put(student.getName(), student);
            if (student.getDepartment() != null) {
                student.getDepartment().getStudents().remove(student.getName());
            }
            student.setDepartment(this);
        }
    }
    
    public Map<String,Student> getStudents() {
        return students;
    }

    public String toString() {
        return "Department id: " + getId() + 
               ", name: " + getName();
    }
}
