package MVC;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by D.Lazorkin on 31.03.2016.
 */
public interface MVCController {

    MVCModel handleGetRequest(HttpServletRequest request) throws SQLException;

    MVCModel handlePostRequest(HttpServletRequest request);

}