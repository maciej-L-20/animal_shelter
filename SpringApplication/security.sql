CREATE TABLE uzytkownicy (
    nazwa_uzytkownika VARCHAR2(50) PRIMARY KEY,
    haslo VARCHAR2(100),
    wlaczony Character(1),
    imie VARCHAR2(50),
    nazwisko VARCHAR2(50),
    data_urodzenia DATE
);

CREATE TABLE uprawnienia (
    nazwa_uzytkownika VARCHAR2(50),
    uprawnienie VARCHAR2(50),
    CONSTRAINT fk_uprawnienia_uzytkownicy FOREIGN KEY (nazwa_uzytkownika) REFERENCES uzytkownicy(nazwa_uzytkownika)
);
INSERT INTO uzytkownicy (nazwa_uzytkownika, haslo, wlaczony)
VALUES ('user', '$2a$10$yByFdDgLOM9JhAcv4EyhJOsciXf4U.ugvI1XfYwUNWsolizh0CAgm','T');
INSERT INTO uprawnienia (nazwa_uzytkownika, uprawnienie)
VALUES ('user', 'USER');
INSERT INTO uzytkownicy (nazwa_uzytkownika, haslo, wlaczony)
VALUES ('admin', '$2a$10$oJFe92XqWcKO2u0emnEFXOeRP.QUiTo0NIhyYs7hFI6sEMY81b1r6','T');
INSERT INTO uprawnienia (nazwa_uzytkownika, uprawnienie)
VALUES ('admin', 'ADMIN');