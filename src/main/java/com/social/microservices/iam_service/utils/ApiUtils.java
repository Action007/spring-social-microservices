package com.social.microservices.iam_service.utils;

import com.social.microservices.iam_service.model.constants.ApiConstants;

public class ApiUtils {

    public static String getMethodName() {
        try {
            return Thread.currentThread().getStackTrace()[1].getMethodName();
        } catch (Exception e) {
            return ApiConstants.UNDEFINED;
        }
    }
}