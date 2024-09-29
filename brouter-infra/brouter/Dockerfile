FROM gradle:jdk17-jammy as build

RUN mkdir /tmp/brouter
WORKDIR /tmp/brouter
COPY . .

# Ustawienie uprawnień i konwersja znaków końca linii dla gradlew
RUN sed -i 's/\r$//' gradlew
RUN chmod +x gradlew

# Kompilacja projektu
RUN ./gradlew clean build

# Konwersja znaków końca linii i ustawienie uprawnień dla server.sh
RUN sed -i 's/\r$//' misc/scripts/standalone/server.sh
RUN chmod +x misc/scripts/standalone/server.sh

# (Opcjonalnie) Wyświetlenie zawartości katalogu standalone
RUN ls -l misc/scripts/standalone/

FROM openjdk:17.0.1-jdk-slim

# Kopiowanie plików z etapu budowania
COPY --from=build /tmp/brouter/brouter-server/build/libs/brouter-*-all.jar /brouter.jar
COPY --from=build /tmp/brouter/misc/scripts/standalone/server.sh /bin/
COPY --from=build /tmp/brouter/misc/* /profiles2/

# Ustawienie uprawnień do pliku server.sh w obrazie końcowym
RUN chmod +x /bin/server.sh

CMD ["/bin/server.sh"]
