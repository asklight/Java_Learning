

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Employee implements Serializable{
  private static final long serialVersionUID = 1L;
  private final int id;
  private final String name;
  private final Date hireDate;

  public Employee(int id, String name, Date hireDate){
    this.id = id;
    this.name = name;
    this.hireDate = hireDate;
  }

  public abstract double calculateSalary();

  public int getId(){
    return id;
  }

  public String getName(){
    return name;
  }

  public Date getHireDate(){
    return hireDate;
  }

  @Override
  public String toString(){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return "编号："+"，姓名："+"，入职日期："+sdf.format(hireDate);
  }
}

