package alticci;

import io.quarkus.cache.CacheResult;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.logging.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.print.Book;

@Path("/alticci")
public class AlticciResource {

    private static final Logger LOG = Logger.getLogger(AlticciResource.class);

    @GET
    @Path("/{n}")
    @Produces(MediaType.TEXT_PLAIN)
    @CacheResult(cacheName = "alticci-cache")
    @Operation(summary = "API para calcular o próximo número da sequência Alticci")
    @APIResponse(responseCode = "200", description = "Próxima sequência encontrada")
    @APIResponse(responseCode = "400", description = "Número inválido")
    public int proximaSequencia(
            @PathParam("n")
            @Parameter(description = "número para cálculo da próxima sequência (n >= 0)")
            int n) {
        // Informações de log para testar cache da aplicação
        LOG.info("Obtendo próxima sequência de " + n);
        return calcularSequencia(n);
    }

    /**
     * A sequência ALticci é definida da seguinte forma:
     *
     * <pre>
     *      n=0 => a(0) => 0
     *      n=1 => a(1) => 1
     *      n=2 => a(2) => 1
     *      n>2 => a(n-3) + a(n-2)
     * </pre>
     *
     * @param n número a ser calculado
     * @return o próximo número da sequência
     */
    private int calcularSequencia(int n) {
        if (n < 0) {
            throw new WebApplicationException(400);
        }
        if (n == 0) return 0;
        if (n <= 2) return 1;
        return calcularSequencia(n - 3) + calcularSequencia(n - 2);
    }
}