package org.models;

/**
 * Исключение для выброса ошибки знакового числа
 * @author Aladser
 */
public class SignedNumberException extends Exception{
    public SignedNumberException(int number){     
        super(number + ": только беззнаковое целое число");
    }
}
