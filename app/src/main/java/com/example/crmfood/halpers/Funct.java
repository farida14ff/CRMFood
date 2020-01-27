package com.example.crmfood.halpers;

import com.example.crmfood.models.AddMealList;
import com.example.crmfood.models.Basket;
import com.example.crmfood.models.SubMenu;

import java.util.ArrayList;
import java.util.List;

public class Funct {

    public static Basket getBasket2(SubMenu subMenu){
        Basket basket2 = new Basket();
        basket2.setMealId(subMenu.getSub_id());
        basket2.setBasket_name(subMenu.getSub_name());
        basket2.setBasket_price(subMenu.getSub_price());
        basket2.setOrderedQuantity(0);
        return basket2;
    }

    public static List<Basket> getBusketList(List<SubMenu> d){
        List g = new ArrayList<Basket>();
        for (SubMenu s: d){
            g.add(getBasket2(s));
        }
        return g;
    }

    public static List<AddMealList> getAddBusketList(List<SubMenu> d){
        List g = new ArrayList<AddMealList>();
        for (SubMenu s: d){
            g.add(getBasket2(s));
        }
        return g;
    }
}
