package com.social.microservices.iam_service.model.response;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IamResponse<P extends Serializable> implements Serializable {

    private String message;
    private P payload;
    private boolean success;

    public static <P extends Serializable> IamResponse<P> createSuccessful(P payload) {
        return new IamResponse<>(StringUtils.EMPTY, payload, true);
    }
}
