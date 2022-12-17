package com.rjgc.eycs.dto;


import com.rjgc.eycs.entity.Setmeal;
import com.rjgc.eycs.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
