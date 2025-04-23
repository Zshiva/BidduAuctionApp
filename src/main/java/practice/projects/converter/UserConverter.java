package practice.projects.converter;

import practice.projects.controller.payload.LoginUserRequestPayload;
import practice.projects.usecase.login.LoginUseCaseRequest;
import practice.projects.usecase.login.LoginUseCaseRequestBuilder;
import practice.projects.usecase.register.RegisterUseCaseRequestBuilder;
import practice.projects.controller.payload.RegisterRequestPayload;
import practice.projects.repository.UserEntity;
import practice.projects.usecase.register.RegisterUseCaseRequest;

public class UserConverter {
    public static LoginUseCaseRequest toRequest(LoginUserRequestPayload payload){
        return LoginUseCaseRequestBuilder.builder()
                .email(payload.email())
                .password(payload.password())
                .build();
    }
    public static RegisterUseCaseRequest toRequest(RegisterRequestPayload payload){
        return RegisterUseCaseRequestBuilder.builder()
                .name(payload.name())
                .email(payload.email())
                .address(payload.address())
                .contact(payload.contact())
                .roles(payload.roles())
                .build();
    }
    public static UserEntity toEntity(RegisterUseCaseRequest request, String password){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(request.name());
        userEntity.setEmail(request.email());
        userEntity.setPassword(password);
        userEntity.setAddress(request.address());
        userEntity.setContact(request.contact());
        userEntity.setRoles(request.roles());
        return userEntity;
    }
}
