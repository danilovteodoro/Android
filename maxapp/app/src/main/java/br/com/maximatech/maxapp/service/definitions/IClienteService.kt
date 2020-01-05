package br.com.maximatech.maxapp.service.definitions

import br.com.maximatech.maxapp.model.Cliente

interface IClienteService {
    fun getCliente(): Cliente
}