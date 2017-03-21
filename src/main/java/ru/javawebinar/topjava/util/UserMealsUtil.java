package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(14, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        List<UserMealWithExceed> mealListExceed = new ArrayList();
        List<UserMeal> mealListExceedTemp = new ArrayList();
        int totalDayCalories = 0;
        boolean justBoolean = false;
        for (UserMeal userMeal : mealList) {
            totalDayCalories = totalDayCalories + userMeal.getCalories();
            if (totalDayCalories > caloriesPerDay) {
                justBoolean = true;
            }
            if ((userMeal.getDateTime().getHour() > startTime.getHour()) && (userMeal.getDateTime().getHour() < endTime.getHour())) {
                mealListExceedTemp.add(userMeal);
            }
            if (userMeal.getDescription().equals("Ужин")) {
                totalDayCalories = 0;
                if (mealListExceedTemp.size() > 0) {
                    for (UserMeal userMealtemp : mealListExceedTemp
                            ) {
                        mealListExceed.add(new UserMealWithExceed(userMealtemp.getDateTime(), userMealtemp.getDescription(),
                                userMealtemp.getCalories(), justBoolean));
                    }

                    mealListExceedTemp.clear();
                }

            }


        }
        return mealListExceed;
    }
}
