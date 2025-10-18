import java.util.Date;

public class President extends Employee implements Bonus{
    private static final long serialVersionUID = 1L;
    private double annualSalary; // 年薪
    private double profitShare;  // 利润分成比例

    public President(int id, String name, Date hireDate, double annualSalary, double profitShare) {
        super(id, name, hireDate);
        this.annualSalary = annualSalary;
        this.profitShare = profitShare;
    }

    @Override
    public double calculateSalary() {
        return annualSalary / 12; // 月薪=年薪/12
    }

    @Override
    public double calculateBonus() {
        return annualSalary * profitShare; // 奖金=年薪×分成比例
    }

    // setter方法
    public void setAnnualSalary(double annualSalary) {
        this.annualSalary = annualSalary;
    }

    public void setProfitShare(double profitShare) {
        this.profitShare = profitShare;
    }

    @Override
    public String toString() {
        return "【总裁】" + super.toString() + "，年薪：" + annualSalary + "，利润分成：" + profitShare;
    }
}
