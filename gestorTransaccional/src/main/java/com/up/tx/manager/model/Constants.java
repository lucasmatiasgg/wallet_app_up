package com.up.tx.manager.model;

public class Constants {

    public static final Integer ROWS_PER_PAGE = 5;
    public static final Integer RESPONSE_OK_CODE = 0;
    public static final Integer RESPONSE_NO_DATA_FOUND_CODE = 1;
    public static final Integer RESPONSE_AUTH_ERROR_CODE = 401;
    public static final Integer RESPONSE_ERROR_CODE = 99;
    public static final Integer TRANSACTION_ERROR_CODE = 80;
    public static final Integer FUNDS_INSUFFICIENTS_ERROR_CODE = 85;
    
    public static final String STATUS_CODE = "statusCode";

    public static final String GENERIC_OK_MESSAGE="Transacción realizada con éxito";
    public static final String GENERIC_ERROR_MESSAGE="Error genérico";
   
    public static final String NO_DATA_FOUND_ERROR_MESSAGE="No se encontró información para los valores ingresados";

    public static final String UPDATE_COMMISSIONS_OK_MESSAGE="Comisiones actualizadas correctamente";

    public static final String USER_OR_PASS_ERROR_MESSAGE="Usuario o password incorrecto";
    public static final String CREATE_USER_OK_MESSAGE="Usuario creado correctamente";
    public static final String USER_ALREADY_EXIST_ERROR_MESSAGE="Ya existe un usuario con ese nombre";
    public static final String CREATE_USER_FIELD_ERROR_MESSAGE="No se ingresaron todos los campos requeridos.";

    public static final String AUTH_ERROR_MESSAGE="Token erroneo o vencido";
    
    public static final String TRANSACTION_ERROR_MESSAGE="Ocurrió un error realizando la transacción";
    
    public static final String FUNDS_INSUFFICIENTS_ERROR_MESSAGE="Fondos insuficientes";

}
