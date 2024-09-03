package th.mfu.dto.mapper;

import org.mapstruct.Mapper;

import th.mfu.dto.CustomerDTO;
import org.mapstruct.MappingTarget;

import th.mfu.domain.Customer;
import org.mapstruct.NullValuePropertyMappingStrategy;

import org.mapstruct.BeanMapping;


@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateCustomerFromDto(CustomerDTO dto,@MappingTarget Customer entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateCustomerFromEntity(Customer entity,@MappingTarget CustomerDTO dto);
}
