package com.example.redevabletnb.security.springjwt.controllers;

import java.util.HashSet;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.redevabletnb.security.springjwt.ChangePasswordRequest;
import com.example.redevabletnb.security.springjwt.models.ERole;
import com.example.redevabletnb.security.springjwt.models.Role;
import com.example.redevabletnb.security.springjwt.models.User;
import com.example.redevabletnb.security.springjwt.payload.request.LoginRequest;
import com.example.redevabletnb.security.springjwt.payload.request.SignupRequest;
import com.example.redevabletnb.security.springjwt.payload.response.JwtResponse;
import com.example.redevabletnb.security.springjwt.payload.response.MessageResponse;
import com.example.redevabletnb.security.springjwt.repository.RoleRepository;
import com.example.redevabletnb.security.springjwt.repository.UserRepository;
import com.example.redevabletnb.security.springjwt.security.jwt.JwtUtils;
import com.example.redevabletnb.security.springjwt.security.jwt.services.UserDetailsImpl;

import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  //methode ajouter

  @Autowired
  private UserRepository userRepository;

  // Get all users
  @GetMapping
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  // Get a user by ID
  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    Optional<User> user = userRepository.findById(id);
    return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  // Create a new user
  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    User newUser = userRepository.save(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
  }

  // Update a user
  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
    if (!userRepository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }
    user.setId(id);
    userRepository.save(user);
    return ResponseEntity.ok(user);
  }

  // Delete a user
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    if (!userRepository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }
    userRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  //methode auth
  @Autowired
  AuthenticationManager authenticationManager;

 /* @Autowired
  UserRepository userRepository;*/

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @GetMapping("/user-profile")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public ResponseEntity<?> getUserProfile() {
    UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal();
    return ResponseEntity.ok(new User(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail()));
  }

  @PutMapping("/users/{userId}/password")

  public ResponseEntity<?> updatePassword(
      @PathVariable Long userId, @Valid @RequestBody ChangePasswordRequest updatePasswordRequest) {

    User user = userRepository.getReferenceById(userId);

    if (!encoder.matches(updatePasswordRequest.getOldPassword(), user.getPassword())) {
      throw new BadCredentialsException("Invalid old password");
    }

    user.setPassword(encoder.encode(updatePasswordRequest.getNewPassword()));
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("Password updated successfully"));
  }

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    System.out.println("tokeeeen generate :" + jwt);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    System.out.println(jwt + "   " + userDetails.getUsername());

    return ResponseEntity.ok(new JwtResponse(jwt,
        userDetails.getId(),
        userDetails.getUsername(),
        userDetails.getEmail(),
        roles));

  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);

            break;
          case "mod":
            Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(modRole);

            break;
          default:
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }


  @GetMapping("/users")
  @PreAuthorize("permitAll()")
  public ResponseEntity<List<User>> findAllUsers() {
    List<User> users = userRepository.findAll();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/users/admin")
  @PreAuthorize("permitAll()")
  public ResponseEntity<List<User>> findAdmin() {
    List<User> users = userRepository.findAdmin();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/users/mod")
  @PreAuthorize("permitAll()")
  public ResponseEntity<List<User>> findMod() {
    List<User> users = userRepository.findMod();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/users/user")
  @PreAuthorize("permitAll()")
  public ResponseEntity<List<User>> findUser() {
    List<User> users = userRepository.findUser();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/users/role/{username}")
  @PreAuthorize("permitAll()")
  public ResponseEntity<ERole> findRole(@PathVariable String username) {
    ERole role = userRepository.findRole(username);
    return ResponseEntity.ok(role);
  }

  @GetMapping("user/{username}")
  @PreAuthorize("permitAll()")
  public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
    Optional<User> user = userRepository.findByUsername(username);

    return user.map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/u/cin/{cin}")
  public Optional<User> findByCin(@PathVariable String cin) {
    return userRepository.findByCin(cin);
  }


}
