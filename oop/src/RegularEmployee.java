import java.util.Date;

public class RegularEmployee extends Employee{
  private static final long serialVersionUID = 1L;
  private double monthlySalary;

  public RegularEmployee(int id, String name, Date hireDate, double monthlySalary){
    super(id, name, hireDate);
    this.monthlySalary = monthlySalary;
  }

  @Override
  public double calculateSalary(){
    return monthlySalary;
  }

  public void setMonthlySalary(double monthlySalary){
    this.monthlySalary = monthlySalary;
  }

  @Override
  public String toString(){
    return "【普通员工】" + super.toString() + "，月薪：" + monthlySalary;
  }
}
