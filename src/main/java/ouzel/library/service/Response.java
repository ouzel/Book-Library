package ouzel.library.service;


/*
Response to the user actions.
 */
public class Response {
    private final String message;

    public Response(String message) {
        this.message = message;
    }


    /*
    Printing the message of the responce.
     */
    public String getMessage() {
        return message;
    }
}
