package com.up.tx.manager.model;

public class Constants {

    public static final Integer ROWS_PER_PAGE = 5;
    public static final Integer RESPONSE_OK_CODE = 0;
    public static final Integer RESPONSE_NO_DATA_FOUND_CODE = 1;
    public static final Integer RESPONSE_AUTH_ERROR_CODE = 401;
    public static final Integer RESPONSE_ERROR_CODE = 99;
    public static final Integer TRANSACTION_ERROR_CODE = 80;
    public static final Integer FUNDS_INSUFFICIENTS_ERROR_CODE = 85;
    public static final Integer DELETE_USER_ERROR_CODE = 50;
    public static final Integer MODIFY_USER_FIELD_REQUIRED_ERROR_CODE = 51;
    public static final Integer DELETE_CREDIT_CARD_ERROR_CODE = 53;
    public static final Integer DELETE_RECIPIENT_ERROR_CODE = 55;
    
    
    public static final String STATUS_CODE = "statusCode";

    public static final String GENERIC_OK_MESSAGE="Transacción realizada con éxito";
    public static final String GENERIC_ERROR_MESSAGE="Error genérico";
   
    public static final String NO_DATA_FOUND_ERROR_MESSAGE="No se encontró información para los valores ingresados";

    public static final String UPDATE_COMMISSIONS_OK_MESSAGE="Comisiones actualizadas correctamente";

    public static final String USER_OR_PASS_ERROR_MESSAGE="Usuario o password incorrecto";
    public static final String CREATE_USER_OK_MESSAGE="Usuario creado correctamente";
    public static final String USER_ALREADY_EXIST_ERROR_MESSAGE="Ya existe un usuario con ese nombre";
    public static final String CREATE_USER_FIELD_ERROR_MESSAGE="No se ingresaron todos los campos requeridos.";
    public static final String DELETE_USER_ERROR_MESSAGE="Error al eliminar el usuario.";
    public static final String MODIFY_USER_FIELD_REQUIRED_ERROR_MESSAGE="No se recibieron todos los campos obligatorios";
    public static final String CREATE_CREDIT_CARD_OK_MESSAGE="Tarjeta adherida correctamente";
    public static final String CREDIT_CARD_ALREADY_EXIST_ERROR_MESSAGE="Ya existe una tarjet de credito con esos datos";
    public static final String DELETE_CREDIT_CARD_ERROR_MESSAGE="Error al eliminar la tarjeta.";
    public static final String DELETE_USER_WITH_BALANCE_ERROR = "No se puede eliminar el usuario. El saldo tiene que ser $0.00";
    
    public static final String CREATE_RECIPIENT_OK_MESSAGE="Destinatario adherido";
    public static final String RECIPIENT_ALREADY_EXIST_ERROR_MESSAGE="Ya existe un detinatario con estos datos";
    public static final String DELETE_RECIPIENT_ERROR_MESSAGE="Error al eliminar el destinatario.";
    
    public static final String AUTH_ERROR_MESSAGE="Token erroneo o vencido";
    
    public static final String TRANSACTION_ERROR_MESSAGE="Ocurrió un error realizando la transacción";
    
    public static final String FUNDS_INSUFFICIENTS_ERROR_MESSAGE="Fondos insuficientes";

}
