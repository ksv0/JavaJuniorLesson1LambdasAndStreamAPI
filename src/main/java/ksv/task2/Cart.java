package ksv.task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Корзина
 * @param <T> Еда
 */
public class Cart<T extends Food> {

    //region Поля

    /**
     * Товары в магазине
     */
    private final ArrayList<T> foodstuffs;
    private final UMarket market;
    private final Class<T> clazz;

    //endregion

    //region Конструкторы

    /**
     * Создание нового экземпляра корзины
     * @param market принадлежность к магазину
     */
    public Cart(Class<T> clazz, UMarket market)
    {
        this.clazz = clazz;
        this.market = market;
        foodstuffs = new ArrayList<>();
    }

    //endregion

    /**
     * Балансировка корзины
     * Переработать метод балансировки корзины товаров cardBalancing() с использованием Stream API
     */

    public void cardBalancing() {
        boolean[] nutrients = {false, false, false}; // proteins, fats, carbohydrates

        if (foodstuffs.stream().allMatch(food -> {
            if (food.getProteins()) nutrients[0] = true;
            if (food.getFats()) nutrients[1] = true;
            if (food.getCarbohydrates()) nutrients[2] = true;
            return nutrients[0] && nutrients[1] && nutrients[2];
        })) {
            System.out.println("Корзина уже сбалансирована по БЖУ.");
            return;
        }

        market.getThings(clazz).stream()
                .filter(thing -> !nutrients[0] && thing.getProteins() || !nutrients[1] && thing.getFats() || !nutrients[2] && thing.getCarbohydrates())
                .forEach(thing -> {
                    if (!nutrients[0] && thing.getProteins()) nutrients[0] = true;
                    if (!nutrients[1] && thing.getFats()) nutrients[1] = true;
                    if (!nutrients[2] && thing.getCarbohydrates()) nutrients[2] = true;
                    foodstuffs.add(thing);
                });

        if (nutrients[0] && nutrients[1] && nutrients[2])
            System.out.println("Корзина сбалансирована по БЖУ.");
        else
            System.out.println("Невозможно сбалансировать корзину по БЖУ.");
    }

    public Collection<T> getFoodstuffs() {
        return foodstuffs;
    }

    /**
     * Распечатать список продуктов в корзине
     */
    public void printFoodstuffs()
    {
        /*int index = 1;
        for (var food : foodstuffs)
            System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n", index++, food.getName(), food.getProteins() ? "Да" : "Нет",
                    food.getFats() ? "Да" : "Нет", food.getCarbohydrates() ? "Да" : "Нет");*/
        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                index.getAndIncrement(), food.getName(),
                food.getProteins() ? "Да" : "Нет",
                food.getFats() ? "Да" : "Нет",
                food.getCarbohydrates() ? "Да" : "Нет"));

    }


}
