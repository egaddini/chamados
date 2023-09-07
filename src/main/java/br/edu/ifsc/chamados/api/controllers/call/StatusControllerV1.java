package br.edu.ifsc.chamados.api.controllers.call;

public interface StatusControllerV1 {

    public static final String BASE_PATH = CallControllerV1.BASE_PATH + "/status";

    public static final String ID_PATH = "/{id}";
    public static final String NAME_PATH = "/name";
    public static final String FREE_WEIGHTS_PATH = "/free-weights";
    public static final String CHANGE_PATH = "/change/{id}";

}
