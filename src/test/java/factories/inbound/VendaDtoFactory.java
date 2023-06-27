package factories.inbound;

import com.loja65.frentecaixa.inbound.adapter.dto.ProdutovendaDto;
import com.loja65.frentecaixa.inbound.adapter.dto.VendaDto;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;

@ApplicationScoped
public class VendaDtoFactory {

    public VendaDto createVenda(){
        return new VendaDto(null,1,10.50,25.00,35.50,
                "CARTAO_DINHEIRO", 2.0, 33.5,67987,
                new ProdutovendaDto(null, "codBara", "descricao"), LocalDateTime.now());
    }
}
