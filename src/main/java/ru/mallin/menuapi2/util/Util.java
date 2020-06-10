package ru.mallin.menuapi2.util;

import ru.mallin.menuapi2.entity.Ingredient;

import java.util.List;

public class Util {

    public void convertIngredientValuesToOneType(List<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients) {

            if (ingredient.getTitle().toLowerCase().contains("карто") && ingredient.getMeasure().getTitle().equals("ШТ")) {
                ingredient.setAmount(ingredient.getAmount() * 100);
                ingredient.getMeasure().setTitle("ГР");
            } else if (ingredient.getTitle().toLowerCase().equals("куриное филе")) {
                if (ingredient.getMeasure().getTitle().equals("УП")) {
                    ingredient.setAmount(ingredient.getAmount() * 850);
                    ingredient.getMeasure().setTitle("ГР");
                } else if (ingredient.getMeasure().getTitle().equals("ШТ")) {
                    ingredient.setAmount(ingredient.getAmount() * 300);
                    ingredient.getMeasure().setTitle("ГР");
                }
            } else if (ingredient.getTitle().toLowerCase().contains("молоко")) {
                if (ingredient.getMeasure().getTitle().equals("СТАКАН")) {
                    ingredient.setAmount(ingredient.getAmount() * 200);
                    ingredient.getMeasure().setTitle("ГР");
                } else if (ingredient.getMeasure().getTitle().equals("СТ_ЛОЖ")) {
                    ingredient.setAmount(ingredient.getAmount() * 18);
                    ingredient.getMeasure().setTitle("ГР");
                }
            } else if (ingredient.getTitle().toLowerCase().contains("мука")) {
                if (ingredient.getMeasure().getTitle().equals("СТАКАН")) {
                    ingredient.setAmount(ingredient.getAmount() * 130);
                    ingredient.getMeasure().setTitle("ГР");
                } else if (ingredient.getMeasure().getTitle().equals("СТ_ЛОЖ")) {
                    ingredient.setAmount(ingredient.getAmount() * 25);
                    ingredient.getMeasure().setTitle("ГР");
                }
            } else if (ingredient.getTitle().toLowerCase().contains("овсяные хлопья")) {
                if (ingredient.getMeasure().getTitle().equals("СТАКАН")) {
                    ingredient.setAmount(ingredient.getAmount() * 70);
                    ingredient.getMeasure().setTitle("ГР");
                } else if (ingredient.getMeasure().getTitle().equals("СТ_ЛОЖ")) {
                    ingredient.setAmount(ingredient.getAmount() * 12);
                    ingredient.getMeasure().setTitle("ГР");
                }
            } else if (ingredient.getTitle().toLowerCase().contains("разрыхлитель") && ingredient.getMeasure().getTitle().equals("Ч_ЛОЖ")) {
                ingredient.setAmount(ingredient.getAmount() * 5);
                ingredient.getMeasure().setTitle("ГР");
            } else if (ingredient.getTitle().toLowerCase().contains("рис")) {
                if (ingredient.getMeasure().getTitle().equals("СТАКАН")) {
                    ingredient.setAmount(ingredient.getAmount() * 200);
                    ingredient.getMeasure().setTitle("ГР");
                } else if (ingredient.getMeasure().getTitle().equals("СТ_ЛОЖ")) {
                    ingredient.setAmount(ingredient.getAmount() * 25);
                    ingredient.getMeasure().setTitle("ГР");
                }
            } else if (ingredient.getTitle().toLowerCase().contains("сахар")) {
                if (ingredient.getMeasure().getTitle().equals("Ч_ЛОЖ")) {
                    ingredient.setAmount(ingredient.getAmount() * 8);
                    ingredient.getMeasure().setTitle("ГР");
                } else if (ingredient.getMeasure().getTitle().equals("СТ_ЛОЖ")) {
                    ingredient.setAmount(ingredient.getAmount() * 25);
                    ingredient.getMeasure().setTitle("ГР");
                }
            } else if (ingredient.getTitle().toLowerCase().contains("сметана")) {
                if (ingredient.getMeasure().getTitle().equals("СТАКАН")) {
                    ingredient.setAmount(ingredient.getAmount() * 200);
                    ingredient.getMeasure().setTitle("ГР");
                } else if (ingredient.getMeasure().getTitle().equals("СТ_ЛОЖ")) {
                    ingredient.setAmount(ingredient.getAmount() * 25);
                    ingredient.getMeasure().setTitle("ГР");
                }
            }
        }

    }
}
