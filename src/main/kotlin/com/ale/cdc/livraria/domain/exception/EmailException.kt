package com.ale.cdc.livraria.domain.exception

class EmailException (email: String) :
    RuntimeException("Email já cadastrado: $email")