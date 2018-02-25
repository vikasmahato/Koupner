package com.koupner.koupner.util;

import com.koupner.koupner.pojo.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

/**
 * Created by vikasmahato on 08/02/18.
 */

public class MenuUtil {

    private static final String RESTAURANT_URL_FMT = "https://storage.googleapis.com/firestorequickstarts.appspot.com/food_%d.png";

    private static final int MAX_IMAGE_NUM = 22;

    /**
     * Get a list of random Rating POJOs.
     */
    public static List<Product> getRandomList(int length) {
        List<Product> result = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            result.add(getRandom());
        }

        return result;
    }


    /**
     * Create a random Rating POJO.
     */
    public static Product getRandom() {
        Random random = new Random();

        Product rating = new Product(UUID.randomUUID().toString(), UUID.randomUUID().toString(), 50, getRandomImageUrl(random), "Veg");

        return rating;
    }

    /**
     * Get a random image.
     */
    private static String getRandomImageUrl(Random random) {
        // Integer between 1 and MAX_IMAGE_NUM (inclusive)
        int id = random.nextInt(MAX_IMAGE_NUM) + 1;

        return String.format(Locale.getDefault(), RESTAURANT_URL_FMT, id);
    }
}
