package sunbase.portal.api.service;

import sunbase.portal.api.model.User;
public interface UserService {
    public User signUp(User user);

    public User updateUser(User user);

    public User getUserById(long userId);
}