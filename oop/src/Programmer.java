import java.util.Date;

public class Programmer extends Employee implements Bonus{
    private static final long serialVersionUID = 1L;
    private double dailyRate; // 日薪
    private int projectCount; // 项目数量

    public Programmer(int id, String name, Date hireDate, double dailyRate, int projectCount) {
        super(id, name, hireDate);
        this.dailyRate = dailyRate;
        this.projectCount = projectCount;
    }    

    @Override
    public double calculateSalary() {
        return dailyRate * 22; // 月工作22天
    }

    @Override
    public double calculateBonus() {
        return projectCount * 800;
    }

    // setter方法
    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate
        ;
    }

    public void setProjectCount(int projectCount) {
        this.projectCount = projectCount;
    }

    @Override
    public String toString() {
        return "【程序员】" + super.toString() + "，日薪：" + dailyRate + "，项目数量：" + projectCount;
    }
}