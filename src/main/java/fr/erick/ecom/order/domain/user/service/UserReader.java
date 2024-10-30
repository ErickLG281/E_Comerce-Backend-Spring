package fr.erick.ecom.order.domain.user.service;

import fr.erick.ecom.order.domain.user.aggregate.User;
import fr.erick.ecom.order.domain.user.repository.UserRepository;
import fr.erick.ecom.order.domain.user.vo.UserEmail;
import fr.erick.ecom.order.domain.user.vo.UserPublicId;

import java.util.Optional;

public class UserReader {

  private final UserRepository userRepository;

  public UserReader(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Optional<User> getByEmail(UserEmail userEmail) {
    return userRepository.getOneByEmail(userEmail);
  }

  public Optional<User> getByPublicId(UserPublicId userPublicId) {
    return userRepository.get(userPublicId);
  }
}
