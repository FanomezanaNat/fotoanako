package mu.mcb.fotoanako.service;

import static mu.mcb.fotoanako.model.mapper.UserMapper.toResponse;

import jakarta.persistence.EntityNotFoundException;
import mu.mcb.fotoanako.model.User;
import mu.mcb.fotoanako.model.dto.UserRequest;
import mu.mcb.fotoanako.model.dto.UserResponse;
import mu.mcb.fotoanako.model.mapper.UserMapper;
import mu.mcb.fotoanako.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

  private final UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  public UserResponse create(UserRequest request) {
    var user = User.builder()
        .lastName(request.lastName())
        .firstName(request.firstName())
        .email(request.email())
        .build();

    var savedUser = repository.save(user);
    return toResponse(savedUser);
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
    user.setEmail(request.email());

    return toResponse(user);
  }

  public void delete(Long id) {
    if (!repository.existsById(id)) {
      throw new EntityNotFoundException("User not found with id: " + id);
    }
    repository.deleteById(id);
  }


}
