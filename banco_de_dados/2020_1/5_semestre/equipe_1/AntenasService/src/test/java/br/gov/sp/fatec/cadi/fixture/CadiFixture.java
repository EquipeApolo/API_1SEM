package br.gov.sp.fatec.cadi.fixture;

import br.gov.sp.fatec.cadi.domain.Cadi;

import javax.persistence.Id;

public class CadiFixture {

    private static final Boolean ACTIVE = true;
    private static final String POSITION = "professor";
    private static final String CPF = "111.111.111-11";
    private static final String EMAIL = "email@teste.com";
    private static final String NAME = "test";
    private static final String PASSWORD = "test";
    private static final Long ID = 1l;

    public static Cadi newCadi() {
        Cadi cadi = new Cadi();
        cadi.setId(ID);
        cadi.setActive(ACTIVE);
        cadi.setCpf(CPF);
        cadi.setEmail(EMAIL);
        cadi.setPassword(PASSWORD);
        cadi.setPosition(POSITION);
        cadi.setName(NAME);

        return cadi;
    }

    public static Cadi newCadi(Long id, Boolean active) {
        Cadi cadi = new Cadi();
        cadi.setId(id);
        cadi.setActive(active);
        cadi.setCpf(CPF);
        cadi.setEmail(EMAIL);
        cadi.setPassword(PASSWORD);
        cadi.setPosition(POSITION);
        cadi.setName(NAME);

        return cadi;
    }
}
