package com.moura.comicShop.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(contact = @Contact(name = "Adryel Torres", email = "torresadryel@gmail.com", url = "https://www.instagram.com/dhyelltorres/"), description = "API Desafio Técnico Processo Seletivo SQUAD DIGITAL", title = "comic-shop SQUAD DIGITAL - Adryel Torres", version = "1.0", license = @License(name = "Licence name", url = "https://some-url.com"), termsOfService = "Terms of service"), externalDocs = @ExternalDocumentation(description = "README", url = "https://github.com/DhyellTorres/comic-shop#readme"), servers = {
        @Server(description = "Local ENV", url = "http://localhost:8080"),
        @Server(description = "PROD ENV", url = "")
}, security = {
        @SecurityRequirement(name = "bearerAuth")
})
@SecurityScheme(name = "bearerAuth", description = "JWT auth description", scheme = "bearer", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", in = SecuritySchemeIn.HEADER)
public class OpenApiConfig {
}
