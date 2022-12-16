package com.rjgc.eycs.dto;


import com.rjgc.eycs.entity.Dish;
import com.rjgc.eycs.entity.DishFlavor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
