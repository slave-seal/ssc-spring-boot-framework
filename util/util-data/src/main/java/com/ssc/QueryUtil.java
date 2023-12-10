package com.ssc;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QueryUtil {

    public boolean isNotValid(BooleanExpression... args) {
        for (Object arg : args) {
            if (arg != null) return false;
        }
        return true;
    }

}
