package com.streamnet.client;

import com.streamnet.commons.configuration.FeignConfiguration;
import com.streamnet.commons.constants.FeignConstants;
import com.streamnet.commons.constants.PathConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = FeignConstants.LOCALIZATION_SERVICE, path = PathConstants.API_V1_LOCALIZATION, configuration = FeignConfiguration.class)
public interface LocalizationClient {

    @GetMapping(PathConstants.PHONE_CODE)
    Boolean isPhoneCodeExists(@PathVariable("code") String code);
}
