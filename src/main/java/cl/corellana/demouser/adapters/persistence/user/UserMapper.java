package cl.corellana.demouser.adapters.persistence.user;

import cl.corellana.demouser.adapters.web.dto.PhoneDTO;
import cl.corellana.demouser.adapters.web.dto.UserDTO;
import cl.corellana.demouser.adapters.web.dto.UserResponseDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Optional;
import java.util.UUID;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity toEntity(UserDTO userDTO);

    @Mapping(expression = "java(userEntity.getId() != null ? userEntity.getId().toString() : null)", target = "id")
    UserResponseDTO toDTO(UserEntity userEntity);

    @AfterMapping
    default void setPhoneRef(@MappingTarget UserEntity user){

        Optional.ofNullable(user.getPhones()).ifPresent(phones -> phones.forEach(phone -> phone.setUser(user)));
    }

}
