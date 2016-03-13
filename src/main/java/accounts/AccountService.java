package accounts;

import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import dataSets.CustomerDataSet;
import services.DBService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountService {
    private final Map<String, UserProfile> loginToProfile = new HashMap<String, UserProfile>();
    private final Map<String, UserProfile> sessionIdToProfile = new HashMap<String, UserProfile>();

    public AccountService() throws SQLException {

        DBService dbService = new DBService();
        CustomerDAO customerDAO = new CustomerDAOImpl(dbService.getConnection(), dbService.getDatabaseName());
        List<CustomerDataSet> listOfCustomers = customerDAO.getAll();

        for(CustomerDataSet object: listOfCustomers){
            UserProfile newUser = new UserProfile(object.getLogin(), object.getPassword(), object.getName());
            loginToProfile.put(newUser.getLogin(), newUser);
        }

    }

    public void addNewUser(UserProfile userProfile) {
        loginToProfile.put(userProfile.getLogin(), userProfile);
    }

    public UserProfile getUserByLogin(String login) {
        return loginToProfile.get(login);
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
