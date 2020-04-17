package br.com.eldorado.service.validators;

import br.com.eldorado.domain.Appointment;

public abstract class AppointmentValidator {
    private AppointmentValidator proximo;
    
    public AppointmentValidator link(AppointmentValidator proximo) {
        this.proximo = proximo;
        return proximo;
    }

    public void executaValidacao(Appointment registro) {
        this.valida(registro);
        validaProximo(registro);
    }
    
    protected abstract void valida(Appointment registro);
    
    private void validaProximo(Appointment registro) {
        if (proximo != null)
            proximo.valida(registro);
    }
}
