package service;

import dao.UserDAO;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements service.Service<User> {
    @Autowired
    UserDAO userDAO;

    @Override
    public Optional<User> get(int id) {
        return userDAO.get(id);
    }

    public Optional<User> check(String name, String password) {
        return userDAO.check(name, password);
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public boolean add(User user) {
        return userDAO.add(user) > 0;
    }

    @Override
    public boolean update(User user) {
        return userDAO.update(user) > 0;
    }

    @Override
    public boolean delete(int id) {
        return userDAO.delete(id) > 0;
    }
}
