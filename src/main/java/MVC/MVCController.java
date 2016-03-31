package MVC;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by D.Lazorkin on 31.03.2016.
 */
public interface MVCController {

    MVCModel handleRequest(HttpServletRequest request);

}