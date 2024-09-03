package th.mfu.dto.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import th.mfu.domain.Theatre;
import th.mfu.dto.TheatreDTO;

@Mapper(componentModel = "spring")
public interface TheatreMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateTheatreFromDto(TheatreDTO dto,@MappingTarget Theatre entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateTheatreFromEntity(Theatre entity,@MappingTarget TheatreDTO dto);
    
} 
