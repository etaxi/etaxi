package MVC;

/**
 * Created by D.Lazorkin on 31.03.2016.
 */
public class MVCModel {

    private String jspName;
    private Object data;
    private String message;

    public MVCModel(String jspName, Object data) {
        this.jspName = jspName;
        this.data = data;
        this.message = null;
    }

    public MVCModel(String jspName, Object data, String message) {
        this.jspName = jspName;
        this.data = data;
        this.message = message;
    }

    public String getJspName() {
        return jspName;
    }

    public Object getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
