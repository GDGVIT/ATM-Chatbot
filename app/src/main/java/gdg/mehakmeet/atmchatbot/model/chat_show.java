package gdg.mehakmeet.atmchatbot.model;

/**
 * Created by MEHAKMEET on 12-03-2018.
 */
public class chat_show {
    private String message;

    private int type;
    private int rv2;

    public chat_show(String message, int type, int rv2) {
        this.message = message;
        this.type = type;
        this.rv2 = rv2;
    }

    public int getRv2() {
        return rv2;
    }

    public void setRv2(int rv2) {
        this.rv2 = rv2;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}