package views;
import domain.House;
import service.HouseService;
import utils.Utility;

import  java.util.Scanner;
public class HouseView {
    Scanner sc = new Scanner(System.in);
    private boolean flag = true;
    private char key = ' ';//记录用户选择
    private String exit;
    private HouseService houseService = new HouseService(10);
    public void mainMenu(){

        System.out.println("---------欢迎使用房屋租赁系统----------");
        do{
            System.out.println("请选择功能：");
            System.out.println("\t1.新 增 房 源");
            System.out.println("\t2.查 找 房 屋");
            System.out.println("\t3.删 除 房 屋");
            System.out.println("\t4.修改房屋信息");
            System.out.println("\t5.房 屋 列 表");
            System.out.println("\t6.退      出");
            System.out.println("请输入1-6：");
            key = Utility.readChar();
            switch (key){
                case '1':
                    System.out.println("---------新 增 房 屋----------");
                    addHouse();
                    break;
                case '2':
                    System.out.println("---------查 找 房 屋----------");
                    findHouse();
                    break;
                case '3':
                    System.out.println("---------删 除 房 屋----------");
                    delHouse();
                    break;
                case '4':
                    System.out.println("---------修 改 房 屋 信 息---------");
                    updateHouse();
                    break;
                case '5':
                    listHouses();
                    break;
                case '6':
                    System.out.println("您确定要退出吗？ y/n");
                    exit = sc.next();
                    if("y".equals(exit)){
                        flag = false;
                        System.out.println("已退出");
                    }
            }
        }while(flag);
        System.out.println("----------欢迎下次使用----------");
    }
    public void listHouses(){
        System.out.println("---------房 屋 列 表----------");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态(已出租/未出租)");
        House[] houses = houseService.list();
        for (int i = 0;i<houses.length;i++){
            if(houses[i] == null){
                break;
            }
            System.out.println(houses[i]);
        }
    }
    public void addHouse(){
        System.out.println("---------添 加 房 屋----------");
        System.out.print("请输入姓名：");
        String name = Utility.readString(8);
        System.out.print("请输入电话：");
        String phone = Utility.readString(11);
        System.out.print("请输入地址：");
        String address = Utility.readString(16);
        System.out.print("请输入租金：");
        int rent = Utility.readInt();
        System.out.print("请输入当前状态：");
        String state = Utility.readString(3);
        House newHouse = new House(0,name,phone,address,rent,state);
        if(houseService.add(newHouse)){
            System.out.println("----------添 加 成 功----------");
        }else{
            System.out.println("----------添 加 失 败----------");
        }
    }
    public void delHouse(){
        System.out.println("---------删 除 房 屋----------");
        System.out.println("请输入待删除房屋的编号(-1退出)");
        int delId = Utility.readInt();
        if(delId == -1){
            System.out.println("你放弃了删除");
            return;
        }
        char choice = Utility.readConfirmSelection();
        if(choice == 'y' || choice == 'Y'){
            if (houseService.del(delId)){
                System.out.println("----------删 除 成 功----------");
            }else{
                System.out.println("----------删 除 失 败----------");
            }
        }else {
            System.out.println("你放弃了删除");
        }
    }
    public void findHouse(){
        System.out.println("---------查 找 房 屋----------");
        System.out.println("请输入你要查找的id：");
        int findId = Utility.readInt();
        House house = houseService.findById(findId);
        if(house != null){
            System.out.println(house);
        }else {
            System.out.println("查找失败");
        }
    }
    public void updateHouse(){
        System.out.println("---------修 改 房 屋 信 息----------");
        System.out.println("请选择待修改房屋编号：（-1退出）");
        int updateId = Utility.readInt();
        if(updateId == -1){
            System.out.println("您放弃了修改");
            return;
        }
        House house = houseService.findById(updateId);
        if(house == null){
            System.out.println("您要修改的房屋信息不存在");
            return;
        }
        System.out.println("姓名(" + house.getName() + "):");
        String name = Utility.readString(8,"");//表示直接回车表示不修改
        if(!" ".equals(name)){
            house.setName(name);
        }
        System.out.println("电话(" + house.getPhone() + "):");
        String phone = Utility.readString(11,"");//表示直接回车表示不修改
        if(!" ".equals(phone)){
            house.setPhone(phone);
        }
        System.out.println("地址(" + house.getAddress() + "):");
        String address = Utility.readString(18,"");//表示直接回车表示不修改
        if(!" ".equals(address)){
            house.setName(address);
        }
        System.out.println("租金(" + house.getRent() + "):");
        int rent = Utility.readInt(-1);
        if(rent != -1){
            house.setRent(rent);
        }
        System.out.println("状态(" + house.getState() + "):");
        String state = Utility.readString(3,"");//表示直接回车表示不修改
        if(!" ".equals(state)){
            house.setName(state);
        }
    }
}
