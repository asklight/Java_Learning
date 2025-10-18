
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeManager {
  private List<Employee> employees = new ArrayList<>();
  private final Scanner scanner = new Scanner(System.in);
  private static final String FILE_PATH = "employees.dat";

  public EmployeeManager(){
    loadFromFile();
  }

  public void batchAddEmployees() throws DuplicateIdException{
    System.out.print("请输入批量添加的职工数量：");
    int count = scanner.nextByte();
    scanner.nextLine();

    for(int i = 0; i < count; i++){
      System.out.println("\n-----添加第" + (i+1)+"个职工-----");
      addEmployeeByConsole();
    }
  }

  private void addEmployeeByConsole() throws DuplicateIdException{
    System.out.print("请选择职工类型（1-普通员工 2-经理 3-程序员 4-总裁）：");
    int type = scanner.nextInt();
    scanner.nextLine();

    System.out.print("请输入职工编号：");
    int id = scanner.nextInt();
    scanner.nextLine(); 
    System.out.print("请输入姓名：");
    String name = scanner.nextLine();
    Date hireDate = new Date();

    switch (type) {
        case 1:
            System.out.print("请输入月薪：");
            double monthlySalary = scanner.nextDouble();
            addEmployee(new RegularEmployee(id, name, hireDate, monthlySalary));
            break;
        case 2:
            System.out.print("请输入基本工资：");
            double baseSalary = scanner.nextDouble();
            System.out.print("请输入团队绩效（如1.2）：");
            double performance = scanner.nextDouble();
            addEmployee(new Manager(id, name, hireDate, baseSalary, performance));
            break;
        case 3:
            System.out.print("请输入日薪：");
            double dailyRate = scanner.nextDouble();
            System.out.print("请输入当月项目数量：");
            int projectCount = scanner.nextInt();
            addEmployee(new Programmer(id, name, hireDate, dailyRate, projectCount));
            break;
        case 4:
            System.out.print("请输入年薪：");
            double annualSalary = scanner.nextDouble();
            System.out.print("请输入利润分成比例（如0.1）：");
            double profitShare = scanner.nextDouble();
            addEmployee(new President(id, name, hireDate, annualSalary, profitShare));
            break;
        default:
            System.out.println("类型错误，添加失败！");
    }
  }

  private void addEmployee(Employee employee) throws DuplicateIdException{
    for(Employee e:employees){
      if(e.getId()==employee.getId()){
        throw new DuplicateIdException("编号"+ employee.getId()+ "已存在！");
      }
    }
    employees.add(employee);
    saveToFile();
    System.out.println("添加成功！");
  }

  // 2. 删除职工（按编号）
  public void deleteEmployee(int id) throws EmployeeNotFoundException {
    Employee emp = findEmployeeById(id);
    employees.remove(emp);
    saveToFile();
    System.out.println("删除成功！");
}

// 3. 显示所有职工信息
public void showAllEmployees() {
    if (employees.isEmpty()) {
        System.out.println("暂无职工信息（文件为空或未添加记录）！");
        return;
    }
    System.out.println("\n===== 所有职工信息 =====");
    for (Employee e : employees) {
        System.out.println(e);
    }
}

// 4. 修改职工信息（按编号）
public void updateEmployee() throws EmployeeNotFoundException {
    System.out.print("请输入要修改的职工编号：");
    int id = scanner.nextInt();
    scanner.nextLine();

    Employee emp = findEmployeeById(id);
    System.out.println("当前信息：" + emp);

    // 根据类型修改属性
    if (emp instanceof RegularEmployee) {
        System.out.print("请输入新月薪：");
        ((RegularEmployee) emp).setMonthlySalary(scanner.nextDouble());
    } else if (emp instanceof Manager) {
        System.out.print("请输入新基本工资：");
        ((Manager) emp).setBaseSalary(scanner.nextDouble());
        System.out.print("请输入新团队绩效：");
        ((Manager) emp).setPerformance(scanner.nextDouble());
    } else if (emp instanceof Programmer) {
        System.out.print("请输入新日薪：");
        ((Programmer) emp).setDailyRate(scanner.nextDouble());
        System.out.print("请输入新项目数量：");
        ((Programmer) emp).setProjectCount(scanner.nextInt());
    } else if (emp instanceof President) {
        System.out.print("请输入新年薪：");
        ((President) emp).setAnnualSalary(scanner.nextDouble());
        System.out.print("请输入新利润分成比例：");
        ((President) emp).setProfitShare(scanner.nextDouble());
    }

    saveToFile();
    System.out.println("修改成功！");
}

// 5. 查找职工（按编号）
public Employee findEmployeeById(int id) throws EmployeeNotFoundException {
    for (Employee e : employees) {
        if (e.getId()== id ) {
            return e;
        }
    }
    throw new EmployeeNotFoundException("未找到编号" + id + "的职工！");
}

// 5. 查找职工（按姓名，模糊匹配）
public List<Employee> findEmployeesByName(String name) {
    return employees.stream()
            .filter(emp -> emp.getName().contains(name))
            .collect(Collectors.toList());
}

// 6. 按职工编号排序（升序/降序）
public void sortEmployeesByid() {
    System.out.print("请选择排序方式（1-升序 2-降序）：");
    int choice = scanner.nextInt();

    // 按编号字符串排序
    employees.sort(Comparator.comparing(Employee::getId));
    if (choice == 2) {
        Collections.reverse(employees); // 降序反转
    }

    saveToFile();
    System.out.println("排序完成！");
}

// 7. 清空文档
public void clearFile() {
    employees.clear();
    saveToFile();
    System.out.println("文档已清空！");
}

// 保存数据到文件（序列化）
private void saveToFile() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
        oos.writeObject(employees);
    } catch (IOException e) {
        System.out.println("保存文件失败：" + e.getMessage());
    }
}

// 从文件加载数据（反序列化）
@SuppressWarnings("unchecked")
private void loadFromFile() {
    File file = new File(FILE_PATH);
    if (!file.exists()) return;

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
        employees = (List<Employee>) ois.readObject();
        System.out.println("已从文件加载" + employees.size() + "条职工数据");
    } catch (IOException | ClassNotFoundException e) {
        System.out.println("加载文件失败：" + e.getMessage());
    }
}
}
