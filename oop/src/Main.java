import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // 欢迎界面
        System.out.println("===== 欢迎使用企业职工管理系统 =====");

        while (running) {
            System.out.println("\n请选择操作：");
            System.out.println("0 -- 退出");
            System.out.println("1 -- 增加职工（支持批量）");
            System.out.println("2 -- 删除职工（按编号）");
            System.out.println("3 -- 显示所有职工信息");
            System.out.println("4 -- 修改职工信息（按编号）");
            System.out.println("5 -- 查找职工（按编号/姓名）");
            System.out.println("6 -- 按职工编号排序（升序/降序）");
            System.out.println("7 -- 清空文档");
            System.out.print("请输入选项（0-7）：");

            int choice = scanner.nextInt();
            try {
                switch (choice) {
                    case 0:
                        running = false;
                        System.out.println("系统已退出，感谢使用！");
                        break;
                    case 1:
                        manager.batchAddEmployees();
                        break;
                    case 2:
                        System.out.print("请输入要删除的职工编号：");
                        int delId = scanner.nextInt();
                        manager.deleteEmployee(delId);
                        break;
                    case 3:
                        manager.showAllEmployees();
                        break;
                    case 4:
                        manager.updateEmployee();
                        break;
                    case 5:
                        System.out.print("请选择查找方式（1-按编号 2-按姓名）：");
                        int findType = scanner.nextInt();
                        scanner.nextLine();
                        if (findType == 1) {
                            System.out.print("请输入职工编号：");
                            int id = scanner.nextInt();
                            Employee emp = manager.findEmployeeById(id);
                            System.out.println("查询结果：" + emp);
                            if (emp instanceof Bonus) {
                                System.out.println("奖金：" + ((Bonus) emp).calculateBonus() + "元");
                            }
                        } else if (findType == 2) {
                            System.out.print("请输入职工姓名（支持模糊查询）：");
                            String name = scanner.nextLine();
                            List<Employee> result = manager.findEmployeesByName(name);
                            if (result.isEmpty()) {
                                System.out.println("未找到匹配的职工！");
                            } else {
                                System.out.println("查询到" + result.size() + "条结果：");
                                result.forEach(System.out::println);
                            }
                        } else {
                            System.out.println("查找方式错误！");
                        }
                        break;
                    case 6:
                        manager.sortEmployeesByid();
                        break;
                    case 7:
                        System.out.print("确定要清空所有记录吗？（1-确定 0-取消）：");
                        if (scanner.nextInt() == 1) {
                            manager.clearFile();
                        } else {
                            System.out.println("已取消清空操作");
                        }
                        break;
                    default:
                        System.out.println("输入错误，请重新选择！");
                }
            } catch (Exception e) {
                System.out.println("操作失败：" + e.getMessage());
            }
        }
        scanner.close();
    }
}