
import java.util.Date;

public class Manager extends Employee implements Bonus{
  private static final long serialVersionUID = 1L;
  private double baseSalary;
  private double performance;

  public Manager(int id, String name, Date hirDate, double baseSalary, double performance){
    super(id, name, hirDate);
    this.baseSalary = baseSalary;
    this.performance = performance;
  }

  @Override
  public double calculateSalary(){
    return baseSalary * performance;
  }

  @Override
  public double calculateBonus(){
    return baseSalary * 0.3 * performance;
  }

  public void setBaseSalary(double baseSalary){
    this.baseSalary = baseSalary;
  }

  public void setPerformance(double performance){
    this.performance = performance;
  }

  @Override
  public String toString(){
    return "【经理】" + super.toString() + "， 基本工资：" + baseSalary + "，绩效：" + performance;
  }
}
