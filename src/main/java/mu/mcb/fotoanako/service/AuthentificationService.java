package mu.mcb.fotoanako.service;

import static mu.mcb.fotoanako.model.UserRole.USER;

import mu.mcb.fotoanako.model.LoginForm;
import mu.mcb.fotoanako.model.User;
import mu.mcb.fotoanako.model.UserRole;
import mu.mcb.fotoanako.model.dto.AuthentificationResponse;
import mu.mcb.fotoanako.model.dto.UserRequest;
import mu.mcb.fotoanako.model.dto.UserResponse;
import mu.mcb.fotoanako.model.mapper.UserMapper;
import mu.mcb.fotoanako.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;


  public AuthentificationService(UserRepository userRepository, PasswordEncoder passwordEncoder,
      JwtService jwtService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
  }

  public UserResponse register(UserRequest request) {

    if (userRepository.existsByEmail(request.email())) {
      throw new RuntimeException("Email already used");
    }

    var user = User.builder()
        .firstName(request.firstName())
        .lastName(request.lastName())
        .email(request.email())
        .password(passwordEncoder.encode(request.password()))
        .role(USER)
        .build();

    var userToSaved = userRepository.save(user);

    return UserMapper.toResponse(userToSaved);
  }

  public AuthentificationResponse login(LoginForm form) {

    User user = userRepository.findByEmail(form.email())
        .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));

    if (!passwordEncoder.matches(form.password(), user.getPassword())) {

      throw new BadCredentialsException("Invalid email or password");
    }
    String token = jwtService.generateToken(user);
    return AuthentificationResponse.builder()
        .token(token)
        .email(user.getEmail())
        .role(user.getRole())
        .build();
  }
}
