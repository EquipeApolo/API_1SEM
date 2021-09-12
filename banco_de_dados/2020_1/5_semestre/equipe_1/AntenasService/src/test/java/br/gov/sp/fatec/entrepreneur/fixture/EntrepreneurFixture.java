package br.gov.sp.fatec.entrepreneur.fixture;

import br.gov.sp.fatec.entrepreneur.domain.Entrepreneur;

public class EntrepreneurFixture {

    private static final Boolean ACTIVE = true;
    private static final String COMPANY = "empresinha";
    private static final String CPF = "111.111.111-11";
    private static final String EMAIL = "email@teste.com";
    private static final String NAME = "test";
    private static final String PASSWORD = "test";
    private static final String TELEPHONE = "(12) 91111-1111";
    private static final Long ID = 1l;

    public static Entrepreneur newEntrepreneur() {
        Entrepreneur entrepreneur = new Entrepreneur();
        entrepreneur.setId(ID);
        entrepreneur.setActive(ACTIVE);
        entrepreneur.setEmail(EMAIL);
        entrepreneur.setCpf(CPF);
        entrepreneur.setName(NAME);
        entrepreneur.setPassword(PASSWORD);
        entrepreneur.setTelephone(TELEPHONE);

        return entrepreneur;
    }

    public static Entrepreneur newEntrepreneur(Long id, Boolean active) {
        Entrepreneur entrepreneur = new Entrepreneur();
        entrepreneur.setId(id);
        entrepreneur.setActive(active);
        entrepreneur.setEmail(EMAIL);
        entrepreneur.setCpf(CPF);
        entrepreneur.setName(NAME);
        entrepreneur.setPassword(PASSWORD);
        entrepreneur.setTelephone(TELEPHONE);

        return entrepreneur;
    }
}
