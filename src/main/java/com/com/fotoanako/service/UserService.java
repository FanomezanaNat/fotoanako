package com.com.fotoanako.service;

import static com.com.fotoanako.model.UserRole.USER;
import static com.com.fotoanako.model.mapper.UserMapper.toResponse;

import jakarta.persistence.EntityNotFoundException;
import com.com.fotoanako.model.User;
import com.com.fotoanako.model.dto.UserRequest;
import com.com.fotoanako.model.dto.UserResponse;
import com.com.fotoanako.model.mapper.UserMapper;
import com.com.fotoanako.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;


  public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
  }

  public UserResponse register(UserRequest request) {

    if (repository.existsByEmail(request.email())) {
      throw new RuntimeException("Email already used");
    }

    var user = User.builder()
        .firstName(request.firstName())
        .lastName(request.lastName())
        .email(request.email())
        .password(passwordEncoder.encode(request.password()))
        .role(USER)
        .build();

    var userToSaved = repository.save(user);

    return UserMapper.toResponse(userToSaved);
  }

  @Transactional(readOnly = true)
  public Page<UserResponse> findAll(Pageable pageable) {
    return repository.findAll(pageable)
        .map(UserMapper::toResponse);
  }

  @Transactional(readOnly = true)
  public UserResponse findById(Long id) {
    return repository.findById(id)
        .map(UserMapper::toResponse)
        .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
  }

  public UserResponse update(Long id, UserRequest request) {
    User user = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

    user.setLastName(request.lastName());
    user.setFirstName(request.firstName());

    return toResponse(user);
  }

  public void delete(Long id) {
    if (!repository.existsById(id)) {
      throw new EntityNotFoundException("User not found with id: " + id);
    }
    repository.deleteById(id);
  }
}
