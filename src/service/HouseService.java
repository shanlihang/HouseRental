package service;

import domain.House;
import views.HouseView;

public class HouseService {
    private House[] houses;
    private int houseNum;//记录当前有多少的房屋信息
    private int idCounter;//计数器
    public HouseService(int size){
        houses = new House[size];
    }
    public House[] list(){
        return houses;
    }
    public boolean add(House newHouse){
        if(houseNum>=houses.length){
            System.out.println("数组已满，添加失败");
            return false;
        }
        houses[houseNum] = newHouse;
        houseNum++;
        idCounter++;
        newHouse.setId(idCounter);
        return true;
    }
    public boolean del(int delId){
        int index = -1;
        for(int i = 0;i<houseNum;i++){
            if(delId == houses[i].getId()){
                index = i;
            }
        }
        if(index == -1){
            return false;
        }
        for(int i = index;i<houseNum-1;i++){
             houses[i] = houses[i+1];
        }
        houses[houseNum-1] = null;
        houseNum--;
        return true;
    }
    public House findById(int findId){
        for (int i=0;i<houseNum;i++){
            if(findId == houses[i].getId()){
                return houses[i];
            }
        }
        return null;
    }
    public void update(){}
}
