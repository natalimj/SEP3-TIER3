package sep3.classes.Model;

import java.io.Serializable;

public class Request implements Serializable {
    private String type;
    private Object arg;

    public Request() {
    }

    public Request(String type, Object arg) {
        this.type = type;
        this.arg = arg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getArg() {
        return arg;
    }

    public void setArg(Object arg) {
        this.arg = arg;
    }
}
