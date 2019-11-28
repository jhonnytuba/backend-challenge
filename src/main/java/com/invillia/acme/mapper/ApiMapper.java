package com.invillia.acme.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ApiMapper {

    private ModelMapper mapper;

    public ApiMapper() {
        this.mapper = new ModelMapper();
        this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.mapper.getConfiguration().setPropertyCondition(pContext -> pContext.getSource() != null);
    }

    public void map(Object obj, Object objDest) {
        mapper.map(obj, objDest);
    }

    public <T> T map(Object obj, Class<T> clazz) {
        return mapper.map(obj, clazz);
    }
}
