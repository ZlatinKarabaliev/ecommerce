package com.ecommerce.server.demo.configuration;

import com.ecommerce.server.demo.model.tsc.DeviceSession;
import com.ecommerce.server.demo.service.DeviceSessionMutationSpec;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        modelMapper.typeMap(DeviceSessionMutationSpec.class, DeviceSession.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getExpireDateAsTimestamp(),
                            (dest, v) -> dest.setExpireDate((Timestamp) v));
                    mapper.map(src -> src.getStartDateAsTimestamp(),
                            (dest, v) -> dest.setStartDate((Timestamp) v));
                });

        return modelMapper;
    }

}